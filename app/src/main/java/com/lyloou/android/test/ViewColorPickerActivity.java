package com.lyloou.android.test;

import com.lyloou.android.R;
import com.lyloou.android.activity.BaseActivity;
import com.lyloou.android.view.ColorPickerView2;
import com.lyloou.android.view.ColorPickerView2.OnChangeColorListener;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;

public class ViewColorPickerActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_view_color_picker_view);
		initView();
	}

	private void initView() {
		int[] colorsColor = new int[] { Color.rgb(92, 231, 254),
				Color.rgb(65, 163, 254), Color.rgb(31, 69, 254),
				Color.rgb(26, 41, 230), Color.rgb(94, 51, 255),
				Color.rgb(159, 45, 255), Color.rgb(254, 19, 148),
				Color.rgb(250, 18, 57), Color.rgb(255, 123, 22),
				Color.rgb(235, 242, 38), Color.rgb(225, 255, 43),
				Color.rgb(164, 255, 38), Color.rgb(96, 255, 38),
				Color.rgb(72, 255, 40), Color.rgb(51, 207, 38) };
		final ColorPickerView2 cpvColor = (ColorPickerView2) findViewById(R.id.cpv_color);
		cpvColor.setColors(colorsColor);
		cpvColor.setOnChangeColorListener(new OnChangeColorListener() {
			
			@Override
			public void handleColorIndex(int colorIndex) {
				// LogUtil.e("colorIndex:" + colorIndex);
			}
		});
		
		cpvColor.setCurrentIndex(3);
	}

}
