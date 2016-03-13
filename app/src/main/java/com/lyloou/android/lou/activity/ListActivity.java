package com.lyloou.android.lou.activity;


import android.app.Fragment;

import com.lyloou.android.R;
import com.lyloou.android.activity.SingleFragmentActivity;
import com.lyloou.android.lou.fragment.ListFragment;

public class ListActivity extends SingleFragmentActivity {

	@Override
	public Fragment createFragment() {
		String[] datas = getIntent().getStringArrayExtra(ListFragment.DATA_LEVEL);
		if(datas == null){
			// default get home datas;
			datas = getResources().getStringArray(R.array.home);
		}
		return ListFragment.newInstance(datas);
	}

}
