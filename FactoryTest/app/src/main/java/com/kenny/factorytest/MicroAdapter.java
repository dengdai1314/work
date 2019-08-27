package com.kenny.factorytest;
/*
 *
 * File: MicroAdapter.java
 * Author: 29003
 * Create: 2019/8/21 15:53
 * Changes (from 2019/8/21)
 * -----------------------------------------------------------------
 * 2019/8/21 : Create MicroAdapter.java (29003);
 * -----------------------------------------------------------------
 * description:Micro适配器
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 29003
 * @description
 * @date 2019/8/21
 */
public class MicroAdapter extends BaseAdapter {
    LayoutInflater microinflater;
    List<Micro> microList;
    Context microContext;
    View view;
    ViewHolder viewHolder;
    private static final int micro_dark = 0;
    private static final int micro_red  = 1;
    private static final int micro_green =2;

    public MicroAdapter(List<Micro> microList,Context mContext){
        this.microList = microList;
        this.microContext = mContext;
        this.microinflater = LayoutInflater.from(microContext);
    }

    @Override
    public int getCount() {
        return microList.size();
    }

    @Override
    public String getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            view = microinflater.inflate(R.layout.micro_list,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.micro_name  = view.findViewById(R.id.micro_name);
            viewHolder.micro_angel = view.findViewById(R.id.micro_angle);
            viewHolder.micro_beam  = view.findViewById(R.id.micro_beam);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.micro_name.setText(microList.get(position).getMicro_name());
        viewHolder.micro_angel.setText("角度="+microList.get(position).getAngle()+"");
        viewHolder.micro_beam.setText("当前麦克风="+microList.get(position).getBeam()+"");
        //如果当前角度不为0，显示隐藏的内容
        if (microList.get(position).getAngle()!=0){
            viewHolder.micro_angel.setVisibility(View.VISIBLE);
            viewHolder.micro_beam.setVisibility(View.VISIBLE);
        }
        else{
            viewHolder.micro_angel.setVisibility(View.GONE);
            viewHolder.micro_beam.setVisibility(View.GONE);
        }
        //如果当前颜色为1，设置micro_name颜色为红色
        if (microList.get(position).getColor()==micro_red){
            viewHolder.micro_name.setTextColor(Color.RED);
        }else if (microList.get(position).getColor()==micro_green){
            viewHolder.micro_name.setTextColor(Color.GREEN);
        }else{
            viewHolder.micro_name.setTextColor(Color.BLACK);
        }
        //返回view
        return view;
    }

    /**
     * 改变页面数据，刷新页面显示
     * @param position
     */
    public void change(int position,int color){
        if(microList == null){
            microList = new LinkedList<Micro>();
        }
        if (position == 0){
            microList.get(position).setColor(micro_dark);
        }else if(position >0){
            microList.get(position-1).setColor(color);
        }
        notifyDataSetChanged();    //listView刷新
    }

    private class ViewHolder{
        TextView micro_name;
        TextView micro_angel;
        TextView micro_beam;
    }
}
