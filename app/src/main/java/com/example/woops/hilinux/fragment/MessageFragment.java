package com.example.woops.hilinux.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.woops.hilinux.R;
import com.example.woops.hilinux.util.TitleBuilder;
import com.example.woops.hilinux.util.ToastUtils;

public class MessageFragment extends BaseFragment {

    private View view;
    private TextView titlebar_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(activity, R.layout.frag_message, null);

        new TitleBuilder(view)
                .setTitleText("信息")
                .setRightImage(R.drawable.home_ico_city)
                .setRightOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast(activity, "right click~", Toast.LENGTH_SHORT);
                    }
                });
        return view;
    }
}
