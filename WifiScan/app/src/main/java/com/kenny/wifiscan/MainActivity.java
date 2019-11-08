package com.kenny.wifiscan;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/**
 * wifi列表扫描
 */
public class MainActivity extends AppCompatActivity {

    private WifiManager wifiManager;
    List<ScanResult> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        openWifi();
        list = wifiManager.getScanResults();
        ListView listView = (ListView) findViewById(R.id.listView);
        if (list == null) {
            Toast.makeText(this, "wifi未打开！", Toast.LENGTH_LONG).show();
        }else {
            listView.setAdapter(new MyAdapter(this,list));
        }
    }

    /**
     *  打开WIFI
     */
    private void openWifi() {
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
    }

    public class MyAdapter extends BaseAdapter {
        LayoutInflater inflater;
        List<ScanResult> list;

        public MyAdapter(Context context, List<ScanResult> list) {
            // TODO Auto-generated constructor stub
            this.inflater = LayoutInflater.from(context);
            this.list = list;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override

        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View view = null;
            view = inflater.inflate(R.layout.item_wifi_list, null);
            ScanResult scanResult = list.get(position);
            TextView textView = (TextView) view.findViewById(R.id.textView);
            textView.setText(scanResult.SSID);
            TextView signalStrenth = (TextView) view.findViewById(R.id.signal_strenth);
            signalStrenth.setText("信号强度:   -"+String.valueOf(Math.abs(scanResult.level)));
            return view;
        }
    }
}
