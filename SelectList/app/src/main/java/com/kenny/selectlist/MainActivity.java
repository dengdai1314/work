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
    ListView lvData;                                    //listview
    LinearLayout mLlEditBar;                            //按钮条
    Button cancel;
    Button delete;
    Button inverse;
    Button selectall;
    private MainAdapter adapter;                        //适配器
//    private List<Main> Result = new ArrayList<Main>();  //所有数据
    private List<BaseItem> mData = null;
    private List<Main> mCheckedData = new ArrayList<Main>();//将选中数据放入里面
    private SparseBooleanArray stateCheckedMap = new SparseBooleanArray();//用来存放CheckBox的选中状态，true为选中,false为没有选中
    private boolean isSelectedAll = true;//用来控制点击全选，全选和全不选相互切换
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        initData();
        adapter = new MainAdapter(Result,MainActivity.this,stateCheckedMap);
        lvData.setAdapter(adapter);
        setOnListViewItemClickListener();
        setOnListViewItemLongClickListener();
    }

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

    private void cancel() {
        setStateCheckedMap(false);
        mCheckedData.clear();
        mLlEditBar.setVisibility(View.GONE);
        adapter.setShowCheckBox(false);
        adapter.notifyDataSetChanged();
    }

    private void delete() {
        if(mCheckedData.size() == 0){
            Toast.makeText(MainActivity.this, "您还没有选中任何数据！", Toast.LENGTH_SHORT).show();
            return;
        }
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("提示");
        dialog.setMessage("您确定要删除吗？");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Result.removeAll(mCheckedData);
                setStateCheckedMap(false);
                mCheckedData.clear();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeButton("取消",null);
        dialog.show();
    }
    private void inverse() {
        mCheckedData.clear();
        for (int i = 0; i < Result.size(); i++) {
            if (stateCheckedMap.get(i)) {
                stateCheckedMap.put(i, false);
            } else {
                stateCheckedMap.put(i, true);
                mCheckedData.add(Result.get(i));
            }
            lvData.setItemChecked(i, stateCheckedMap.get(i));
        }
        adapter.notifyDataSetChanged();
    }
    private void selectall() {
        mCheckedData.clear();//清空之前选中数据
        if (isSelectedAll) {
            setStateCheckedMap(true);//将CheckBox的所有选中状态变成选中
            isSelectedAll = false;
            mCheckedData.addAll(Result);//把所有的数据添加到选中列表中
        } else {
            setStateCheckedMap(false);//将CheckBox的所有选中状态变成选中
            isSelectedAll = true;
        }
        adapter.notifyDataSetChanged();
    }
    private void initview() {
        lvData = findViewById(R.id.resultList);
        mLlEditBar = findViewById(R.id.edit_bar);
        cancel = findViewById(R.id.cancel);
        delete = findViewById(R.id.delete);
        inverse = findViewById(R.id.inverse);
        selectall = findViewById(R.id.select_all);
        cancel.setOnClickListener(this);
        delete.setOnClickListener(this);
        inverse.setOnClickListener(this);
        selectall.setOnClickListener(this);
        lvData.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    public void initData(){
//        for(int i = 0;i<30;i++){
//            Result.add(new Main("第"+i+"项"));
//        }
        for(int i = 0;i<30;i++){
            if (i%2!=0){
                this.mData.add(new )
            }
        }

        setStateCheckedMap(false);
    }
    /**
     * 设置所有CheckBox的选中状态
     * */
    private void setStateCheckedMap(boolean isSelectedAll) {
        for (int i = 0; i < Result.size(); i++) {
            stateCheckedMap.put(i, isSelectedAll);
            lvData.setItemChecked(i, isSelectedAll);
        }
    }

    private void setOnListViewItemClickListener() {
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateCheckBoxStatus(view, position);
            }
        });
    }

    /**
     * 如果返回false那么click仍然会被调用,,先调用Long click，然后调用click。
     * 如果返回true那么click就会被吃掉，click就不会再被调用了
     * 在这里click即setOnItemClickListener
     */
    private void setOnListViewItemLongClickListener() {
        lvData.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mLlEditBar.setVisibility(View.VISIBLE);//显示上方按钮条
                adapter.setShowCheckBox(true);//CheckBox的那个方框显示
                updateCheckBoxStatus(view, position);
                return true;
            }
        });
    }

    private void updateCheckBoxStatus(View view, int position) {
        MainAdapter.ViewHolder1 holder1 = (MainAdapter.ViewHolder1) view.getTag();
        holder1.checkBox.toggle();//反转CheckBox的选中状态
        lvData.setItemChecked(position, holder1.checkBox.isChecked());//长按ListView时选中按的那一项
        stateCheckedMap.put(position, holder1.checkBox.isChecked());//存放CheckBox的选中状态
        if (holder1.checkBox.isChecked()) {
            mCheckedData.add(Result.get(position));//CheckBox选中时，把这一项的数据加到选中数据列表
        } else {
            mCheckedData.remove(Result.get(position));//CheckBox未选中时，把这一项的数据从选中数据列表移除
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        if (mLlEditBar.getVisibility() == View.VISIBLE) {
            cancel();
            return;
        }
        super.onBackPressed();
    }

}
