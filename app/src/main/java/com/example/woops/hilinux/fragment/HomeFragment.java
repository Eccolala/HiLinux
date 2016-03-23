package com.example.woops.hilinux.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.woops.hilinux.BaseFragment;
import com.example.woops.hilinux.R;
import com.example.woops.hilinux.adapter.NavAdapter;
import com.example.woops.hilinux.util.TitleBuilder;
import com.example.woops.hilinux.util.ToastUtils;

import org.xutils.view.annotation.ViewInject;

public class HomeFragment extends BaseFragment{
    private View view;
    private TitleBuilder tbBuilder;
    @ViewInject(R.id.home_nav_sort)
    private GridView navSort;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(activity, R.layout.frag_home, null);



        initView();
        tbBuilder.setLeftText("HH");
        return view;
    }


    private void initView() {
//        navSort = (GridView)view.findViewById(R.id.home_nav_sort);
        tbBuilder = new TitleBuilder(view);
        tbBuilder
                .setTitleText("首页")

                .setLeftImage(R.drawable.home_ico_city)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast(activity, "left onclick", Toast.LENGTH_SHORT);
                    }
                });
        navSort.setAdapter(new NavAdapter());
    }


}
