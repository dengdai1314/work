package com.kenny.factorytest;
/*
 *
 * File: Result.java
 * Author: 29003
 * Create: 2019/8/3 11:53
 * Changes (from 2019/8/3)
 * -----------------------------------------------------------------
 * 2019/8/3 : Create Result.java (29003);
 * -----------------------------------------------------------------
 * description:最终结果
 */

import android.os.Bundle;
import android.util.Log;

public class Result extends BaseActivity {
//    String result_button;
//    String result_lcd;
//    String result_camera;
//    String result_wifi;
//    String result_trumpet;
//    String result_micro;
//    String result_micro0;
//    String result_micro1;
//    String result_micro2;
//    String result_micro3;
//    String result_micro4;
//    String result_micro5;
    private static final String TAG = Result.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initData();
    }

    public void initData(){
//        result_button = sharedPreferences.getString("ButtonTest", "按键测试结果：失败");
//        result_lcd = sharedPreferences.getString("LcdTest", "lCD测试结果：失败");
//        result_camera = sharedPreferences.getString("CameraTest", "摄像头测试结果：失败");
//        result_wifi = sharedPreferences.getString("WifiTest", "Wifi测试结果：失败");
//        result_trumpet = sharedPreferences.getString("TrumpetTest","喇叭测试结果：失败");
//        result_micro = "麦克风测试结果：";
//        result_micro0 = sharedPreferences.getString("麦克风0","麦克风0：测试失败");
//        result_micro1 = sharedPreferences.getString("麦克风1","麦克风1：测试失败");
//        result_micro2 = sharedPreferences.getString("麦克风2","麦克风2：测试失败");
//        result_micro3 = sharedPreferences.getString("麦克风3","麦克风3：测试失败");
//        result_micro4 = sharedPreferences.getString("麦克风4","麦克风4：测试失败");
//        result_micro5 = sharedPreferences.getString("麦克风5","麦克风5：测试失败");
//        String[] data ={result_button,result_lcd,result_camera,result_wifi,result_trumpet,
//                result_micro,result_micro0,result_micro1,result_micro2,result_micro3,result_micro4,result_micro5};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Result.this,android.R.layout.simple_list_item_1,data);
//        ListView listView = findViewById(R.id.resultList);
//        listView.setAdapter(adapter);
        Log.e(TAG,products.toString());
        saveFile();

    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        活动完成后，清空数据
//        File sdcardRoot=Environment.getExternalStorageDirectory();//获取sdcard目录路径
//        SharedPreferences settings = this.getSharedPreferences("result", 0);
//        SharedPreferences.Editor localEditor =settings.edit();
//        localEditor.clear().commit();
//        Log.e(TAG,"测试结果已清空");
//        Toast.makeText(Result.this,"数据已清空",Toast.LENGTH_SHORT).show();
//    }
}