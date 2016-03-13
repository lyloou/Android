package com.lyloou.android.test;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;

import com.lyloou.android.R;
import com.lyloou.android.activity.BaseActivity;
import com.lyloou.android.view.HistogramView;
import com.lyloou.android.view.HistogramView.Bar;

import java.util.ArrayList;

public class ViewHistogramActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_view_histogram_view);
		initView();
	}

	private void initView() {
		HistogramView histogram = (HistogramView) findViewById(R.id.histogram_view_01);
	    ArrayList<Bar> barLists = new ArrayList<Bar>();
	    for(int i=0; i<15; i++){
	        float ratio = (float) Math.random();
	        int color = (int) (Color.GRAY * ratio);
	        Bar bar = histogram.new Bar(i, ratio, color, "", "");
	        barLists.add(bar);
	    }
	    histogram.setBarLists(barLists);
	    
	    
	    HistogramView histogram2 = (HistogramView) findViewById(R.id.histogram_view_02);
	    ArrayList<Bar> barLists2 = new ArrayList<Bar>();
	    for(int i=0; i<25; i++){
	        float ratio = (float) Math.random();
	        int color = (int) (Color.GRAY * ratio);
	        Bar bar = histogram.new Bar(i, ratio, color, "", "");
	        barLists2.add(bar);
	    }
	    histogram2.setBarLists(barLists2);
	    
	    
	    HistogramView histogram3 = (HistogramView) findViewById(R.id.histogram_view_03);
	    ArrayList<Bar> barLists3 = new ArrayList<Bar>();
	    for(int i=0; i<20; i++){
	        float ratio = (float) Math.random();
	        int color = Color.GRAY ;
	        Bar bar = histogram.new Bar(i, ratio, color, "", "");
	        barLists3.add(bar);
	    }
	    histogram3.setBarLists(barLists3);
	}

}
