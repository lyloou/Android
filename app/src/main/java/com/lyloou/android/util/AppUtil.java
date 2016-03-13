package com.lyloou.android.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class AppUtil {

	/**
	 * get app version name
	 * @param context
	 * @return the version name
	 */
	public static String getAppVersionName(Context context){
		PackageManager pm = context.getPackageManager();
		String versionName="";
		try {
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		
		return versionName;
	}
	
	/**
	 * get app version code
	 * @param context
	 * @return the version code
	 */
	public static int getAppVersionCode(Context context){
		PackageManager pm = context.getPackageManager();
		int versionCode = 0;
		try {
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionCode = pi.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		
		return versionCode;
	}
	
	/**
	 * get sdk version
	 * @return the current os's sdk version
	 */
	public static String getSdkVersion(){
		return android.os.Build.VERSION.RELEASE;
	}
}
