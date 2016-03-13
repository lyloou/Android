package com.lyloou.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;

import com.lyloou.android.R;
import com.lyloou.android.util.ScreenUtil;
import com.lyloou.android.util.ViewUtil;

public class CircleView extends View {
	private Context mContext;
	private int mPicId;
	private PorterDuff.Mode mMode;
	private Bitmap mOutBitmap;
	

	public CircleView(Context context) {
		this(context, null);
	}

	public CircleView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	

	public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;
		mMode = PorterDuff.Mode.SRC_IN;
		TypedArray ta =  context.obtainStyledAttributes(attrs, R.styleable.CircleView);
		mPicId = ta.getResourceId(R.styleable.CircleView_pic, R.mipmap.lou);
		ta.recycle();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = ViewUtil.getSizeFromMeasureSpec(widthMeasureSpec, ScreenUtil.dp2Px(mContext, 100));
		int height = ViewUtil.getSizeFromMeasureSpec(heightMeasureSpec, ScreenUtil.dp2Px(mContext, 100));
		
		mOutBitmap = ViewUtil.getBitmapByXfermode(mContext, mPicId, Color.BLACK, width, height, getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom(), mMode);
		//mOutBitmap = ViewUtil.getBitmapByXfermode(mContext, mPicId, Color.BLACK, width, height, mMode);
		setMeasuredDimension(width, height);
	}
	
	

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(mOutBitmap, 0, 0, null);
	}

}
