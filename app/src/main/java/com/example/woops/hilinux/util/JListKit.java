package com.example.woops.hilinux.util;

import android.view.View;
import android.widget.TextView;

import com.example.woops.hilinux.entity.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class JListKit {



    public static List<View> newArrayListView() {

        List<View> list = new ArrayList<View>();
        return list;
    }

    public static List<NewsItem> newArrayLists() {

        List<NewsItem> list = new ArrayList<NewsItem>();
        return list;
    }

    public static List<TextView> newArrayTextViewLists() {

        List<TextView> list = new ArrayList<TextView>();
        return list;
    }

    public static boolean isNotEmpty(List<?> tmp) {
        if (tmp.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }


}
