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
    public WifiAdapter(Context context,List<ScanResult> wifiList){
        this.wifiinflater  =LayoutInflater.from(context);
        this.WifiList = wifiList;
    }

    @Override
    public int getCount() {
        return WifiList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        view = wifiinflater.inflate(R.layout.wifi_list, null);
        ScanResult scanResult = WifiList.get(position);
        TextView wifiName = (TextView) view.findViewById(R.id.wifi_name);
        wifiName.setText(scanResult.SSID);                                                  //ssid对应wifi名字
        TextView wifiSignal = (TextView) view.findViewById(R.id.wifi_signal);
        wifiSignal.setText("信号强度:   -"+String.valueOf(Math.abs(scanResult.level)));      //level信号等级，为负数
        return view;
    }
}
