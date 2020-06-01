package com.practice.searchview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/5/1911:10
 * @description 存储历史搜索记录
 */
// 继承自SQLiteOpenHelper数据库类的子类
public class RccordSQLiteOpenHelper extends SQLiteOpenHelper {

    public static String name = "temp.db";
    public static Integer version = 1;

    public RccordSQLiteOpenHelper(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 打开数据库 & 建立了一个叫records的表，里面只有一列name来存储历史记录：
        db.execSQL("create table records(id integer primary key autoincrement,name varchar(200))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
