package com.lyloou.android.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbManager {

	private static final String TABLE_NAME = DbConstant.DB_TABLE;
	private static final int NEW_VERSION = DbConstant.VERSION;
	private DBHelper mHelper;

	public DbManager(Context context) {
		mHelper = new DBHelper(context, NEW_VERSION);
	}

	public void close() {
		mHelper.close();
	}

	public void insert(Object device) {
		SQLiteDatabase db = mHelper.getWritableDatabase();
		try {
			db.beginTransaction();
			ContentValues values = new ContentValues();
			// values.put("id", device.getId());

			db.insert(TABLE_NAME, null, values);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			db.close();
		}
	}

	public Object query(String id) {
		Object device = null;
		SQLiteDatabase db = mHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where id = ?", new String[] { id });
		while (cursor.moveToNext()) {
			device = new Object();
			break;
		}
		cursor.close();
		db.close();
		return device;
	}

	public int getCount() {
		SQLiteDatabase db = mHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
		int count = cursor.getCount();
		cursor.close();
		db.close();
		return count;
	}

	public void update(Object device) {
		SQLiteDatabase db = mHelper.getWritableDatabase();
		try {
			db.beginTransaction();
			ContentValues values = new ContentValues();
			// values.put("id", device.getId());

			db.update(TABLE_NAME, values, "id=?", new String[] { ""+device.hashCode() });
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			db.close();
		}
	}
	
}
