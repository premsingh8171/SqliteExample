package com.psd.sqllitedatabaseexp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.psd.sqllitedatabaseexp.db.MyHelper;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyHelper helper = new MyHelper(this);
        database = helper.getReadableDatabase();
        updatedatabase("1", 500.00);
        deleteRecords("1");

        textView = findViewById(R.id.text);
        selectRecord();

    }

    public void updatedatabase(String id, double price) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("PRICE", price);
        //for single value update
        database.update("PRODUCTS", contentValues, "_id = ?", new String[]{id});

        //for multiple value update
        // database.update("PRODUCTS",contentValues,"NAME = ? AND DESCRIPTIN = ?",new String[]{"Bread","Brown Bread"});

    }

    public void deleteRecords(String id) {
        database.delete("PRODUCTS", "_id = ?", new String[]{id});

    }

    public void selectRecord() {
         Cursor cursor = database.rawQuery("SELECT NAME,PRICE FROM PRODUCTS",new String[]{});

      //  Cursor cursor = database.rawQuery("SELECT NAME,PRICE FROM PRODUCTS WHERE NAME = ?", new String[]{"Bread"});

        if (cursor != null) {
            cursor.moveToFirst();
        }
        StringBuilder builder = new StringBuilder();

        do {
            String name = cursor.getString(0);
            String price = cursor.getString(1);

            builder.append("NAME - " + name + " PRICE - " + price + "\n");
        } while (cursor.moveToNext());

        textView.setText(builder.toString());

    }

}