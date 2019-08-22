package com.kenny.factorytest;
/*
 *
 * File: ResultAdapter.java
 * Author: 29003
 * Create: 2019/8/19 11:01
 * Changes (from 2019/8/19)
 * -----------------------------------------------------------------
 * 2019/8/19 : Create ResultAdapter.java (29003);
 * -----------------------------------------------------------------
 * description:Result Listview Adapter
 */
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author 29003
 * @description
 * @date 2019/8/17
 */
public class ResultAdapter extends BaseAdapter {
    ArrayList<ResultJson> resultlist;
    Context mContext;

    public ResultAdapter(ArrayList<ResultJson> resultlist, Context mContext){
        this.resultlist = resultlist;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return resultlist.size();
    }

    @Override
    public Object getItem(int postion) {
        return postion;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.result_list,parent,false);
            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.result_name);
            holder.result = convertView.findViewById(R.id.result);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(resultlist.get(position).getName()+":");
        if (resultlist.get(position).getResult().contains("成功")){
            holder.name.setTextColor(Color.RED);
        } else{
            holder.name.setTextColor(Color.GREEN);
        }
        holder.result.setText(resultlist.get(position).getResult());
        return convertView;
    }

    private class ViewHolder{
        TextView name;
        TextView result;
    }
}
