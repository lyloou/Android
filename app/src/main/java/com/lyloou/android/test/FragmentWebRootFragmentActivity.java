package com.lyloou.android.test;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Window;

import com.lyloou.android.activity.SingleFragmentActivity;
import com.lyloou.android.constants.LouConstants;
import com.lyloou.android.fragment.WebRootFragment;

public class FragmentWebRootFragmentActivity extends SingleFragmentActivity {

	private static final String KEY_URL = "key_url";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public Fragment createFragment() {

		String url = getIntent().getStringExtra(KEY_URL);
		if (url == null) {
			url = LouConstants.URL_LYLOOU_CSDN;
		}
		WebRootFragment fragment = WebRootFragment.newInstance(url);
		return fragment;
	}
}
