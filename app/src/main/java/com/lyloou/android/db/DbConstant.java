package com.lyloou.android.db;

public class DbConstant {
	
	public static final int VERSION = 1;
	public static final int VERSION_UPDATE = 2;
	public static final String DB_TABLE = "table_lou";
	public static final String DB_TABLE_2 = "table_lou_2";
	public static final String DB_TABLE_SQL = ""
			+ "create table if not exists " + DB_TABLE
			+ "("
			+ "id integer primary key autoincrement, "
			+ "first_name text not null, "
			+ "last_name text "
			+ ")";
	public static final String DB_TABLE_SQL_2 = ""
			+ "create table if not exists " + DB_TABLE_2
			+ "("
			+ "id integer primary key autoincrement, "
			+ "first_name text not null, "
			+ "last_name text "
			+ ")";

}
