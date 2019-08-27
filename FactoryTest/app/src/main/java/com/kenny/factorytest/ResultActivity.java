package com.kenny.factorytest;
/*
 *
 * File: ResultActivity.java
 * Author: 29003
 * Create: 2019/8/22 9:50
 * Changes (from 2019/8/22)
 * -----------------------------------------------------------------
 * 2019/8/22 : Create ResultActivity.java (29003);
 * -----------------------------------------------------------------
 * description:结果页
 */
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends BaseActivity {
    private static final String TAG = Result.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initData();
    }
    public void initData(){
        saveJsonFile();                                     //保存文件
        List<Result> result = readJson();                   //读取json文件
        List<Result> showResult = new ArrayList<Result>();
        for(Result show : result){                          //遍历数据
            if(!show.getResult().contains("测试")){         //对数据进行处理
                showResult.add(show);
            }
        }
        ListView list = findViewById(R.id.recycler_view);
        ResultAdapter adapter = new ResultAdapter((ArrayList<Result>) showResult,ResultActivity.this);
        list.setAdapter(adapter);
    }
}
