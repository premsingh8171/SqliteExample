package com.psd.sqllitedatabaseexp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;

import com.psd.sqllitedatabaseexp.db.MyHelper;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyHelper helper = new MyHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT NAME,PRICE FROM PRDUCTS",new String[]{});
        if (cursor!=null){
            cursor.moveToFirst();
        }
        StringBuilder builder = new StringBuilder();

        do {
            String name = cursor.getString(0);
            String price = cursor.getString(1);

            builder.append("NAME - "+name+" PRICE - "+price+"\n");
        }while (cursor.moveToNext());

        TextView textView  = findViewById(R.id.text);
        textView.setText(builder.toString());

    }
}