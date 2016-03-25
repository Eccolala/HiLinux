package com.example.woops.hilinux.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.woops.hilinux.R;
import com.example.woops.hilinux.entity.NewsItem;
import com.squareup.picasso.Picasso;

import java.util.List;


public class NewsHeadAdapter extends PagerAdapter {
    private List<NewsItem> datas;
    private Context context;
    private LayoutInflater inflater;

    public NewsHeadAdapter(Context context, List<NewsItem> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    public void refreshDatas(List<NewsItem> datas) {
        this.datas = datas;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View layout = inflater.inflate(R.layout.news_head_item,null);
        ImageView viewpager_item_img = (ImageView)layout.findViewById(R.id.viewpager_item_img);


        //使用Picasso加载图片
        Picasso.with(context).load(
                datas.get(position).getImage())
                .resize(500,200)   //设置图片大小
                .into(viewpager_item_img);

        ((ViewPager) container).addView(layout);

        TextView text_head = (TextView)layout.findViewById(R.id.news_head_tv);
        text_head.setText(datas.get(position).getTitle());

        return layout;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
