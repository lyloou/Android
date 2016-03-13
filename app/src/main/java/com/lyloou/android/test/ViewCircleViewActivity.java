package com.lyloou.android.test;

import android.animation.Animator;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lyloou.android.R;
import com.lyloou.android.activity.BaseActivity;
import com.lyloou.android.animation.Rotate3dAnimation;
import com.lyloou.android.util.AnimatorUtil;
import com.lyloou.android.util.ScreenUtil;
import com.lyloou.android.util.ViewUtil;
import com.lyloou.android.view.CircleView;

public class ViewCircleViewActivity extends BaseActivity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		initView();
	}
	
	private void touchView(View ...views){
		for(View view : views){
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					anim(v);
				}
			});
		}
	}
	
	private void anim(View ...vs) {
		for(View v : vs){
			if(v.getAnimation()!=null){
				v.animate().cancel();
				v.setAnimation(null);
			} else {
				if(v instanceof ImageView){
					initBitmap(80, 40, (ImageView)v);
				}
				int t = v.getWidth()/2;
				Animation animation = new Rotate3dAnimation(0, 360, t, 0, 0, true);
				animation.setDuration(5000);
				animation.setRepeatMode(Animation.RESTART);
				animation.setRepeatCount(Animation.INFINITE);
				animation.setInterpolator(new LinearInterpolator());
				v.startAnimation(animation);
			}
		}

		
	}
	private void initView() {
		setContentView(R.layout.activity_view_circle_view);
		LinearLayout llytLou = (LinearLayout) findViewById(R.id.llyt_lou);
		llytLou.setGravity(Gravity.CENTER);
		
		ImageView ivL = new ImageView(this);
		ImageView ivO = new ImageView(this);
		ImageView ivU = new ImageView(this);
		ivL.setTag("L");
		ivO.setTag("O");
		ivU.setTag("U");
		
		initBitmap(80, 40, ivL, ivO, ivU);
		addViewToLlyt(llytLou, ivL, ivO, ivU);
		
		CircleView cv1 = (CircleView) findViewById(R.id.cv_1);
		CircleView cv2 = (CircleView) findViewById(R.id.cv_2);
		cv2.setOnClickListener(this);
		
		touchView(ivL, ivO, ivU, cv1);
		
		findViewById(R.id.iv_4).setOnClickListener(this);
		findViewById(R.id.iv_5).setOnClickListener(this);
	}
	
	private void addViewToLlyt(LinearLayout llytLou, View ...views) {
		for(View view : views){
			llytLou.addView(view);
		}
	}

	private void initBitmap(int width, int size, ImageView ...ivs){
		for(ImageView iv:ivs){
			String tag = (String) iv.getTag();
			int bgColor = (int) (Color.GRAY * Math.random());
			int fontColor = (int) (Color.BLACK * Math.random());
			Bitmap b = ViewUtil.getCircleTextBitmap(ViewCircleViewActivity.this, tag, 
					ScreenUtil.dp2Px(this, width), ScreenUtil.dp2Px(this, width), 
					bgColor, fontColor, 8, ScreenUtil.sp2Px(this, size));
			iv.setImageBitmap(b);
		}
	}
	
	Animator mRotateAnimator;

	@Override
	public void onClick(View v) {
		int color = 0;
		switch(v.getId()){
		case R.id.cv_2:
			if(mRotateAnimator == null){
				mRotateAnimator = AnimatorUtil.animatorRotate(v, 25000, 360);
				mRotateAnimator.start();
			} else {
				if(mRotateAnimator.isPaused()){
					mRotateAnimator.resume();
				} else {
					mRotateAnimator.pause();
				}
			}
			break;
		case R.id.iv_4:
			color = (int) (Color.BLUE * Math.random());
			((ImageView)v).setImageBitmap(
					ViewUtil.getBitmapByXfermode(ViewCircleViewActivity.this, R.mipmap.ic_launcher, color, v.getWidth(), v.getHeight(), PorterDuff.Mode.DST_OUT));
			break;
		case R.id.iv_5:
			color = (int) (Color.BLUE * Math.random());
			((ImageView)v).setImageBitmap(
					ViewUtil.getBitmapByXfermode(ViewCircleViewActivity.this, R.mipmap.ic_launcher, color, v.getWidth(), v.getHeight(), PorterDuff.Mode.DST_IN));
			break;
		}
	}
	
}
