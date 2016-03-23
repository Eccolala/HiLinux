package com.example.woops.hilinux.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.woops.hilinux.BaseFragment;
import com.example.woops.hilinux.R;

public class SearchFragment extends BaseFragment {

    private View view;
    private TextView titlebar_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(activity, R.layout.frag_search, null);
        return view;
    }
}
