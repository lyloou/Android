package com.lyloou.android.util;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

public class AnimationUtil {
	public static Animation getRotateAnimation(int duration) {
		AnimationSet set = new AnimationSet(true);

		Animation anim = new RotateAnimation(0, 360,RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		anim.setDuration(duration);
		anim.setRepeatMode(Animation.RESTART);
		anim.setRepeatCount(Animation.INFINITE);
		
		set.setInterpolator(new LinearInterpolator());
		set.setRepeatMode(Animation.RESTART);
		set.setRepeatCount(Animation.INFINITE);
		set.addAnimation(anim);
		return set;
	}
}
