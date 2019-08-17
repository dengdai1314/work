package com.kenny.refreshsingledata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @author 29003
 * @description
 * @date 2019/8/14
 */
public class MicroAdapter extends BaseAdapter {
    List<Micro> microlist;
    Context mContext;
    public MicroAdapter(List<Micro> microlist, Context mContext){
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.micro_list,parent,false);
        TextView micro_name = convertView.findViewById(R.id.micro_name);
        TextView angel = convertView.findViewById(R.id.angle);
        TextView beam = convertView.findViewById(R.id.beam);
        micro_name.setText(microlist.get(position).getMicro_name());
        angel.setText(microlist.get(position).getAngle()+"");
        beam.setText(microlist.get(position).getBeam()+"");
        return convertView;
    }
}