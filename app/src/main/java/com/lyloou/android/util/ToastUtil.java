package com.lyloou.android.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

	public static void show(Context context, CharSequence text) {
		show(context, text, 0);
	}

	public static void show(Context context, int resId) {
		show(context, resId, 0);
	}

	public static void show(Context context, CharSequence text, int mode) {
		switch (mode) {
		case 0:
			Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
			break;
		case 1:
			Toast.makeText(context, text, Toast.LENGTH_LONG).show();
			break;
		}
	}

	public static void show(Context context, int resId, int mode) {
		switch (mode) {
		case 0:
			Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
			break;
		case 1:
			Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
			break;
		}
	}

}
