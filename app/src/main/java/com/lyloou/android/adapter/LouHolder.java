package com.lyloou.android.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Lou on 2016/3/23.
 */
public class LouHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    protected MARK mark;

    protected enum MARK {
        NORMAL, DELETE
    }

    private LouHolder(Context context, int layoutId) {
        mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, null);
        mConvertView.setTag(this);
    }

    public static LouHolder getInstance(Context context, int layoutId, View convertView) {
        boolean needInflate = convertView == null || ((LouHolder) convertView.getTag()).mark == MARK.DELETE;
        if (needInflate) {
            return new LouHolder(context, layoutId);
        }
        return (LouHolder) convertView.getTag();
    }

    public View getConvertView() {
        return mConvertView;
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public LouHolder putText(int viewId, String text) {
        View v = getView(viewId);
        if (v instanceof TextView) {
            ((TextView) v).setText(text);
        }
        return this;
    }

    public LouHolder putText(int viewId, int resId) {
        View v = getView(viewId);
        if (v instanceof TextView) {
            ((TextView) v).setText(resId);
        }
        return this;
    }

    public LouHolder putImg(int viewId, int resId) {
        View v = getView(viewId);
        if (v instanceof ImageView) {
            ((ImageView) v).setImageResource(resId);
        }
        return this;
    }

    public LouHolder putImg(int viewId, Bitmap bitmap) {
        View v = getView(viewId);
        if (v instanceof ImageView) {
            ((ImageView) v).setImageBitmap(bitmap);
        }
        return this;
    }

    public LouHolder putImg(int viewId, Drawable drawable) {
        View v = getView(viewId);
        if (v instanceof ImageView) {
            ((ImageView) v).setImageDrawable(drawable);
        }
        return this;
    }


}
