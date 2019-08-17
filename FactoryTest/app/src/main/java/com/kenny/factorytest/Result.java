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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Result extends BaseActivity {
//    String result_button;
//    String result_lcd;
//    String result_camera;
//    String result_wifi;
//    String result_trumpet;
//    String result_micro;
//    String result_micro0;
//    String result_micro1;
//    String result_micro2;
//    String result_micro3;
//    String result_micro4;
//    String result_micro5;
    private static final String TAG = Result.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initData();
    }

    public void initData(){
        saveFile();//保存文件
        List<ResultJson> result = readJson();//读取json文件
        List<ResultJson> showResult = new ArrayList<ResultJson>();
        for(ResultJson show : result){
            if(!show.getResult().contains("测试")){
                showResult.add(show);
            }
        }
        ListView list = findViewById(R.id.resultList);
        ResultAdapter adapter = new ResultAdapter((ArrayList<ResultJson>) showResult,Result.this);
        list.setAdapter(adapter);
    }

}