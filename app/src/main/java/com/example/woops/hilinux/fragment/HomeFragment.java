package com.example.woops.hilinux.fragment;


import android.os.AsyncTask;
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

import com.example.woops.hilinux.R;
import com.example.woops.hilinux.adapter.NewsHeadAdapter;
import com.example.woops.hilinux.adapter.NewsItemAdapter;
import com.example.woops.hilinux.entity.NewsItem;
import com.example.woops.hilinux.util.TitleBuilder;
import com.example.woops.hilinux.util.ToastUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {
    private View view;
    private TitleBuilder tbBuilder;

    @ViewInject(R.id.news_important_lv)
    private ListView news_import_lv;
    private NewsItemAdapter newsItemAdapter;
    //动态加载布局
    private LinearLayout loading_llyt;
    private List<NewsItem> data_news;
    //是否是最后一行
    private boolean isLastRow = false;
    private boolean isLoading = false;

    private boolean isMore = true;

    //viewpager
    private FrameLayout news_head_view;
    private ViewPager news_head_vp;
    private List<NewsItem> headList;
    private NewsHeadAdapter headAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(activity, R.layout.frag_home, null);
        x.view().inject(this, view);

        initView();
//        tbBuilder.setLeftText("HH");
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
        loading_llyt = (LinearLayout) getLayoutInflater(null).inflate(R.layout.listview_loading_view, null);
        data_news = new ArrayList<NewsItem>();
        for (int i = 0; i < 20; i++) {
            NewsItem item = new NewsItem();
            item.setContent("这是一条动态" + i);
            data_news.add(item);
        }
        newsItemAdapter = new NewsItemAdapter(getActivity(), data_news);
        news_import_lv.addFooterView(loading_llyt);
        news_import_lv.setAdapter(newsItemAdapter);

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
                    if (!isLastRow && isMore){
                        new AsyncTask<Void, Void, Void>() {

                            @Override
                            protected Void doInBackground(Void... params) {
                                try {
                                    Thread.sleep(5000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                data_news.addAll(data_news);
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                isLoading = false;
                                isMore = false;
                                newsItemAdapter.notifyDataSetChanged();

                            }
                        }.execute();
                    }else {
                        news_import_lv.removeFooterView(loading_llyt);
                        ToastUtils.showToast(getActivity(),"no data",2000);
                    }
                    isLastRow = false;
                }
            }
        });
    }


}
