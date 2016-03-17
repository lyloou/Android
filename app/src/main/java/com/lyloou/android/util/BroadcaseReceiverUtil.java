package com.lyloou.android.util;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class BroadcaseReceiverUtil {
	
	
	public static void register(Context context, BroadcastReceiver receiver, IntentFilter filter){
		context.registerReceiver(receiver, filter);
	}
	
	public static void unregister(Context context, BroadcastReceiver receiver){
		context.unregisterReceiver(receiver);
	}
	
	
	
	private Context mContext;
	private BroadcaseReceiverUtil(Context context){
		mContext = context;
	}
	
	// ~~~ 监听蓝牙状态变化
	/************************** start ********************************/
	public static BroadcaseReceiverUtil getInstance(Context context){
		return new BroadcaseReceiverUtil(context);
	}
	public void registBluetoothStatusReceiver(){
		register(mContext, mBluetoothReceiver, makeBluetoothStatusFilter());
	}
	public void unregistBluetoothStatusReceiver(){
		unregister(mContext, mBluetoothReceiver);
	}
	
	private IntentFilter makeBluetoothStatusFilter() {
		IntentFilter filter = new IntentFilter();
		filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
		return filter;
	}
	
	private BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			switch(intent.getAction()){
			case BluetoothAdapter.ACTION_STATE_CHANGED:
				int blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
				switch(blueState){
				case BluetoothAdapter.STATE_TURNING_ON:
					Ulog.i("mBluetoothReceiver --> STATE_TURNING_ON");
					break;
				case BluetoothAdapter.STATE_ON:
					Ulog.i("mBluetoothReceiver --> STATE_ON");
					break;
				case BluetoothAdapter.STATE_TURNING_OFF:
					Ulog.i("mBluetoothReceiver --> STATE_TURNING_OFF");
					break;
				case BluetoothAdapter.STATE_OFF:
					Ulog.i("mBluetoothReceiver --> STATE_OFF");
					break;
				}
				break;
			}
		}
	};
	/************************** end ********************************/
	
}
