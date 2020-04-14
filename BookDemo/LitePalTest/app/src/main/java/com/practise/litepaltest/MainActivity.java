package com.practise.litepaltest;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/14 14:05
 * @description 6.5使用LitePal操作数据库
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_createDatabase = findViewById(R.id.create_database);
        Button btn_addData = findViewById(R.id.add_data);
        Button btn_updataData1 = findViewById(R.id.update_data1);
        Button btn_updataData2 = findViewById(R.id.update_data2);
        Button btn_deleteData = findViewById(R.id.delete_data);
        Button btn_queryAllData = findViewById(R.id.query_alldata);
        Button btn_updataDefault = findViewById(R.id.update_Default);
        btn_createDatabase.setOnClickListener(this);
        btn_addData.setOnClickListener(this);
        btn_updataData1.setOnClickListener(this);
        btn_updataData2.setOnClickListener(this);
        btn_deleteData.setOnClickListener(this);
        btn_queryAllData.setOnClickListener(this);
        btn_updataDefault.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_database:
                LitePal.getDatabase();
                break;
            case R.id.add_data:
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPress("unKnow");
                book.setPrice(16);
                book.save();
                break;
            case R.id.update_data1:
                Book book1 = new Book();
                book1.setName("The Lost Symbol");
                book1.setAuthor("Dan Brown");
                book1.setPages(520);
                book1.setPrice(20);
                book1.setPress("unknown");
                book1.save();
                book1.setPrice(10);
                book1.save();
                break;
            case R.id.update_data2:
                Book book2 = new Book();
                book2.setPrice(13);
                book2.setAuthor("the da");
                book2.updateAll("name = ? and author = ?","The Da Vinci Code","Dan Brown");
            case R.id.delete_data:
                LitePal.deleteAll(Book.class,"price = ?","10");
                break;
            case R.id.query_alldata:
                List<Book> books = LitePal.findAll(Book.class);
//                Book firstBook = LitePal.findFirst(Book.class);//查询表中第一条数据
//                Book lastBook = LitePal.findLast(Book.class);//查询表中最后一条数据
//                List<Book> books = LitePal.select("name","author").find(Book.class);//指定查询哪几列的数据，对应了SQL当中的select关键字
//                List<Book> books = LitePal.where("pages > ?","400").find(Book.class);//指定查询的约束条件，对应了SQL当中的where关键字
//                List<Book> books = LitePal.order("price desc").find(Book.class);//指定结果的排序方式，对应了SQL当中的order by 关键字,desc 降序
//                List<Book> books = LitePal.limit(3).find(Book.class);//指定查询结果的数量，这里只查表中前3条数据
//                List<Book> books = LitePal.limit(3).offset(3).find(Book.class);//指定查询结果的偏移量，比如查询表中的第2,3,4条数据
                Cursor cursor = LitePal.findBySQL("select * from Book where pages > ? and price > ?","400","20");//原生查询


                for (Book book4 : books){
                    Log.d("MainActivity","book name is"+book4.getName());
                    Log.d("MainActivity","book author is"+book4.getAuthor());
                    Log.d("MainActivity","book pages is"+book4.getPages());
                    Log.d("MainActivity","book price is"+book4.getPrice());
                    Log.d("MainActivity","book press is"+book4.getPress());
                }
                break;
            case R.id.update_Default:
                Book book3 = new Book();
                book3.setToDefault("pages");
                book3.updateAll();
                break;
            default:break;
        }
    }
}
