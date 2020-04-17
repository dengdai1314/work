package com.demo.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/14 14:04
 * @description 6.4SQLite数据库存储
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MyDatabaseHelper dbHelper;
    SQLiteDatabase db;
    ContentValues values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        values = new ContentValues();
        //记得实例化
        dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,1);
        Button btn_createDatabase = findViewById(R.id.create_database);
        Button btn_addData = findViewById(R.id.add_data);
        Button btn_updataData = findViewById(R.id.update_data);
        Button btn_deleteData = findViewById(R.id.delete_data);
        Button btn_queryData = findViewById(R.id.query_data);
        btn_createDatabase.setOnClickListener(this);
        btn_addData.setOnClickListener(this);
        btn_updataData.setOnClickListener(this);
        btn_deleteData.setOnClickListener(this);
        btn_queryData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_database:
//                    this.deleteDatabase("BookStore.db");//删除数据库
                if(db.needUpgrade(1)){
                    db =
                            dbHelper.getWritableDatabase();
                }
                break;
            case R.id.add_data:
                values.put("name","the da vinci code");
                values.put("author","Dan brown");
                values.put("pages",454);
                values.put("price",16);
                db.insert("book",null,values);//插入数据
                values.clear();
                values.put("name","the da ");
                values.put("author","Dan ");
                values.put("pages",45);
                values.put("price",160);
                db.insert("book",null,values);
                values.clear();
                //原生
//                db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
//                        new String[] { "The Da Vinci Code", "Dan Brown", "454", "16.96" });
//                db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
//                        new String[] { "The Lost Symbol", "Dan Brown", "510", "19.95" });
                break;
            case R.id.update_data:
                values.clear();
                values.put("price",20);
                db.update("book",values,"name = ?",new String[]{"the da vinci code"});//更新数据
//                db.execSQL("update Book set price = ? where name = ?", new String[] { "10.99", "The book"});
                break;
            case R.id.delete_data:
                values.clear();
                db.delete("book","name = ?",new String[]{"the da vinci code"});//删除数据
//                db.execSQL("delete from Book where pages > ?", new String[] { "500" });
                break;
            case R.id.query_data:
//                db.rawQuery("select * from Book", null);
                Cursor cursor = db.query("book",null,null,null,null,null,null);
                if(cursor.moveToFirst()){//判断cursor内是否有数据,也可移动游标到第一
                    do{//先把第一个数据拿出来
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity", "book name is " + name);
                        Log.d("MainActivity", "book author is " + author);
                        Log.d("MainActivity", "book pages is " + pages);
                        Log.d("MainActivity", "book price is " + price);
                    }while (cursor.moveToNext());
                }
                cursor.close();
                break;
               default:break;
        }
    }
}
