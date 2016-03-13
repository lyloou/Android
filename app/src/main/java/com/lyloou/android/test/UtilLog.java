package com.lyloou.android.test;

import com.lyloou.android.util.LogUtil;

import android.test.AndroidTestCase;

public class UtilLog extends AndroidTestCase {

	public void testLog(){
		LogUtil.d("my------------");
		LogUtil.i("my------------");
		LogUtil.w("my------------");
		LogUtil.v("my------------");
		LogUtil.e("my------------");
	}
}
