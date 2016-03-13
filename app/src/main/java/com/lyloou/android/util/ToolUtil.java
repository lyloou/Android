package com.lyloou.android.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class ToolUtil {
    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
        }
        return false;
    }
    public static boolean isApkDebugable(Context context, String packageName) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            ApplicationInfo info = packageInfo.applicationInfo;
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
        }
        return false;
    }
}