package com.practise.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String newId;

    Button addData;
    Button deleteData;
    Button queryData;
    Button updateData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addData = findViewById(R.id.add_data);
        deleteData = findViewById(R.id.delete_data);
        queryData = findViewById(R.id.query_data);
        updateData = findViewById(R.id.update_data);
        addData.setOnClickListener(this);
        deleteData.setOnClickListener(this);
        queryData.setOnClickListener(this);
        updateData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_data:
                //1. 调用Uri.parse将一个内容URI解析成Uri对象
                //2. 把要添加的数据都存放到ContentValues对象中
                //3. 调用ContentResolver的insert方法执行添加操作
                //注：insert方法返回一个Uri对象，这个对象中包含了新增数据的id
                Uri uri1 = Uri.parse("content://com.example.databasetest.provider/book");
                ContentValues values = new ContentValues();
                values.put("name","A Clash of Kings");
                values.put("author","George Martin");
                values.put("pages",1040);
                values.put("price",22.85);
                Uri newUri = getContentResolver().insert(uri1,values);
                newId = newUri.getPathSegments().get(1);
                break;
            case R.id.query_data:
                //1. 调用Uri.parse将一个内容URI解析成Uri对象
                //2. 调用ContentResolver的query方法执行添加操作
                //3. 遍历Cursor
                Uri uri2 = Uri.parse("content://com.example.databasetest.provider/book");
                Cursor cursor = getContentResolver().query(uri2,null,null,null,null);
                if(cursor!=null){
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity", "book name is " + name);
                        Log.d("MainActivity", "book author is " + author);
                        Log.d("MainActivity", "book pages is " + pages);
                        Log.d("MainActivity", "book price is " + price);
                    }
                    cursor.close();
                }
                break;
            case R.id.update_data:
                //1. 调用Uri.parse将一个内容URI解析成Uri对象
                //2. 把要更新的数据都存放到ContentValues对象中
                //3. 调用ContentResolver的update方法执行添加操作
                Uri uri3 = Uri.parse("content://com.example.databasetest.provider/book/" + newId);//只更新之前添加的新数据
                ContentValues values2 = new ContentValues();
                values2.put("name", "A Storm of Swords");
                values2.put("pages", 1216);
                values2.put("price", 24.05);
                getContentResolver().update(uri3, values2, null, null);
                break;
            case R.id.delete_data:
                Uri uri4 = Uri.parse("content://com.example.databasetest.provider/book/" + newId);
                getContentResolver().delete(uri4, null, null);
                break;
                default:break;
        }
    }
}
