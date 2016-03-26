package com.example.woops.hilinux.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.woops.hilinux.R;
import com.example.woops.hilinux.adapter.CarLogoAdapter;
import com.example.woops.hilinux.entity.CarLogo;
import com.example.woops.hilinux.util.JListKit;
import com.example.woops.hilinux.widegt.PinnedHeaderListView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class SearchFragment extends BaseFragment {

    private View view;
    private TextView titlebar_tv;

    @ViewInject(R.id.find_brand_lv)
    PinnedHeaderListView find_brand_lv;

    //设置数据源
    private List<CarLogo> datas = JListKit.newArrayListsCar();
    //适配器
    private CarLogoAdapter carLogoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(activity, R.layout.frag_search, null);
        x.view().inject(this, view);

        initView();

        return view;
    }

    private void initView() {

        for (int i = 0;i < 100;i++){
            CarLogo m;
            if (i < 10){
                m = new CarLogo("A","大众" + i);
            }else if (i < 20){
                m = new CarLogo("X" , "西瓜" + i);
            }else if (i < 30){
                m = new CarLogo("C" , "宝马" + i);
            }else if (i < 40){
                m = new CarLogo("D" , "比亚迪" + i);
            }else if (i < 50){
                m = new CarLogo("E" , "奔驰" + i);
            }else if (i < 60){
                m = new CarLogo("F" , "奥迪" + i);
            }else if (i < 70){
                m = new CarLogo("G" , "雪佛兰" + i);
            }else if (i < 80){
                m = new CarLogo("H" , "好吃的" + i);
            }else{
                m = new CarLogo("I","奔奔" + i);
            }
            datas.add(m);
        }

        //初始化适配器
        carLogoAdapter = new CarLogoAdapter(getActivity(),datas,find_brand_lv);
        find_brand_lv.setAdapter(carLogoAdapter);

        //设置列表滚动事件
        find_brand_lv.setOnScrollListener(carLogoAdapter);
        //设置列表压缩头部
        find_brand_lv.setPinnedHeaderView(LayoutInflater.from(getActivity()).
                inflate(R.layout.fragment_find_brand_listview_head, find_brand_lv,
                false));
    }

}
