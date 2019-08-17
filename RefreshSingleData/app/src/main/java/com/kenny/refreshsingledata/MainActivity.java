package com.kenny.refreshsingledata;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    List<Micro> microdata;
    Button test;
    int currentMicro=0;//当前麦克风项位置
    MicroAdapter microAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context microContext = MainActivity.this;
        ListView micro_list = findViewById(R.id.micro_list);
        microdata  = new LinkedList<Micro>();
        microdata.add(new Micro("麦克风1",0,0));
        microdata.add(new Micro("麦克风2",0,0));
        microdata.add(new Micro("麦克风3",0,0));
        microdata.add(new Micro("麦克风4",0,0));
        microdata.add(new Micro("麦克风5",0,0));
        microdata.add(new Micro("麦克风6",0,0));
        microAdapter = new MicroAdapter((LinkedList<Micro>) microdata, microContext);
        micro_list.setAdapter(microAdapter);

        test.findViewById(R.id.test);
        test.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.test:{
                microdata.add(currentMicro,new Micro("麦克风2",0,0));
                break;
            }
        }
    }

    public void add(int position,Micro micro){
        microdata.add(position,micro);
    }

}
