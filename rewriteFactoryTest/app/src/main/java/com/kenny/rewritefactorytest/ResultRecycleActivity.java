package com.kenny.rewritefactorytest;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
public class ResultRecycleActivity extends BaseActivity  {

    private static final String TAG = ResultRecycleActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_recycle);
        initData();
    }

    private void initData(){
        saveJsonFile();//保存文件
        List<Result> result = readJson();//读取json文件
        List<Result> showResult = new ArrayList<Result>();
        for(Result show : result){
            if(!show.getResult().contains("测试")){
                showResult.add(show);
            }
        }
        RecyclerView rv = findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
