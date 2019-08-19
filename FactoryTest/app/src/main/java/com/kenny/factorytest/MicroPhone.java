package com.kenny.factorytest;
/*
 *
 * File: MicroPhone.java
 * Author: 29003
 * Create: 2019/8/9 19:19
 * Changes (from 2019/8/9)
 * -----------------------------------------------------------------
 * 2019/8/9 : Create MicroPhone.java (29003);
 * -----------------------------------------------------------------
 * description:麦克风测试
 * 记得要申请读取sdcard，录音权限
 */

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zz.iflytekenginelib.IWakeListener;
import com.zz.iflytekenginelib.IflytekEngineManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
//IWakeListenter 讯飞接入使用
public class MicroPhone extends BaseActivity implements View.OnClickListener,IWakeListener {
    public static final String TAG = MicroPhone.class.getSimpleName();
    TextView micro_result;                                              //麦克风结果项
    Button micro_up;                                                    //按钮+
    Button micro_down;                                                  //按钮-
    String resPath = "xiaoouxiaoou.jet";                                //原文件
    String targetDir = getSDPath() + "/AIUI/ivw60/";                    //迁移原文件到对应的目标文件夹
    String mResPath = getSDPath() + "/AIUI/ivw60/xiaoouxiaoou.jet";     //目标文件
    private IflytekEngineManager iflytekEngineManager;                  //实例化

    private int currentPosition =1;                                     //当前item position
    private String angle ;                                              //麦克风监听角度
    private String beam ;                                               //当前麦克风

    //请求权限
    private int PERMISSION_REQUEST_CODE=0;                              //请求权限码
    private String[] NEEDED_PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE,  //权限组
            Manifest.permission.RECORD_AUDIO};
    Boolean mHasPermission = false;                                     //是否请求权限
    //adapter
    List<Micro> microdata;
    private Context microContext;
    private MicroAdapter microAdapter = null;
    private ListView micro_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_micro_phone);
        micro_result = findViewById(R.id.micro_result);
        micro_up = findViewById(R.id.key_volume_up);
        micro_down = findViewById(R.id.key_volume_down);
        micro_up.setOnClickListener(this);
        micro_down.setOnClickListener(this);
        //检查权限是否获取，获取后实例化IflytekEngineManager
        initPermission();
        //判断目标文件夹是否存在
        if(!new File(targetDir).exists()){
            new File(targetDir).mkdirs();
        }
        //复制assets目录下文件到sdcard目标目录下
        try {
            if (getAssets().open(resPath) != null) {
                Util.copyFileFromAssets(MicroPhone.this, resPath, mResPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        microContext = MicroPhone.this;
        micro_list = findViewById(R.id.micro_list);
        microdata  = new LinkedList<Micro>();
        microdata.add(new Micro("麦克风0",10,10,0));
        microdata.add(new Micro("麦克风1",20,20,0));
        microdata.add(new Micro("麦克风2",30,30,0));
        microdata.add(new Micro("麦克风3",40,40,0));
        microdata.add(new Micro("麦克风4",50,50,0));
        microdata.add(new Micro("麦克风5",60,60,0));
        microAdapter = new MicroAdapter((LinkedList<Micro>) microdata,microContext);
        micro_list.setAdapter(microAdapter);
    }

    /**
     * 获取当前sdcard目录
     * @return
     */
    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取根目录
        }
        return sdDir.toString();
    }

    /**
     * 初始化权限
     */
    private void initPermission(){
        mHasPermission = checkPermission();
        if(mHasPermission){
            //initifly();
        }else{
            requestPermission();
        }
    }

    /**
     * 检查权限
     * @return
     */
    private boolean checkPermission() {
        for(String permission:NEEDED_PERMISSIONS){
            if (ContextCompat.checkSelfPermission(MicroPhone.this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 请求权限
     */
    public void requestPermission(){
        ActivityCompat.requestPermissions(MicroPhone.this,NEEDED_PERMISSIONS,PERMISSION_REQUEST_CODE);
    }

    /**
     * 权限请求回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasAllPermission = true;
        if(requestCode == PERMISSION_REQUEST_CODE){
            for(int x:grantResults){
                if(x!=PackageManager.PERMISSION_GRANTED){
                    hasAllPermission = false;
                    break;
                }
            }
            if(hasAllPermission){
                mHasPermission = true;
               // initifly();
            }
            else {
                mHasPermission = false;
                Log.e(TAG,"拒绝权限");
            }
        }
    }

    /**
     *初始化ifly
     */
    public void initifly(){
        boolean cae = IflytekEngineManager.getInstance().createCAEEngine(mResPath);
        iflytekEngineManager = IflytekEngineManager.getInstance();
        //iflytekEngineManager.setJniLog(false);
        //iflytekEngineManager.createCAEEngine(mResPath);
        iflytekEngineManager.startRecording();
        // iflytekEngineManager.stopRecording();
        iflytekEngineManager.setCAEListener(this);
    }

    /**
     * 按钮点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.key_volume_up:
                test(1);
                currentPosition++;
                break;
            case R.id.key_volume_down:
                test(2);
                currentPosition++;
                break;
            default:
                break;
        }
    }

    /**
     * 按钮点击，根据按钮改变上一个position颜色，显示当前position数据
     * 当点击第六次的时候，改变麦克风5的颜色，显示测试完成
     * 当点击第七次的时候，跳转活动
     * @param color
     */
    public void test(int color){
         if (currentPosition <=6){
            microAdapter.change(currentPosition,color);
            int position = currentPosition-1;//用于存储正确数据
            if(color == 1){
                saveJson("麦克风" + position, "成功");
            }else{
                saveJson("麦克风" + position, "失败");
            }
            if(currentPosition == 6){
                micro_result.setText("测试完成\n请按+/-键进入结果页面");
            }
        }
        else if (currentPosition == 7) {
            Toast.makeText(MicroPhone.this,"跳转",Toast.LENGTH_SHORT).show();
            skip(MicroPhone.this,Result.class);
        }
    }

    /**
     * 音箱唤醒
     * 唤醒数据解析
     * @param s
     */
    @Override
    public void onWakeup(String s) {
        Log.e(TAG, "Wakeup:" + s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            angle = jsonObject.getString("angle");
            beam = jsonObject.getString("beam");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAudio(byte[] bytes, int i, int i1, int i2) {

    }

    @Override
    public void onError(int i, String s) {

    }

}
