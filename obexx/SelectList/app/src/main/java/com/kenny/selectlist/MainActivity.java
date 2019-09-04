package com.kenny.selectlist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = Main.class.getSimpleName();
    ListView List;                                          //listview
    LinearLayout EditBar;                                   //按钮条
    Button cancel;
    Button delete;
    Button inverse;
    Button selectall;
    private MainAdapter adapter;                            //适配器
    private List<Main> Result = new ArrayList<Main>();      //所有数据
    private List<Main> mCheckedData = new ArrayList<Main>();   //选中的数据
    //SparseBooleanArray类似map的key_value的存储方式，利用int管理object对象
    private SparseBooleanArray stateCheckedMap = new SparseBooleanArray();//用来存放CheckBox的选中状态，true为选中,false为没有选中

    private boolean isSelectedAll = true;                   //用来控制点击全选，全选和全不选相互切换
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        initData();
        adapter = new MainAdapter(Result,MainActivity.this,stateCheckedMap);
        List.setAdapter(adapter);
        setOnListViewItemClickListener();    //短按
        setOnListViewItemLongClickListener();//长按
    }

    /**
     * 初始化页面
     */
    private void initview() {
        List = findViewById(R.id.resultList);
        EditBar = findViewById(R.id.edit_bar);
        cancel = findViewById(R.id.cancel);
        delete = findViewById(R.id.delete);
        inverse = findViewById(R.id.inverse);
        selectall = findViewById(R.id.select_all);
        cancel.setOnClickListener(this);
        delete.setOnClickListener(this);
        inverse.setOnClickListener(this);
        selectall.setOnClickListener(this);
        List.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);//listview自带的多选模式
    }

    /**
     * 初始化数据
     */
    public void initData(){
        for(int i = 0;i<30;i++){
            Result.add(new Main(R.drawable.ic_launcher_background,"第"+i+"项","obexx"));
        }
        setStateCheckedMap(false);  //设置当前checkbox为未选中状态
    }

    /**
     *按钮点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancel:
                cancel();
                break;
            case R.id.delete:
                delete();
                break;
            case R.id.inverse:
                inverse();
                break;
            case R.id.select_all:
                selectall();
                break;
        }
    }

    /**
     * 取消事件
     */
    private void cancel() {
        setStateCheckedMap(false);          //将checkBox的所有状态变成未选中
        EditBar.setVisibility(View.GONE);   //隐藏按钮条
        adapter.setShowCheckBox(false);     //隐藏checkbox
        adapter.notifyDataSetChanged();     //更新listview
    }

    /**
     * 删除事件
     */
    private void delete() {
        if(mCheckedData.size() == 0){
            Toast.makeText(MainActivity.this, "您还没有选中任何数据！", Toast.LENGTH_SHORT).show();
            return;
        }
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);   //弹窗
        dialog.setTitle("提示");                                              //设置弹窗标题
        dialog.setMessage("您确定要删除吗？");                                  //设置弹窗信息
        //确定按钮监听事件
        //setPositiveButton：设置弹框后的确定按钮
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Result.removeAll(mCheckedData);    //移除选中的数据
                setStateCheckedMap(false);         //改变选中状态
                mCheckedData.clear();              //清空数据
                adapter.notifyDataSetChanged();    //刷新页面
                Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
            }
        });
        //setNegativeButton：设置弹框后的取消按钮
        dialog.setNegativeButton("取消",null);
        dialog.show();                              //显示弹窗
    }

    /**
     * 反转
     * 先清空数据
     *
     */
    private void inverse() {
        mCheckedData.clear();
        for (int i = 0; i < Result.size(); i++) {
            if (stateCheckedMap.get(i)) {          //获取index所对应的值，没有则返回null
                //存入数据
                stateCheckedMap.put(i, false);     //向sparseArray存入元素value，key为index
//                stateCheckedMap.append(i,true);
//                //获取信息
//                stateCheckedMap.get(i);
//                stateCheckedMap.get(i,true);
//                stateCheckedMap.clear();           //清空所有数据
//                stateCheckedMap.delete(i);         //删除某个key的数据

            } else {
                stateCheckedMap.put(i, true);
                mCheckedData.add(Result.get(i));
            }
            List.setItemChecked(i, stateCheckedMap.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 全选
     */
    private void selectall() {
        mCheckedData.clear();//清空之前选中数据
        if (isSelectedAll) {
            setStateCheckedMap(true);//将CheckBox的所有选中状态变成选中
            isSelectedAll = false;
            mCheckedData.addAll(Result);//把所有的数据添加到选中列表中
        } else {
            setStateCheckedMap(false);//将CheckBox的所有选中状态变成未选中
            isSelectedAll = true;
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 设置所有CheckBox的选中状态
     * */
    private void setStateCheckedMap(boolean isSelectedAll) {
        for (int i = 0; i < Result.size(); i++) {
            stateCheckedMap.put(i, isSelectedAll);
            List.setItemChecked(i, isSelectedAll);       //调用事件，设置checkbox被选中/未选中
        }
    }

    /**
     * 短按事件
     */
    private void setOnListViewItemClickListener() {
        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateCheckBoxStatus(view, position);
            }
        });
    }

    /**
     * 长按
     * 如果返回false那么click仍然会被调用,,先调用Long click，然后调用click。
     * 如果返回true那么click就会被吃掉，click就不会再被调用了
     * 在这里click即setOnItemClickListener
     */
    private void setOnListViewItemLongClickListener() {
        List.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                EditBar.setVisibility(View.VISIBLE);//显示上方按钮条
                adapter.setShowCheckBox(true);//CheckBox的那个方框显示
                updateCheckBoxStatus(view, position);
                return true;
            }
        });
    }

    /**
     * 更新复选框状态
     * @param view
     * @param position
     */
    private void updateCheckBoxStatus(View view, int position) {
        MainAdapter.ViewHolder holder = (MainAdapter.ViewHolder) view.getTag();
        holder.checkBox.toggle();                                  //反转CheckBox的选中状态
        List.setItemChecked(position, holder.checkBox.isChecked());//长按ListView时选中按的那一项
        stateCheckedMap.put(position, holder.checkBox.isChecked());//存放CheckBox的选中状态
        if (holder.checkBox.isChecked()) {
            mCheckedData.add(Result.get(position));   //CheckBox选中时，把这一项的数据加到选中数据列表
        } else {
            mCheckedData.remove(Result.get(position));//CheckBox未选中时，把这一项的数据从选中数据列表移除
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 点击返回按钮事件
     */
    @Override
    public void onBackPressed() {
        if (EditBar.getVisibility() == View.VISIBLE) {
            cancel();
            return;
        }
        super.onBackPressed();
    }

}
