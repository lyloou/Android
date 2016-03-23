package com.lyloou.android.util;

import android.util.Log;

import com.lyloou.android.app.LouApp;

public class Ulog {

	private static final boolean DEBUG_MODE = true;
	//private static final boolean DEBUG_MODE = LouApp.DEBUG; // 自动根据环境判断是否打印日志；
	private static final String TAG = "Lou";
	
	public static void d(String tag, String msg){if(DEBUG_MODE){Log.d(tag, msg);}}
	public static void e(String tag, String msg){if(DEBUG_MODE){Log.e(tag, msg);}}
	public static void i(String tag, String msg){if(DEBUG_MODE){Log.i(tag, msg);}}
	public static void v(String tag, String msg){if(DEBUG_MODE){Log.v(tag, msg);}}
	public static void w(String tag, String msg){if(DEBUG_MODE){Log.w(tag, msg);}}
	
	public static void d(String msg){ d(TAG,msg);}
	public static void e(String msg){ e(TAG,msg);}
	public static void i(String msg){ i(TAG,msg);}
	public static void v(String msg){ v(TAG,msg);}
	public static void w(String msg){ w(TAG,msg);}
}
                                                  