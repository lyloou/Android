package com.lyloou.android.test;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lyloou.android.R;
import com.lyloou.android.fragment.BaseFragment;

public class ActivitySingleFragmentActivityFragment extends BaseFragment {

	private static final String KEY_DATA = "key_data";
	private static final String TAG = "TestFragment";
	private String mData;

	public ActivitySingleFragmentActivityFragment(){
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initData();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_activity_singlefragmentactivity, container, false);
		initView(view);
		return view;
	}
	
	private void initData() {
		mData= getData();
		Log.i(TAG, "get data: " + mData);
	}	
	private void initView(View view) {
		TextView tvText = (TextView) view.findViewById(R.id.tv_text);
		tvText.setText(mData);
	}

	private String getData() {
		return getArguments().getString(KEY_DATA);
	}

	public static Fragment newInstance(String str){
		Fragment fragment = new ActivitySingleFragmentActivityFragment();
		Bundle bundle = new Bundle();
		bundle.putString(KEY_DATA, str);
		fragment.setArguments(bundle);
		return fragment;
	}
}
