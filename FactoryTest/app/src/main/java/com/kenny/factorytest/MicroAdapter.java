package com.kenny.factorytest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * @author 29003
 * @description
 * @date 2019/8/14
 */
public class MicroAdapter extends BaseAdapter {

    LinkedList<Micro> microlist;
    Context mContext;
    int micro_dark = 0;
    int micro_red = 1;
    int micro_green =2;

    public MicroAdapter(LinkedList<Micro> microlist,Context mContext){
        this.microlist = microlist;
        this.mContext = mContext;
    }
    @Override
    public int getCount() {
        return microlist.size();
    }

    @Override
    public String getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView ==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.micro_list,parent,false);
            holder = new ViewHolder();
            holder.micro_name = convertView.findViewById(R.id.micro_name);
            holder.angel = convertView.findViewById(R.id.angle);
            holder.beam = convertView.findViewById(R.id.beam);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.micro_name.setText(microlist.get(position).getMicro_name());
        //如果当前角度不为0，显示隐藏的内容
        if (microlist.get(position).getAngle()!=0){
            holder.angel.setText("角度="+microlist.get(position).getAngle()+"");
            holder.beam.setText("当前麦克风="+microlist.get(position).getBeam()+"");
            holder.angel.setVisibility(View.VISIBLE);
            holder.beam.setVisibility(View.VISIBLE);
        }
        else{
            holder.angel.setVisibility(View.GONE);
            holder.beam.setVisibility(View.GONE);
        }
        //如果当前颜色为1，设置micro_name颜色为红色
        if (microlist.get(position).getColor()==micro_red){
            holder.micro_name.setTextColor(Color.RED);
        }else if (microlist.get(position).getColor()==micro_green){
            holder.micro_name.setTextColor(Color.GREEN);
        }else{
            holder.micro_name.setTextColor(Color.BLACK);
        }
        //返回view
        return convertView;
    }

    /**
     * 改变页面数据，刷新页面显示
     * @param position
     */
    public void change(int position,int color){
        if(microlist == null){
            microlist = new LinkedList<Micro>();
        }
        if (position == 0){
            microlist.get(position).setColor(micro_dark);
        }else if(position >0){
            microlist.get(position-1).setColor(color);
        }
//        microlist.set(position,micro);
        notifyDataSetChanged();
    }

    private class ViewHolder{
        TextView micro_name;
        TextView angel;
        TextView beam;
    }

}