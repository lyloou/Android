package com.lyloou.android.test;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Window;

import com.lyloou.android.activity.SingleFragmentActivity;

public class ActivitySingleFragmentActivityActivity extends SingleFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		initFeature();
		super.onCreate(savedInstanceState);
	}

	/**
	 * 该方法要在 setContentView() 之前调用
	 */
	private void initFeature() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	@Override
	public Fragment createFragment() {
		String data = "How are you ?";
		return ActivitySingleFragmentActivityFragment.newInstance(data);
	}
}
