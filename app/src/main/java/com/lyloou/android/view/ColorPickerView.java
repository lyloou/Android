package com.lyloou.android.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

import com.lyloou.android.util.ScreenUtil;
import com.lyloou.android.util.ViewUtil;

/**
 * 
 * @author Lyloou
 * 
 * how use it?
 * [xml]
 * <com.lyloou.android.view.ColorPickerView
 *              android:id="@+id/cpv_color"
 *              android:layout_width="match_parent"
 *              android:layout_height="32dp"
 *              android:layout_marginEnd="16dp"
 *              android:layout_marginStart="16dp"
 *              android:paddingEnd="16dp"
 *              android:paddingStart="16dp" />
 *
 *
 * [activity]
 * int[] colorsColor = new int[] { Color.rgb(92, 231, 254), Color.rgb(65, 163, 254), Color.rgb(31, 69, 254),
 * 			Color.rgb(26, 41, 230), Color.rgb(94, 51, 255), Color.rgb(159, 45, 255), Color.rgb(254, 19, 148),
 * 			Color.rgb(250, 18, 57), Color.rgb(255, 123, 22), Color.rgb(235, 242, 38), Color.rgb(225, 255, 43),
 *			Color.rgb(164, 255, 38), Color.rgb(96, 255, 38), Color.rgb(72, 255, 40), Color.rgb(51, 207, 38) };
 * ColorPickerView mCpvColor;
 * cpvColor = (ColorPickerView) findViewById(R.id.cpv_color);
 * cpvColor.setColors(colorsColor);
 * cpvColor.setCurrentIndex(3);
 * 
 * cpvColor.setOnChangeColorListener(new OnChangeColorListener() {
 *			@Override
 *			public void handleColorIndex(int colorIndex) {
 *				LogUtil.e("colorIndex:" + colorIndex);
 *			}
 *		});
 */
public class ColorPickerView extends View {
	private Paint mPaint;
	private RectF mRectF;
	private int mWidth ;
	private int mHeight ;
	private float mCenterX;
	private int[] mColors;
	private static final float RADIUS = 20;
	private OnChangeColorListener mListener;
	
