package com.lyloou.android.util;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class ListenerUtil {
	public static void registClickListener(OnClickListener listener, View... views) {
		if (listener == null || views == null) {
			return;
		}
		for (View view : views) {
			view.setOnClickListener(listener);
		}
	}

	public static void registLongClickListener(OnLongClickListener listener, View... views) {
		if (listener == null || views == null) {
			return;
		}
		for (View view : views) {
			view.setOnLongClickListener(listener);
		}
	}

}
