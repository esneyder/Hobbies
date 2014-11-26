package com.example.hobbies;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class HobbiesSQLite extends SQLiteOpenHelper {
	String sqlCreate="create table tblHobbies(idHobbie INTEGER,hobbie TEXT)";
	public HobbiesSQLite(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sqlCreate);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS TABLE tblHobbies");
		db.execSQL(sqlCreate);
	}

}
