package com.practice.searchview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/5/1910:22
 * @description 搜索页面
 */
public class SearchDemo extends AppCompatActivity {
    // 1. 初始化搜索框变量
    private SearchView searchView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 2. 绑定视图
        setContentView(R.layout.activity_serach);
        // 3. 绑定组件
        searchView = findViewById(R.id.search_view);

        // 4. 设置点击搜索按键后的操作（通过回调接口）
        // 参数 = 搜索框输入的内容
        searchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAction(String string) {
                System.out.println("我收到了"+string);
            }
        });

        // 5. 设置点击返回按键后的操作（通过回调接口）
        searchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAction() {
                finish();
            }
        });

    }
}
