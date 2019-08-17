package com.kenny.recyclerview;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();
    FruitAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        //创建实例，第一个参数用于指定布局的列数，第二个参数指定布局的排列方向
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//调整布局的排列方向（横向）
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }

    private void  initFruits(){
        for(int i=0;i<15;i++) {
            Fruit fruit1 = new Fruit(getRandomLengthName("fruit1"),R.mipmap.ic_launcher);
            fruitList.add(fruit1);
            Fruit fruit2 = new Fruit(getRandomLengthName("fruit2"),R.mipmap.ic_launcher);
            fruitList.add(fruit2);
            Fruit fruit3 = new Fruit(getRandomLengthName("fruit3"),R.mipmap.ic_launcher);
            fruitList.add(fruit3);
        }
    }

    //创造一个1到20间的随机数，然后将参数中传入的字符串重复随机遍
    private String getRandomLengthName(String name){
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append(name);
        }
        return builder.toString();
    }
}
