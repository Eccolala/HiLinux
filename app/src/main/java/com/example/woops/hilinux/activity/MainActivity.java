package com.example.woops.hilinux.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.woops.hilinux.R;
import com.example.woops.hilinux.fragment.MyFragmentController;
import com.example.woops.hilinux.util.ToastUtils;


public class MainActivity extends FragmentActivity implements
        RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private RadioGroup rg_tab;
    private ImageView iv_add;
    private MyFragmentController controller;
    private long firstTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        controller = MyFragmentController.getInstance(this, R.id.fl_content);
        controller.showFragment(0);

        initView();
    }


    private void initView() {
        rg_tab = (RadioGroup) findViewById(R.id.rg_tab);
        iv_add = (ImageView) findViewById(R.id.iv_add);

        rg_tab.setOnCheckedChangeListener(this);
        iv_add.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                controller.showFragment(0);
                break;
            case R.id.rb_meassage:
                controller.showFragment(1);
                break;
            case R.id.rb_search:
                controller.showFragment(2);
                break;
            case R.id.rb_user:
                controller.showFragment(3);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                ToastUtils.showToast(this, "add", Toast.LENGTH_SHORT);
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();

        if (secondTime - firstTime > 1000){
            ToastUtils.showToast(this,"再按一次退出客户端",2000);
            firstTime = secondTime;
        }else {
            finish();
        }
    }
}


