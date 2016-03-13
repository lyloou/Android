package com.lyloou.android.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;

public class AnimatorUtil {

	public static AnimatorSet animatorTranslation(Object target, float toXDelta, 
			float toYDelta, int duration, int delay) {

		AnimatorSet set = new AnimatorSet();
		ObjectAnimator oa1 = ObjectAnimator.ofFloat(target, "translationX", toXDelta);
		ObjectAnimator oa2 = ObjectAnimator.ofFloat(target, "translationY", toYDelta);
		oa1.setDuration(duration);
		oa2.setDuration(duration);
		set.setDuration(duration);
		set.playTogether(oa1, oa2);
		set.setStartDelay(delay);
		return set;
	}

	public static AnimatorSet animatorTranslationX(Object target, float toXDelta, int duration) {
		return animatorTranslation(target, toXDelta, 0, duration, 0);
	}

	public static AnimatorSet animatorTranslationY(Object target, float toYDelta, int duration) {
		return animatorTranslation(target, 0, toYDelta, duration, 0);
	}
	
	public static AnimatorSet animatorRotate(Object target,int duration,  float ... degrees){
		AnimatorSet set = new AnimatorSet();
		ObjectAnimator oa1 = ObjectAnimator.ofFloat(target, "rotation", degrees);
		oa1.setDuration(duration);
		oa1.setRepeatCount(ObjectAnimator.INFINITE);
		oa1.setRepeatMode(ObjectAnimator.RESTART);
		set.setDuration(duration);
		set.setInterpolator(new LinearInterpolator());
		set.playTogether(oa1);
		return set;
	}
}
