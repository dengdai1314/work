package com.kenny.selectlist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import androidx.appcompat.widget.AppCompatCheckBox;

/**
 * @author 29003
 * @description
 * @date 2019/8/22
 */
public class MainAdapter extends BaseAdapter {
    HashMap<Integer,View> lmap = new HashMap<Integer, View>();//用于解决列表重复选中
    List<Main> data;
    ViewHolder holder ;
    Context mContext;
    LayoutInflater resultinflater;
    private boolean isShowCheckBox = false;                   //表示当前是否是多选状态。
    private SparseBooleanArray stateCheckedMap = new SparseBooleanArray();//用来存放CheckBox的选中状态，true为选中,false为没有选中

    public MainAdapter(List<Main> resultList, Context mContext, SparseBooleanArray stateCheckedMap){
        this.resultinflater = LayoutInflater.from(mContext);
        this.data = resultList;
        this.mContext = mContext;
        this.stateCheckedMap = stateCheckedMap;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(lmap.get(position) == null){
            convertView = resultinflater.inflate(R.layout.main_list,parent,false);
            holder = new ViewHolder();
            holder.checkBox =  convertView.findViewById(R.id.chb_select_way_point);
            holder.result_image = convertView.findViewById(R.id.result_image);
            holder.mTvData = convertView.findViewById(R.id.result_name);
            holder.result_describe = convertView.findViewById(R.id.result_discribe);
            holder.result_name2 = convertView.findViewById(R.id.result_name2);
            holder.line1 = convertView.findViewById(R.id.line1);
            holder.line2 = convertView.findViewById(R.id.line2);
            lmap.put(position,convertView);
            convertView.setTag(holder);
        }else {
            convertView = lmap.get(position);
            holder = (ViewHolder) convertView.getTag();
        }
        showAndHideCheckBox();//控制CheckBox的那个的框显示与隐藏
        //根据当前位置显示不同布局
        if(position%2 ==0){
            holder.line1.setVisibility(View.VISIBLE);
            holder.line2.setVisibility(View.GONE);
            holder.mTvData.setText(data.get(position).getName());
            holder.result_image.setImageResource(data.get(position).getImage());
            holder.result_describe.setText(data.get(position).getResult_describe());
        }else {
            holder.line1.setVisibility(View.GONE);
            holder.line2.setVisibility(View.VISIBLE);
            holder.result_name2.setText(data.get(position).getName());
        }
        holder.checkBox.setChecked(stateCheckedMap.get(position));//设置CheckBox是否选中//如未设置，取消后复选框保持选中状态
        return convertView;
    }

    public class ViewHolder{
        AppCompatCheckBox checkBox;
        ImageView result_image;
        TextView mTvData;
        TextView result_describe;
        TextView result_name2;
        LinearLayout line1;
        LinearLayout line2;
    }

    private void showAndHideCheckBox() {
        if (isShowCheckBox) {
            holder.checkBox.setVisibility(View.VISIBLE);
        } else {
            holder.checkBox.setVisibility(View.GONE);
        }
    }

    public boolean isShowCheckBox() {
        return isShowCheckBox;
    }

    public void setShowCheckBox(boolean showCheckBox) {
        isShowCheckBox = showCheckBox;
    }
}