	public ColorPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initData();
	}

	public ColorPickerView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ColorPickerView(Context context) {
		this(context, null);
	}
	
	private void initData() {
		 mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		 mRectF = new RectF();
		 mColors = getAlphaColArray(Color.rgb(0, 0, 0), 15);
		 setCurrentIndex(3);
	}
	
	public void setOnChangeColorListener(OnChangeColorListener listener){
		mListener = listener;
	}
	
	public interface OnChangeColorListener{
		void handleColorIndex(int colorIndex);
	}
	
	public int[] getColors() {
		return mColors;
	}


	public void setColors(int[] colors) {
		mColors = colors;
		postInvalidate();
	}

	public int getCurrentIndex(){
		int result = (int)(mCenterX/(getWidth()-getPaddingStart()-getPaddingEnd())*mColors.length);
		if(result >= mColors.length){
			result = mColors.length-1;
		} else if(result <=0){
			result = 0;
		}
		return result;
	}
	
	public void setCurrentIndex(final int index){
		// make sure the getWidth() method is available.
		post(new Runnable() {
			@Override
			public void run() {
				float w3 = (1.0f*getWidth()-getPaddingStart()-getPaddingEnd())/mColors.length;
				float centerX = index * w3;
				setCenterX(centerX);
			}
		});
	}
	
	@Override
	public boolean performClick() {
		return super.performClick();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float cx = event.getX();
		setCenterX(cx);
		if(mListener!=null){
			mListener.handleColorIndex(getCurrentIndex());
		}
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			return true;
		case MotionEvent.ACTION_MOVE:
			return true;
		case MotionEvent.ACTION_UP:
			performClick();
			break;
		}
		return super.onTouchEvent(event);
	}
	
	private void setCenterX(float centerX) {
		int startX = getPaddingStart();
		int endX = getWidth()-getPaddingEnd();
		if(centerX<=startX){
			mCenterX = startX;
		} else if(centerX>=endX){
			mCenterX = endX;
		} else {
			mCenterX = centerX;
		}
		postInvalidate();
	}

	
	// 事件拦截，防止滑动冲突，与ScrollView等可滑动控件之间；
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		ViewParent parent = getParent();
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			parent.requestDisallowInterceptTouchEvent(true);
			break;
		case MotionEvent.ACTION_UP:
			parent.requestDisallowInterceptTouchEvent(false);
			break;
		default:
			break;
		}
		
		return super.dispatchTouchEvent(event);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		mWidth = ViewUtil.getSizeFromMeasureSpec(widthMeasureSpec, ScreenUtil.dp2Px(getContext(), 200));
		mHeight = ViewUtil.getSizeFromMeasureSpec(heightMeasureSpec, ScreenUtil.dp2Px(getContext(), 32));
		setMeasuredDimension(mWidth, mHeight);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// 总宽度
		mWidth = getWidth();
		// 总高度
		mHeight = getHeight();
		int paddingStart = getPaddingStart();
		int paddingEnd = getPaddingEnd();
		
		
		// 每一小格的宽度
		float w3 = (1.0f*mWidth-paddingStart-paddingEnd)/mColors.length;
		// 每一小格的高度
		float h3 = (1.0f*mHeight)/3;
		
		// 画白色背景
		mRectF.set(paddingStart, h3, mWidth-paddingEnd, h3*2);
		mPaint.setColor(Color.WHITE);
		canvas.drawRoundRect(mRectF, RADIUS, RADIUS, mPaint);
		
		// 画小格
		drawAllRect(canvas, mColors, w3, h3);
		
		// 画取色小球
		drawBall(canvas);
		
		super.onDraw(canvas);
	}
	
	public static int[] getAlphaColArray(int currentCol, int len) {
		int[] cols = new int [len];
		for(int i=0; i<len; i++){
			int alpha = 255-(int)(255f * i/len); 
			cols[i] = Color.argb(alpha, Color.red(currentCol), Color.green(currentCol), Color.blue(currentCol));
		}
		return cols; 
	}
	
	private void drawBall(Canvas canvas) {
		float cy = mHeight/2.0f;
		float rOut = mHeight/2.0f;
		float rIn = mHeight/2.4f;
		
		int color = mColors[getCurrentIndex()];
		mPaint.setColor(Color.WHITE);
		canvas.drawCircle(mCenterX, cy, rOut, mPaint);
		mPaint.setColor(color);
		canvas.drawCircle(mCenterX, cy, rIn, mPaint);
	}


	private void drawAllRect(Canvas canvas, int[] colors, float w3, float h3) {
		
		for(int i=0; i<colors.length; i++){
			mRectF.set(i*w3+getPaddingStart(), h3, (i+1)*w3+getPaddingStart(), h3*2);
			mPaint.setColor(colors[i]);
			if(i==0){
				// 画圆角的思路：先画一个圆角矩形，再画一次右半部分的矩形
				mRectF.set(getPaddingStart(), h3, w3+getPaddingStart(), h3*2);
				canvas.drawRoundRect(mRectF, RADIUS, RADIUS, mPaint);
				mRectF.set(getPaddingStart()+w3/2, h3, w3+getPaddingStart(), h3*2);
				canvas.drawRoundRect(mRectF, 0, 0, mPaint);
			} else if(i==colors.length-1){
				int color = colors[i];
				mPaint.setColor(color);
				mRectF.set(i*w3+getPaddingStart(), h3, (i+1)*w3+getPaddingStart(), h3*2);
				canvas.drawRoundRect(mRectF, RADIUS, RADIUS, mPaint);
				// 下面这样做目的是防止颜色叠加，导致显示效果与预期不一样；
				// 解决方法是先画一次背景，再画一次矩形
				mPaint.setColor(Color.WHITE);
				mRectF.set(i*w3+getPaddingStart(), h3, (i+1)*w3+getPaddingStart()-w3/2, h3*2);
				canvas.drawRoundRect(mRectF, 0, 0, mPaint);
				
				mPaint.setColor(color);
				canvas.drawRoundRect(mRectF, 0, 0, mPaint);
			} else {
				canvas.drawRoundRect(mRectF, 0, 0, mPaint);
			}
		}
		
	}

	
}
