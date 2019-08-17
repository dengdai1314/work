package com.kenny.factorytest;
/*
 *
 * File: QRCode.java
 * Author: 29003
 * Create: 2019/8/3 11:56
 * Changes (from 2019/8/3)
 * -----------------------------------------------------------------
 * 2019/8/3 : Create QRCode.java (29003);
 * -----------------------------------------------------------------
 * description:摄像头测试
 */

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kenny.factorytest.zxing.android.CaptureActivity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class QRCode extends BaseActivity implements View.OnClickListener {

    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private static final int REQUEST_CODE_SCAN = 0x0000;
    String TAG = QRCode.class.getSimpleName();
    Button wifi_scan;
    Button wifi_up;
    Button wifi_down;
    TextView tv_scanResult;
    TextView camera_hint;
    int flag = 0;//wifi按钮按下后，点击+/-才可以操作

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_frist);
        wifi_scan = findViewById(R.id.key_wifi);
        wifi_up = findViewById(R.id.key_volume_up);
        wifi_down = findViewById(R.id.key_volume_down);
        camera_hint = findViewById(R.id.camera_hint);
        tv_scanResult = findViewById(R.id.tv_scanResult);
        wifi_scan.setOnClickListener(this);
        wifi_up.setOnClickListener(this);
        wifi_down.setOnClickListener(this);
    }

    /**
     * 按钮点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.key_wifi:
                flag = flag+1;//wifi按钮按下，flag+1,然后音量+/-键才可以进行操作
                //动态权限申请
                //请求camera权限
                if (ContextCompat.checkSelfPermission(QRCode.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(QRCode.this, new String[]{Manifest.permission.CAMERA}, 1);//请求权限
                } else {
                    goScan();//跳转扫描页面开始扫描
                }
                break;
            case R.id.key_volume_up:
                if(flag!=0){
                    skip(QRCode.this,WifiScan.class);
                    saveJson("摄像头测试","成功");
                }
                break;
            case R.id.key_volume_down:
                if(flag!=0){
                    skip(QRCode.this,WifiScan.class);
                    saveJson("摄像头测试","失败");
                }
                break;
            default:
                break;
        }
    }
    /**
     * 跳转到扫码界面扫码
     */
    private void goScan(){
        Log.d(TAG,"跳转扫描界面");
        Intent intentcapture = new Intent(QRCode.this, CaptureActivity.class);
        startActivityForResult(intentcapture,REQUEST_CODE_SCAN);//回调扫描结果
    }

    /**
     * 请求权限结果回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                //如果权限获取结果长度>0 及权限获取结果（如果权限获取了，结果为0，获取不了，为-1）第一个等于PERMISSION_GRANTED（0）
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goScan();
                } else {
                    Toast.makeText(this, "你拒绝了权限申请，可能无法扫码", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(QRCode.this, new String[]{Manifest.permission.CAMERA}, 1);//请求权限
                }
                break;
            default:
        }
    }

    /**
     * 活动回传扫描结果
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                //返回的文本内容
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                //返回的BitMap图像
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
                camera_hint.setVisibility(View.VISIBLE);
                tv_scanResult.setText("扫描结果：" + content);
                saveJson("camera","测试成功");
            }
        }
    }
}
