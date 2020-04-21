package com.practise.materialtest;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.practise.materialtest.Adapter.FruitAdapter;
import com.practise.materialtest.entity.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/21 11:40
 * @description 第12章最佳的UI体验
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private SwipeRefreshLayout swipeRefesh;

    private Fruit[] fruits = {new Fruit("Apple",R.drawable.apple),
            new Fruit("Banana",R.drawable.banana),
            new Fruit("Orange", R.drawable.orange),
            new Fruit("Watermelon",R.drawable.watermelon),
            new Fruit("Pear",R.drawable.pear),
            new Fruit("Grape",R.drawable.grape),
            new Fruit("Pineapple",R.drawable.pineapple),
            new Fruit("Strawberry",R.drawable.strawberry),
            new Fruit("Cherry",R.drawable.cherry),
            new Fruit("Mango",R.drawable.mango)
    };
    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter fruitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //滑动菜单
        mDrawerLayout = findViewById(R.id.drawer_layout);
        //获取ActionBar实例
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            //让导航按钮显示出来
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置一个导航按钮图标
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        //滑动菜单内容
        NavigationView navView = findViewById(R.id.nav_view);
        //设置默认选中
        navView.setCheckedItem(R.id.nav_call);
        //设置一个菜单项选中事件的监听器，点击菜单项后悔回调到这里
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            //用户点击任意菜单项时，就会回调到该方法中
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //关闭滑动菜单
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        //悬浮按钮
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用了Snackbar的make() 方法来创建一个Snackbar 对
                //象，make() 方法的第一个参数需要传入一个View，只要是当前界面布
                //局的任意一个View都可以，Snackbar会使用这个View来自动查找最外层
                //的布局，用于展示Snackbar。第二个参数就是Snackbar中显示的内容，
                //第三个参数是Snackbar显示的时长。
                //setAction用于执行一些额外的逻辑操作
                Snackbar.make(v,"Data deleted",Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() //设置一个动作，点击后执行对应操作
                        {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this,"Data reset",Toast.LENGTH_LONG).show();
                            }
                        }).show();
//                Toast.makeText(MainActivity.this,"FAB Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        //CardView
        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        fruitAdapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(fruitAdapter);

        //下拉刷新
        swipeRefesh = findViewById(R.id.swipe_refresh);
        swipeRefesh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefesh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });

    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        fruitAdapter.notifyDataSetChanged();
                        swipeRefesh.setRefreshing(false);
                    }
                });
            }

        }).start();
    }

    private void initFruits() {
        fruitList.clear();
        for (int i=0;i<50;i++){
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    /**
     * 加载toolbar.xml菜单文件
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    /**
     * 处理toolbar中各个按钮的点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
                Toast.makeText(this,"You click BACKUP",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this,"You click DELETE",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this,"You click Setting",Toast.LENGTH_SHORT).show();
                break;
                //HomeAsUp按钮的id永远是android.R.id.home
            case android.R.id.home:
                //将滑动菜单展示出来
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:break;
        }
        return true;
    }
}
