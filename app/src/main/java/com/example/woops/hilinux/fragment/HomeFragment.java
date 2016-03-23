package com.example.woops.hilinux.fragment;


import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.woops.hilinux.BaseFragment;
import com.example.woops.hilinux.R;
import com.example.woops.hilinux.util.TitleBuilder;

public class HomeFragment extends BaseFragment{
    private String cityName;
    private LocationManager locationManager;
    private View view;
    private TitleBuilder tbBuilder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(activity, R.layout.frag_home, null);

        tbBuilder = new TitleBuilder(view);

        initView();
        tbBuilder.setLeftText("HH");
        return view;
    }


    private void initView() {
//        tbBuilder
//                .setTitleText("首页")
//
//                .setLeftImage(R.drawable.home_ico_city)
//                .setLeftOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ToastUtils.showToast(activity, "left onclick", Toast.LENGTH_SHORT);
//                    }
//                });
    }


}
