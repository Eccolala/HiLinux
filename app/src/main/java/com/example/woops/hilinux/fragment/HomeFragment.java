package com.example.woops.hilinux.fragment;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.woops.hilinux.R;
import com.example.woops.hilinux.adapter.NewsHeadAdapter;
import com.example.woops.hilinux.adapter.NewsItemAdapter;
import com.example.woops.hilinux.entity.NewsItem;
import com.example.woops.hilinux.util.ConstantUtils;
import com.example.woops.hilinux.util.JListKit;
import com.example.woops.hilinux.util.Logger;
import com.example.woops.hilinux.util.TitleBuilder;
import com.example.woops.hilinux.util.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import okhttp3.Call;

public class HomeFragment extends BaseFragment {
    private View view;
    private TitleBuilder tbBuilder;

//    @ViewInject(R.id.news_progressbar)
//    private ProgressBar proBar;

    @ViewInject(R.id.news_important_lv)
    private ListView news_import_lv;

    private NewsItemAdapter newsItemAdapter;
    //动态加载布局
    private LinearLayout loading_llyt;
    private List<NewsItem> data_news = JListKit.newArrayLists();
    private List<NewsItem> headList = JListKit.newArrayLists();
    //是否是最后一行
    private boolean isLastRow = false;
    private boolean isLoading = false;

    private boolean isMore = true;

    private int pageIndex = 0;
    private int pageSize = 20;
    //viewpager
    private FrameLayout news_head_view;
    private ViewPager news_head_vp;

    private NewsHeadAdapter headAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(activity, R.layout.frag_home, null);
        x.view().inject(this, view);

        initView();

        return view;
    }


    private void initView() {
        tbBuilder = new TitleBuilder(view);
        tbBuilder.setTitleText("首页")
                .setLeftImage(R.drawable.home_ico_city)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast(activity, "left onclick", Toast.LENGTH_SHORT);
                    }
                });

//        proBar.setVisibility(View.VISIBLE);

        //底部布局
        loading_llyt = (LinearLayout) getLayoutInflater(null).
                inflate(R.layout.listview_loading_view, null);
        //头部布局
        news_head_view = (FrameLayout) getLayoutInflater(null).
                inflate(R.layout.news_head_view, null);
        news_head_vp = (ViewPager) news_head_view.findViewById(R.id.news_head_vp);
        //初始化推荐新闻
        headAdapter = new NewsHeadAdapter(getActivity(), headList);
        news_head_vp.setAdapter(headAdapter);

        //初始化列表
        newsItemAdapter = new NewsItemAdapter(getActivity(), data_news);

        //增加头部显示布局
        news_import_lv.addHeaderView(news_head_view);
        //增加底部显示布局
        news_import_lv.addFooterView(loading_llyt);
        //绑定适配器
        news_import_lv.setAdapter(newsItemAdapter);

        //请求两个数据
        loadHeadData();
        loadListData();


        news_import_lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
                    isLastRow = true;
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (isLastRow && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (!isLoading && isMore) {
                        loadListData();
                    }
                }
                isLastRow = false;
            }
        });
    }

    private void loadHeadData(){
        OkHttpUtils.get().url(ConstantUtils.PIC_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Logger.show("HH","出错了");
            }

            @Override
            public void onResponse(String response) {
                String picList = JSON.parseObject(response).getString("top_stories");
                List<NewsItem> tmp_head = JSON.parseArray(picList,NewsItem.class);

                Logger.show("PIC",response);
                if (tmp_head.size() != 0){
                    headList.addAll(tmp_head);
                    headAdapter.refreshDatas(headList);
                    //初始选中项
                    news_head_vp.setCurrentItem(headList.size() * 1000);
                }
            }
        });
    }
    private void loadListData() {
        isLoading = true;
        pageIndex++;
        OkHttpUtils.get().url(ConstantUtils.TITLE_URL + pageSize + "/" + pageIndex).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
//                proBar.setVisibility(View.GONE);
                ToastUtils.showToast(getActivity(), "请求失败，请重试", 2000);
            }

            @Override
            public void onResponse(String response) {
                ToastUtils.showToast(getActivity(), "请求成功，恭喜", 2000);

                String list = JSON.parseObject(response).getString("results");
                List<NewsItem> tmp = JSON.parseArray(list, NewsItem.class);

//              proBar.setVisibility(View.INVISIBLE);

                if (tmp.size() != 0) {
                    if (pageIndex == 1) {
                        //移除底部加载布局
                        if (tmp.size() < pageSize) {
                            news_import_lv.removeFooterView(loading_llyt);
                        }
                    }
                    data_news.addAll(tmp);
                    newsItemAdapter.refreshDatas(data_news);
                } else {
                    isMore = false;
                    news_import_lv.removeFooterView(loading_llyt);
                    ToastUtils.showToast(getActivity(), "已没有更多数据", 3000);
                }
                isLoading = false;
            }

        });
    }

}
