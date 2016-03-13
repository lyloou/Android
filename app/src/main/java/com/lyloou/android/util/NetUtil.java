package com.lyloou.android.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil {
	public static boolean netIsAvailable(Context context){
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		return (networkInfo != null && networkInfo.isAvailable());
	}
}
