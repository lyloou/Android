package com.lyloou.android.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class LouAdapter extends BaseAdapter {

	private ArrayList<Object> mLists;
	private Context mContext;
	private int mItemViewId;

	/**
	 *
	 * @param context 上下文
	 * @param lists // 数据列表
	 * @param itemViewId // item 的布局；
     */
	public LouAdapter(Context context, ArrayList<Object> lists, int itemViewId) {
		mContext = context;
		mLists = lists;
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

	@SuppressLint("InflateParams")
	public View updateView(int position, View convertView) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(mItemViewId, null);
			// TODO 获取view；
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		// TODO 根据获取的view设置值；
		
		return convertView;
	}

	public void addItem(Object o) {
		mLists.add(o);
		updateChange();
	}
	
	public void deleteItem(Object o) {
		mLists.remove(o);
		updateChange();
	}

	private class ViewHolder {
	}

	public void updateChange() {
		notifyDataSetChanged();
	}

}
