package com.example.woops.hilinux.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.woops.hilinux.R;
import com.example.woops.hilinux.entity.CarLogo;
import com.example.woops.hilinux.widegt.PinnedHeaderListView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;
import java.util.Locale;

public class CarLogoAdapter extends SimpleBaseAdapter<CarLogo> implements
        AbsListView.OnScrollListener, PinnedHeaderListView.PinnedHeaderAdapter, SectionIndexer {

    private View view;

    private int mLocationPosition = -1;

    public CarLogoAdapter(Context c, List<CarLogo> datas, View view) {
        super(c, datas);
        this.view = view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EntityHolder entityHolder = null;
        if (convertView == null) {
            entityHolder = new EntityHolder();

            convertView = layoutInflater.inflate(
                    R.layout.fragment_find_brand_listview_item, null);

            x.view().inject(entityHolder,convertView);
            convertView.setTag(entityHolder);
        } else {
            entityHolder = (EntityHolder) convertView.getTag();
        }

        int section = getSectionForPosition(position);
        if (getPositionForSection(section) == position) {
            entityHolder.list_item_header.setVisibility(View.VISIBLE);
            entityHolder.list_item_header_text
                    .setText(datas.get(position).getNameSpell().toUpperCase(
                            Locale.CHINA).charAt(0)
                            + "");

        } else {
            entityHolder.list_item_header.setVisibility(View.GONE);
        }
        entityHolder.list_item_content_text.setText(datas.get(position).getName());
        //entityHolder.list_item_content_logo.setImageDrawable(ic_launcher);


        return convertView;
    }

    public class EntityHolder {
        @ViewInject(R.id.list_item_header)
        LinearLayout list_item_header;
        @ViewInject(R.id.list_item_content_text)
        TextView list_item_content_text;
        @ViewInject(R.id.list_item_content_logo)
        ImageView list_item_content_logo;
        @ViewInject(R.id.list_item_header_text)
        TextView list_item_header_text;

    }

    @Override
    public int getPinnedHeaderState(int position) {
        int realPosition = position;
        if (realPosition < 0
                || (mLocationPosition != -1 && mLocationPosition == realPosition)) {
            return PINNED_HEADER_GONE;
        }
        mLocationPosition = -1;
        int nextSection = getSectionForPosition(realPosition + 1);
        int nextSectionPosition = getPositionForSection(nextSection);
        if (nextSectionPosition != -1
                && realPosition == nextSectionPosition - 1) {
            return PINNED_HEADER_PUSHED_UP;
        }
        return PINNED_HEADER_VISIBLE;
    }

    @Override
    public void configurePinnedHeader(View header, int position, int alpha) {
        ((TextView) header.findViewById(R.id.list_item_header_text))
                .setText(datas.get(position).getNameSpell()
                        .toUpperCase(Locale.CHINA).charAt(0) + "");
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        if (view instanceof PinnedHeaderListView) {
            ((PinnedHeaderListView) view).configureHeaderView(firstVisibleItem);
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < getCount(); i++) {
            char firstChar = datas.get(i).getNameSpell().toUpperCase(Locale.CHINA)
                    .charAt(0);
            if (firstChar == sectionIndex) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return datas.get(position).getNameSpell().toUpperCase(Locale.CHINA)
                .charAt(0);
    }
}
