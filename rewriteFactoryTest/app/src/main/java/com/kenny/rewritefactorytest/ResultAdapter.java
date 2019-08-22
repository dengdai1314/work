package com.kenny.rewritefactorytest;
/*
 *
 * File: ResultAdapter.java
 * Author: 29003
 * Create: 2019/8/22 9:50
 * Changes (from 2019/8/22)
 * -----------------------------------------------------------------
 * 2019/8/22 : Create ResultAdapter.java (29003);
 * -----------------------------------------------------------------
 * description:结果页listview适配器
 */
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @author 29003
 * @description
 * @date 2019/8/21
 */
public class ResultAdapter extends BaseAdapter {
    LayoutInflater resultinflater;
    List<Result> resultList;
    Context mContext;

    public ResultAdapter(List<Result> resultList,Context mContext){
        this.resultinflater = LayoutInflater.from(mContext);
        this.resultList = resultList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder = null;
        if(convertView == null){
            view = resultinflater.inflate(R.layout.result_list,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.result_name = convertView.findViewById(R.id.result_name);
            viewHolder.result = convertView.findViewById(R.id.result);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.result_name.setText(resultList.get(position).getName()+":");
        if (resultList.get(position).getResult().contains("成功")){
            viewHolder.result_name.setTextColor(Color.RED);
        } else{
            viewHolder.result_name.setTextColor(Color.GREEN);
        }
        viewHolder.result.setText(resultList.get(position).getResult());
        return view;
    }

    private class ViewHolder{
        TextView result_name;
        TextView result;
    }
}
