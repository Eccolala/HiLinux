package com.example.woops.hilinux.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.woops.hilinux.R;
import com.example.woops.hilinux.entity.NewsItem;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by woops on 16-3-24.
 */
public class NewsItemAdapter extends SimpleBaseAdapter<NewsItem> {

    public NewsItemAdapter(Context context, List<NewsItem> datas) {
        super(context, datas);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EntityHolder entityHolder = null;
        if (convertView == null){
            entityHolder = new EntityHolder();
            convertView = layoutInflater.inflate(R.layout.news_item,null);
            x.view().inject(entityHolder,convertView);
            convertView.setTag(entityHolder);
        }else {
            entityHolder = (EntityHolder) convertView.getTag();
        }
        entityHolder.item_tv_title.setText(datas.get(position).getContent());

        return convertView;
    }

    private class EntityHolder{
        @ViewInject(R.id.item_tv_title)
        TextView item_tv_title;
    }
}
