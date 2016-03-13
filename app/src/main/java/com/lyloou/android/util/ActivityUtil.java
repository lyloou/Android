package com.lyloou.android.util;

import android.app.Activity;
import android.content.Intent;

public class ActivityUtil {
	public static final int RESULT_CODE = 1;

	public static void startActivity(Activity context, Class<?> targetActivity, boolean needResult, int enterAnim,
			int exitAnim) {
		Intent intent = new Intent(context, targetActivity);

		// 是否需要返回结果；
		if (needResult) {
			context.startActivityForResult(intent, RESULT_CODE);
		} else {
			context.startActivity(intent);
		}

		// 进入和退出动画；
		context.overridePendingTransition(enterAnim, exitAnim);
	}

	public enum AnimMode {
		FADE, SLIDE
	}

	public static void startActivityByLou(Activity context, Class<?> targetActivity, AnimMode mode) {

		int anim_in = 0;
		int anim_out = 0;
		switch (mode) {
		case FADE:
			anim_in = android.R.anim.fade_in;
			anim_out = android.R.anim.fade_out;
			break;

		case SLIDE:
			anim_in = android.R.anim.slide_in_left;
			anim_out = android.R.anim.slide_out_right;
			break;
			
		// custom
			
		}
		startActivity(context, targetActivity, false, anim_in, anim_out);
	}
}
