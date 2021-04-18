package com.psd.sqllitedatabaseexp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
    private static  final String dbname="mydb";
    private static  final int version=1;

    public MyHelper(Context context) {
        super(context, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="CREATE TABLE PRDUCTS (_id INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,DESCRIPTION,PRICE REAL)";
        db.execSQL(sql);
        //insertdata
        insertData("Jam","Fruit Jam",300.50,db);
        insertData("Bread","Brown bread",25.00,db);
        insertData("Corn","Flakes",380.50,db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void insertData(String name,String description,double price,SQLiteDatabase database){
        ContentValues values = new ContentValues();
        values.put("NAME",name);
        values.put("DESCRIPTION",description);
        values.put("PRICE",price);
        database.insert("PRDUCTS",null,values);

    }
}
