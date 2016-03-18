package com.lyloou.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context, int newVersion){
		super(context, DbConstant.DB_TABLE, null, newVersion);
	}

	public DBHelper(Context context){
		this(context, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DbConstant.DB_TABLE_SQL);
		db.execSQL(DbConstant.DB_TABLE_SQL_2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		switch(newVersion){
			case 2:
				onCreate(db);
				break;
		}
	}

}
