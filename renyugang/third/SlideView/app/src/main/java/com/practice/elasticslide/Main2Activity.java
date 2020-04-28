package com.practice.elasticslide;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.practice.slideview.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/27 15:44
 * @description 自定义控件之 View 的弹性滑动（三）https://blog.csdn.net/airsaid/article/details/53207851
 */
public class Main2Activity extends AppCompatActivity {
    private ScrollerLinearLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mLayout = findViewById(R.id.scrollerLayout);

//        for (int i=0;i<3;i++){
//            ListView listView = new ListView(this);
//            List<String> list = new ArrayList<>();
//            for(int i1 = 0;i1<50;i1++){
//                list.add("page"+i+",name:"+i1);
//            }
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
//            listView.setAdapter(adapter);
//            mLayout.addView(listView);
//        }

        for (int i=0;i<3;i++){
            MyListView listView = new MyListView(this);
            List<String> list = new ArrayList<>();
            for(int i1 = 0;i1<50;i1++){
                list.add("page"+i+",name:"+i1);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
            listView.setAdapter(adapter);
            mLayout.addView(listView);
        }
    }

//    public void start(View v){
//        mLayout.startScroll();
//    }


    private int mStartX = 0;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    if(mStartX>-100){
                        mLayout.scrollTo(mStartX,0);
                        mStartX --;
                        mHandler.sendEmptyMessageDelayed(0,50);
                    }
            }
        }
    };

}
