package com.practice.lbstest;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/20 15:35
 * @description 11章 基于位置的服务
 */
public class MainActivity extends AppCompatActivity {

    public String[] permissions = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    List<String> mPermissionList = new ArrayList<>();

    private MapView mapView = null;

    private TextView positionText;

    private LocationClient mLocationClient;

    private BaiduMap baiduMap;

    private boolean isFirstLocate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());//初始化操作，必须在setContentView方法前调用
        SDKInitializer.setCoordType(CoordType.BD09LL);//设置坐标类型
        mLocationClient = new LocationClient(getApplicationContext());//创建LocationClient实例，接收一个Context参数
        mLocationClient.registerLocationListener(new MyLocationListener());//注册一个定位监听器，当获取到位置信息时，就会回调这个定位监听器
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.bmapView);
        positionText = findViewById(R.id.posotion_text_view);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            initPermission();
        }
    }

    /**
     * 请求权限
     */
    private void initPermission() {
        mPermissionList.clear();
        for(int i=0;i<permissions.length;i++) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        if(!mPermissionList.isEmpty()){
            ActivityCompat.requestPermissions(MainActivity.this,permissions,1);
        }else {
            requestLocation();
        }
    }

    /**
     * 开始地理位置定位
     */
    private void requestLocation() {
        initLocation();
        //开始定位
        mLocationClient.start();
    }

    /**
     * 初始化
     */
    private void initLocation(){
        baiduMap = mapView.getMap();//获取BaiduMap实例
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);//设置普通地图
        //baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);//设置卫星地图
        baiduMap.setTrafficEnabled(true);//开启交通图
        //路况颜色设置
        baiduMap.setCustomTrafficColor("#ffba0101", "#fff33131", "#ffff9e19", "#00000000");
        //开启地图的定位图层
        baiduMap.setMyLocationEnabled(true);
        //创建LocationClientOption对象
        LocationClientOption option = new LocationClientOption();
        //设置更新间隔，毫秒为单位
        option.setScanSpan(5000);
        //修改定位模式
        //只使用GPS进行定位Device_Sensors；GPS和网络共同定位：Hight_Accuracy(默认)；只使用网络定位Battery_Saving
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIsNeedAddress(true);//表示需要获取当前位置的详细地址信息
        //开始计时
        mLocationClient.setLocOption(option);
    }

    private void navigateTo(BDLocation location){
        if(isFirstLocate){

            //移动到指定经纬度
            LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);

            //设置地图缩放
            update = MapStatusUpdateFactory.zoomTo(19f);//设置地图缩放级别3-19，可小数。值越大，地图显示信息越精细
            //对地图状态做更新，否则可能不会触发渲染，造成样式定义无法立即生效。
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }

        //显示地位点到地图上
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }

    /**
     * 定位结果回调
     */
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(final BDLocation location) {
            //地图移动到设备所在位置
            if(location.getLocType() == BDLocation.TypeGpsLocation||location.getLocType() == BDLocation.TypeNetWorkLocation){
                navigateTo(location);
            }

            //显示一个精度圈
            MyLocationData locationData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            baiduMap.setMyLocationData(locationData);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    StringBuilder currentPosition = new StringBuilder();
                    //location.getLatitude 获取当前位置的纬度
                    currentPosition.append("纬度：").append(location.getLatitude()).append("\n");
                    //location.getLongitude() 获取当前位置的经度
                    currentPosition.append("经线：").append(location.getLongitude()).append("\n");
                    currentPosition.append("国家：").append(location.getCountry()).append("\n");
                    currentPosition.append("省：").append(location.getProvince()).append("\n");
                    currentPosition.append("市：").append(location.getCity()).append("\n");
                    currentPosition.append("区：").append(location.getDistrict()).append("\n");
                    currentPosition.append("街道：").append(location.getStreet()).append("\n");
                    currentPosition.append("定位方式：");
                    //location.getLocType() 获取当前定位方式
                    if (location.getLocType() == BDLocation.TypeGpsLocation) {
                        currentPosition.append("GPS");
                    } else if (location.getLocType() ==
                            BDLocation.TypeNetWorkLocation) {
                        currentPosition.append("网络");
                    }
                    positionText.setText(currentPosition);
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //一定要调用，不然不停定位
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasPermissiomDismiss = false;
        if(requestCode == 1){
            for(int i=0;i<grantResults.length;i++){
                if(grantResults[i]==PackageManager.PERMISSION_DENIED){
                    hasPermissiomDismiss = true;
                    Log.d("permission",permissions[i]);
                }
            }
        }
        if(hasPermissiomDismiss) {
            showPermissionDismiss();
        }
    }

    AlertDialog mPremissionDialog;
    private void showPermissionDismiss() {
        if(mPremissionDialog == null){
            mPremissionDialog = new AlertDialog.Builder(this)
                    .setMessage("已禁用权限")
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancelPermissionDialog();
                            Uri packageUri = Uri.parse("package:com.practise.servicebestpractice");
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,packageUri);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancelPermissionDialog();
                            finish();
                        }
                    })
                    .create();
            mPremissionDialog.show();
        }
    }

    private void cancelPermissionDialog() {
        mPremissionDialog.cancel();
    }
}
