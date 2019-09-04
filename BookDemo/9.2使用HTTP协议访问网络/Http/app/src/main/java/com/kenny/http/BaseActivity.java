package com.kenny.http;

import android.Manifest;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class BaseActivity extends AppCompatActivity {
    public String[] NEEDED_PERMISSIONS = {Manifest.permission.INTERNET};
    boolean mHasPermission = true;                                      //是否请求权限
    boolean hasAllPermission = true;                                    //是否已申请全部权限
    private int PERMISSION_REQUEST_CODE=0;                              //请求权限码
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main:
                skip(this,MainActivity.class);
                break;
            case R.id.okhttp:
                skip(this,OkHttp.class);
                break;
            case R.id.postsome:
                skip(this,PostSome.class);
                break;
            case R.id.officalDemo:
                skip(this,OfficialDemo.class);
                break;
                default:
                    break;
        }
        return true;
    }
    /**
     * 获得栈中最顶层的Activity
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static ComponentName getTopActivity(Context context) {
        android.app.ActivityManager manager = (android.app.ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

        if (runningTaskInfos != null) {
            return (runningTaskInfos.get(0).topActivity);
        } else
            return null;
    }

    public void skip(Context oldactivity,Class newactivity){
        Intent intent = new Intent(oldactivity,newactivity);
        startActivity(intent);
    }
    /**
     * 初始化权限
     */
    public void initPermission(){
        mHasPermission = checkPermission();
        if(mHasPermission){

        }else {
            requestPermission();
        }
    }

    /**
     * 检查权限
     * @return
     */
    public boolean checkPermission(){
        for(String permission : NEEDED_PERMISSIONS){                             //遍历权限组，查看当前权限是否已赋予
            if(ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 请求权限
     */
    public void requestPermission(){
        ActivityCompat.requestPermissions(this,NEEDED_PERMISSIONS,PERMISSION_REQUEST_CODE);
    }

    /**
     * 请求权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_REQUEST_CODE){
            for (int x:grantResults){                       //grantResults 请求权限结果
                if (x!=PackageManager.PERMISSION_GRANTED){  //PERMISSION_GRANTED=0,代表该权限已申请
                    hasAllPermission = false;
                }
            }
            if(hasAllPermission){
                mHasPermission = true;
            }else {
                mHasPermission = false;
                Log.e(MainActivity.class.getSimpleName(),"拒绝权限，将影响功能实现");
            }
        }
    }
}
