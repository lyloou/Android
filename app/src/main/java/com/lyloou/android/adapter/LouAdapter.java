package com.lyloou.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LouAdapter extends BaseAdapter {

    private ArrayList<Object> mLists;
    private Context mContext;
    private int mItemViewId;

    /**
     * @param context    上下文
     * @param itemViewId // item 的布局；
     */
    public LouAdapter(Context context, int itemViewId) {
        mContext = context;
        mLists = new ArrayList<Object>();
        mItemViewId = itemViewId;
    }

    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public Object getItem(int position) {
        return mLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return updateView(position, convertView);
    }

    private View updateView(int position, View convertView) {
        //TODO 根据position和mLists获取对象

        ViewHolder holder = null;
        boolean needInflate = (convertView == null)
                || (((ViewHolder) convertView.getTag()).status == Status.DELETE);
        if (needInflate) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(mItemViewId, null);
            // TODO 根据convertView找到对应view控件；

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // TODO 根据获取到的对象给view控件初始化；

        return convertView;
    }

    private enum Status {
        NORMAL, ADD, DELETE
    }

    private class ViewHolder {
        // TODO item view 中的元素；

        // 用来标识的
        Status status;
    }

    // ------------ 对外提供的接口
    public void updateItem(int position, View view) {
        updateView(position, view);
    }

    public void updateItem(int position, ListView listView) {
        updateView(position, listView.getChildAt(position));
    }

    public void addItem(Object o, int position) {
        if(position <0){
            position = 0;
        } else if(position > mLists.size()){
            position = mLists.size();
        }
        mLists.add(position,o);
        updateChange();
    }

    public void addItem(Object o) {
        addItem(o, 0);
    }

    public void addItemToFirst(Object o) {
        addItem(o, 0);
    }

    public void addItemToLast(Object o) {
        addItem(o, mLists.size());
    }

    public void deleteItem(Object o) {
        mLists.remove(o);
        updateChange();
    }

    public void deleteItem(int position) {
        deleteItem(mLists.get(position));
    }

    /**
     * @param position 删除的元素所在位置
     * @param listView  删除的对象所属ListView
     */
    public void deleteItemWithAnim(final int position, ListView listView) {
        // 注意不能直接使用 getChildAt(position);
        // 原因[ListView getChildAt returning null for visible children](http://stackoverflow.com/questions/6766625/listview-getchildat-returning-null-for-visible-children);
        final View view = listView.getChildAt(position - listView.getFirstVisiblePosition());
        final int initHeight = view.getMeasuredHeight();
        Animation anim = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime,
                                               Transformation t) {
                if (mLists.size() > 0 && interpolatedTime == 1) {
                    // 高度为0时删除元素，并更新 adapter
                    if (view.getTag() instanceof ViewHolder) {
                        ((ViewHolder) view.getTag()).status = Status.DELETE;
                        mLists.remove(position);
                        notifyDataSetChanged();
                    }
                } else {
                    // 不断的改变高度，直到高度为0；
                    view.getLayoutParams().height = initHeight
                            - (int) (initHeight * interpolatedTime);
                    view.requestLayout();
                }
            }
        };
        anim.setDuration(320);
        view.startAnimation(anim);
    }

    public void updateChange() {
        notifyDataSetChanged();
    }
    // ~~~~~~~~~


}
