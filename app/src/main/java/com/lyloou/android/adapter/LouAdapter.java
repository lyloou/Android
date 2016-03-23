package com.lyloou.android.adapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lou on 2016/3/23.
 */

/**
 * 特色功能：
 * 1. 删除伴随着动画（不突兀）；
 * 2. 添加元素：
 */
public abstract class LouAdapter<T> extends BaseAdapter {

	private ArrayList<T> mLists;
	private Context mContext;
	private AbsListView mListView;
	private int mLayoutId;

	public LouAdapter(Context context, AbsListView listView, int layoutId) {
		mContext = context;
		mListView = listView;
		mLayoutId = layoutId;
		mLists = new ArrayList<T>();
	}

	@Override
	public int getCount() {
		return mLists.size();
	}

	@Override
	public T getItem(int position) {
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

	public AbsListView getBindListView() {
		return mListView;
	}

	/**
	 * contain 来表示是否已经包含元素；（可能需要重写，如果允许重复的话，就不必要重写了）；
	 *
	 * @param o
	 * @return
	 */
	protected boolean contain(T o) {
		if (mLists.contains(o)) {
			return true;
		}
		return false;
	}

	/**
	 * 用数据来更新视图；
	 *
	 * @param position
	 * @param convertView
	 * @return
	 */
	private View updateView(int position, View convertView) {
		LouHolder holder = LouHolder.getInstance(mContext, mLayoutId, convertView);
		assign(holder, getItem(position));
		return holder.getConvertView();
	}

	/**
	 * 当更改了某一个Item之后，可以通过updateView(position);的方式只更新这一个Item；
	 *
	 * @param position
	 */
	public void updateView(int position) {
		View convertView = getIndexView(position);
		updateView(position, convertView);
	}

	/**
	 * @param holder
	 * @param t
	 */
	protected abstract void assign(LouHolder holder, T t);


	/**
	 * 获取可见元素的View；
	 *
	 * @param position
	 * @return
	 */
	public View getIndexView(int position) {
		int currentIndex = position - mListView.getFirstVisiblePosition();
		if (currentIndex < 0 || currentIndex >= mLists.size()) {
			return null;
		}
		return mListView.getChildAt(currentIndex);
	}


	public void addItem(T o, boolean filter) {
		if (filter && contain(o)) {
			return;
		}
		mLists.add(o);
		updateChange();
	}

	/**
	 * 默认过滤添加的元素；
	 *
	 * @param o
	 */
	public void addItem(T o) {
		addItem(o, true);
	}

	public List<T> getList() {
		return mLists;
	}

	/**
	 * 初始化元素
	 *
	 * @param list
	 */
	public void initList(List<T> list) {
		mLists.clear();
		mLists.addAll(list);
		updateChange();
	}

	public void deleteItem(T o) {
		mLists.remove(o);
		updateChange();
	}

	public void deleteItem(int position) {
		mLists.remove(position);
		updateChange();
	}

	/**
	 * 高度变为0后删除元素；
	 *
	 * @param position
	 */
	public void deleteItemWithAnim(final int position) {
		final View view = getIndexView(position);
		final int initHeight = view.getMeasuredHeight();
		Animation anim = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime,
											   Transformation t) {
				if (mLists.size() > 0 && interpolatedTime == 1) {
					// 高度为0时删除元素，并更新 adapter
					if (view.getTag() instanceof LouHolder) {
						((LouHolder) view.getTag()).mark = LouHolder.MARK.DELETE;
						deleteItem(position);
					}
				} else {
					// 不断的改变高度，直到高度为0；
					view.getLayoutParams().height = initHeight
							- (int) (initHeight * interpolatedTime);
					view.requestLayout();
				}
			}
		};
		anim.setDuration(300);
		view.startAnimation(anim);
	}


	public void updateChange() {
		notifyDataSetChanged();
	}

	public void clear() {
		mLists.clear();
		updateChange();
	}


}
