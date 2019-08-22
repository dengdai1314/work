package com.kenny.rewritefactorytest;
/*
 *
 * File: WifiAdapter.java
 * Author: 29003
 * Create: 2019/8/20 15:03
 * Changes (from 2019/8/20)
 * -----------------------------------------------------------------
 * 2019/8/20 : Create WifiAdapter.java (29003);
 * -----------------------------------------------------------------
 * description:wifi适配器
 * 已有实体类ScanResult
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @author 29003
 * @description
 * @date 2019/8/20
 */
public class WifiAdapter extends BaseAdapter {
    LayoutInflater wifiinflater;                                     //布局服务
    List<ScanResult> WifiList;
    public WifiAdapter(Context mContext, List<ScanResult> wifiList){
        this.wifiinflater  =LayoutInflater.from(mContext);
        this.WifiList = wifiList;
    }

    @Override
    public int getCount() {
        return WifiList.size();                                     //获取列表数据总数
    }

    @Override
    public Object getItem(int position) {
        return position;
    }        //获取当前项

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder = null;
        if (convertView == null){
            view = wifiinflater.inflate(R.layout.wifi_list, null);
            viewHolder = new ViewHolder();
            viewHolder.wifiName = view.findViewById(R.id.wifi_name);
            viewHolder.wifiSignal =view.findViewById(R.id.wifi_signal);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        ScanResult scanResult = WifiList.get(position);
        viewHolder.wifiName.setText(scanResult.SSID);                                                  //ssid对应wifi名字
        viewHolder.wifiSignal.setText("信号强度:   -"+String.valueOf(Math.abs(scanResult.level)));      //level信号等级，为负数
        return view;
    }
    class ViewHolder{
        TextView wifiName;
        TextView wifiSignal;
    }
}
