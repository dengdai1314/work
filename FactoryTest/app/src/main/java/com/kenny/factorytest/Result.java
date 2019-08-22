package com.kenny.factorytest;
/*
 *
 * File: Result.java
 * Author: 29003
 * Create: 2019/8/3 11:53
 * Changes (from 2019/8/3)
 * -----------------------------------------------------------------
 * 2019/8/3 : Create Result.java (29003);
 * -----------------------------------------------------------------
 * description:最终结果
 */

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Result extends BaseActivity {
    private static final String TAG = Result.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initData();
    }

    private void initData(){
        saveJsonFile();//保存文件
        List<ResultJson> result = readJson();//读取json文件
        List<ResultJson> showResult = new ArrayList<ResultJson>();
        for(ResultJson show : result){
            if(!show.getResult().contains("测试")){
                showResult.add(show);
            }
        }
        //listview
//    ListView list = findViewById(R.id.resultList);
//    ResultAdapter adapter = new ResultAdapter((ArrayList<ResultJson>) showResult,Result.this);
//    list.setAdapter(adapter);
        //recycleview
    RecyclerView rv = findViewById(R.id.recycler_view);
    rv.setLayoutManager(new LinearLayoutManager(this));
    rv.setAdapter(new resultRecyclerAdapter(showResult));
    }
}