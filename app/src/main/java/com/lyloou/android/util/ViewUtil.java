package com.lyloou.android.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ListView;

public class ViewUtil {
	public interface Click {
		void click();
	}

	public static void click(final View view, final Click click){
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				click.click();
			}
		});
	}
	
	/**
	 * 设置 ListView 不可滑动
	 * @param listView
	 */
	@SuppressLint("ClickableViewAccessibility")
	public static void setListViewCannotSlide(ListView listView) {
        listView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return event.getAction() == MotionEvent.ACTION_MOVE;
            }
        });
    }
	
	public static void touchViewBackground(View ... views){
		for(View view:views){
			view.setOnTouchListener(new OnTouchListener() {
				@SuppressLint("ClickableViewAccessibility")
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					Drawable background = v.getBackground();
					if(background!=null){
						switch(event.getAction() ){
						case MotionEvent.ACTION_DOWN:
							v.animate().cancel();
							background.setAlpha(200);
							break;
						case MotionEvent.ACTION_UP:
							background.setAlpha(255);
							break;
						case MotionEvent.ACTION_CANCEL:
							background.setAlpha(255);
							break;
						}
					}
					return false;
				}
			});
		}
	}
	
	public static void touchView(View ... views){
		for(View view:views){
			view.setOnTouchListener(new OnTouchListener() {
				
				@SuppressLint("ClickableViewAccessibility")
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch(event.getAction() ){
					case MotionEvent.ACTION_DOWN:
						v.animate().cancel();
						v.setAlpha(0.5f);
						break;
					case MotionEvent.ACTION_UP:
						v.setAlpha(1f);
						break;
					case MotionEvent.ACTION_CANCEL:
						v.setAlpha(1f);
					}
					return false;
				}
			});
		}
	}
	
	public static void clickIvBackground(OnClickListener listener, ImageView... views) {
		for (ImageView iv : views) {
			iv.setOnClickListener(listener);
			iv.setOnTouchListener(new OnTouchListener() {
				@SuppressLint("ClickableViewAccessibility")
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					ImageView iv = (ImageView) v;

					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						v.animate().cancel();
						iv.setAlpha(0.2f);
						iv.setBackgroundColor(0x88ffffff);
						break;
					case MotionEvent.ACTION_UP:
						iv.setBackgroundResource(0);
						iv.animate().alpha(1f).setDuration(100).start();
						break;
					case MotionEvent.ACTION_CANCEL:
						iv.setBackgroundResource(0);
						iv.animate().alpha(1f).setDuration(100).start();
						break;
					}

					return false;
				}
			});
		}
	}
	
	public static int getSizeFromMeasureSpec(int measureSpec, int defaultSize) {
		int result = 0;
		int mode = MeasureSpec.getMode(measureSpec);
		int size = MeasureSpec.getSize(measureSpec);
		if(mode == MeasureSpec.EXACTLY){
			result = size;
		} else {
			result = defaultSize;
			if(mode == MeasureSpec.AT_MOST){
				result = Math.min(defaultSize, size);
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param context
	 * @param resId the imageView id
	 * @param dstColor the color of the dst
	 * @param width the total width of view
	 * @param height the total height of view
	 * @param paddingLeft
	 * @param paddingTop
	 * @param paddingRight
	 * @param paddingBottom
	 * @param mode the mode to use
	 * @return
	 */
	public static Bitmap getBitmapByXfermode(Context context,
			int resId, 
			int dstColor,
			int width,
			int height,
			int paddingLeft,
			int paddingTop,
			int paddingRight,
			int paddingBottom,
			PorterDuff.Mode mode){
		
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
		int bWidth = bitmap.getWidth();
		int bHeight = bitmap.getHeight();
		
		// get the diameter of the output bitmap.
		int diameter = Math.min(width-(paddingLeft+paddingRight), 
				height-(paddingTop+paddingBottom));
		
		// Scale bitmap by ratio.
		float sx = (diameter*1.0f)/bWidth;
		float sy = (diameter*1.0f)/bHeight;
		Matrix matrix = new Matrix();
		matrix.setScale(sx, sy);
		
		// get the output bitmap and the canvas of the bitmap.
		Bitmap outBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(outBitmap);

		// init paint
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setDither(true);
		
		// draw dst
		RectF rectF = new RectF((width-diameter)/2, (height-diameter)/2, (width+diameter)/2, (height+diameter)/2);
		paint.setColor(dstColor);
		canvas.drawOval(rectF, paint);
		
		// set mode
		paint.setXfermode(new PorterDuffXfermode(mode));
		
		// draw src
		canvas.translate((width-diameter)/2, (height-diameter)/2); // it's important;
		canvas.drawBitmap(bitmap, matrix, paint);
		
		return outBitmap;
	}
	
	public static Bitmap getBitmapByXfermode(Context context,
			int resId, 
			int dstColor,
			int width,
			int height,
			PorterDuff.Mode mode){
		return getBitmapByXfermode(context, resId, dstColor, width, height, 2, 2, 2, 2, mode);
	}
	
	
	public static Bitmap getCircleTextBitmap(Context context,
			String text,
			int width,
			int height,
			int bgColor,
			int fontColor,
			float strokeWidth,
			float textSize){
		
		// get the output bitmap and the canvas of the bitmap.
		Bitmap outBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(outBitmap);

		// init paint
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setDither(true);
		// draw dst
		RectF rectF = new RectF(0, 0, width, height);
		paint.setColor(bgColor);
		canvas.drawOval(rectF, paint);
		
		paint.setStrokeWidth(strokeWidth);
		paint.setTextSize(textSize);
		paint.setColor(fontColor);
		FontMetricsInt pmi = paint.getFontMetricsInt();
		int baseLine = (int) ((rectF.top + rectF.bottom - pmi.bottom - pmi.top)/2);
		paint.setTextAlign(Align.CENTER);
		canvas.drawText(text, rectF.centerX(), baseLine, paint);
		
		return outBitmap;
	}
}
