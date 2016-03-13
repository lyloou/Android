package com.lyloou.android.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

public class ResUtil {

	public static String getStr(Context context, int resId){
		return context.getResources().getString(resId);
	}
	
	public static int getCol(Context context, int resId){
		return ContextCompat.getColor(context, resId);
	}
	
	public static ColorStateList getColStateList(Context context, int resId){
		return ContextCompat.getColorStateList(context, resId);
	}
	
	public static Drawable getDrawable(Context context, int resId){
		return ContextCompat.getDrawable(context, resId);
	}
}
