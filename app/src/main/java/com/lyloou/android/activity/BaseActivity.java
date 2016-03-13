package com.lyloou.android.activity;

import android.app.Activity;
import android.os.Bundle;

import com.lyloou.android.app.LouApp;
import com.lyloou.android.util.LogUtil;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtil.i("----> "+this.getClass().getSimpleName());
        LouApp.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LouApp.removeActivity(this);
    }
}
