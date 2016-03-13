package com.lyloou.android.test;

import android.os.Bundle;
import android.view.Window;

import com.lyloou.android.R;
import com.lyloou.android.activity.BaseActivity;
import com.lyloou.android.view.NumView;

public class ViewNumActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		initView();
	}

	private void initView() {
		setContentView(R.layout.activity_view_num_view);
		NumView nv = (NumView) findViewById(R.id.nv);
		nv.init(0, 30, 40, true);
	}

}
