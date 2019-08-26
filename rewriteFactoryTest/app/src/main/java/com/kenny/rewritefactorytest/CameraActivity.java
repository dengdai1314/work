package com.kenny.rewritefactorytest;
/*
 *
 * File: CameraActivity.java
 * Author: 29003
 * Create: 2019/8/26 19:47
 * Changes (from 2019/8/26)
 * -----------------------------------------------------------------
 * 2019/8/26 : Create CameraActivity.java (29003);
 * -----------------------------------------------------------------
 * description:摄像头测试
 */
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.qrcode.encoder.QRCode;
import com.kenny.rewritefactorytest.zxing.android.CaptureActivity;

public class CameraActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = QRCode.class.getSimpleName();
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private static final int REQUEST_CODE_SCAN = 0x0000;
    Button wifi_scan;
    Button wifi_up;
    Button wifi_down;
    TextView scanResult;
    TextView camera_hint;
    int flag = 0;//wifi按钮按下后，点击+/-才可以进行跳转活动操作

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        wifi_scan = findViewById(R.id.key_wifi);
        wifi_up = findViewById(R.id.key_up);
        wifi_down = findViewById(R.id.key_down);
        camera_hint = findViewById(R.id.camera_hint);
        scanResult = findViewById(R.id.scanResult);
        wifi_scan.setOnClickListener(this);
        wifi_up.setOnClickListener(this);
        wifi_down.setOnClickListener(this);
        initPermission();
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
                goScan();
                break;
            case R.id.key_up:
                if(flag!=0){
                    skip(CameraActivity.this,WifiActivity.class);
                    saveJson("摄像头测试","成功");
                }
                break;
            case R.id.key_down:
                if(flag!=0){
                    skip(CameraActivity.this,WifiActivity.class);
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
        Intent intentcapture = new Intent(CameraActivity.this, CaptureActivity.class);
        startActivityForResult(intentcapture,REQUEST_CODE_SCAN);//跳转回调扫描结果
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
                String content = data.getStringExtra(DECODED_CONTENT_KEY);//getStringExtra获取数据
                //返回的BitMap图像
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
                camera_hint.setVisibility(View.VISIBLE);
                scanResult.setText("扫描结果：" + content);
                saveJson("camera", "测试成功");
            }
        }
    }
}
