package com.lyloou.android.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

public class LocalBroadcastReceiverUtil {
	LocalBroadcastManager mLocalBroadcastManager;
	ReceiverListener mListener;

	public LocalBroadcastReceiverUtil(Context context, ReceiverListener receiverListener) {
		mLocalBroadcastManager = LocalBroadcastManager.getInstance(context);
		mListener = receiverListener;
	}

	interface ReceiverListener {

		IntentFilter makeFilter();

		void louReceive(Context context, Intent intent);
	}

	private BroadcastReceiver mLouReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			mListener.louReceive(context, intent);
		}
	};

	public void regist() {
		mLocalBroadcastManager.registerReceiver(mLouReceiver, mListener.makeFilter());
	}

	public void unregist() {
		mLocalBroadcastManager.unregisterReceiver(mLouReceiver);
	}

	//////////// 帮助类  ////////////////////////////////
	public static void send(Context context, Intent intent) {
		LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
	}

}
