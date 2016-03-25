package com.example.woops.hilinux.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.woops.hilinux.entity.NewsItem;

import java.util.List;

/**
 * Created by woops on 16-3-25.
 */
public class NewsHeadAdapter extends SimpleBaseAdapter<NewsItem>{
    public NewsHeadAdapter(Context context, List<NewsItem> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
