package com.lyloou.android.listener;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * 
 * @author Lyloou
 * if the interval time of two clicked event is under INTERVAL_TIME, no things will happen. 
 *
 */
public abstract class OnNoDoubleClickListener implements OnClickListener {

	private static final long INTERVAL_TIME = 400;
	long lastTime = 0;
	@Override
	public void onClick(View v) {
		long nowTime = System.currentTimeMillis();
		if(nowTime-lastTime > INTERVAL_TIME){
			lastTime = nowTime;
			onNoDoubleClick();
		}
	}
	public abstract void onNoDoubleClick();	
}
