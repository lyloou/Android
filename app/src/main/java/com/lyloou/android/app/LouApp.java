package com.lyloou.android.app;

import android.app.Activity;
import android.app.Application;

import com.lyloou.android.util.ToolUtil;

import java.util.ArrayList;

public class LouApp extends Application {
    public static boolean DEBUG;

    // 用来保存栈中的 activity;
    private static ArrayList<Activity> sActivities = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        // 根据 context 判断debugable
        DEBUG = ToolUtil.isApkDebugable(this);
        super.onCreate();
    }

    public static void addActivity(Activity activity) {
        sActivities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        if (sActivities.contains(activity)) {
            sActivities.remove(activity);
        }
    }

    public static void finishAll() {
        for (Activity activity : sActivities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}