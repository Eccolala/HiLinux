package com.example.woops.hilinux.activity;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.example.woops.hilinux.BaseApplication;
import com.example.woops.hilinux.constants.CommonConstants;
import com.example.woops.hilinux.util.Logger;
import com.example.woops.hilinux.util.ToastUtils;



public abstract class BaseActivity extends Activity {

    protected String TAG;

    protected BaseApplication application;
    protected SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TAG = this.getClass().getSimpleName();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        application = (BaseApplication) getApplication();
        sp = getSharedPreferences(CommonConstants.SP_NAME, MODE_PRIVATE);
    }

    protected void intent2Activity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(this, tarActivity);
        startActivity(intent);
    }

    protected void showToast(String msg) {
        ToastUtils.showToast(this, msg, Toast.LENGTH_SHORT);
    }

    protected void showLog(String msg) {
        Logger.show(TAG, msg);
    }

}
