package com.kenny.filestore;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class JsonStore extends AppCompatActivity {
    File sdCardDir;
    File jsonFile;
    public String[] NEEDED_PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    boolean mHasPermission = true;                                      //是否请求权限
    boolean hasAllPermission = true;                                    //是否已申请全部权限
    private int PERMISSION_REQUEST_CODE=0;                              //请求权限码
    public static List<Result> saveData = new ArrayList<Result>();           //设置为静态，用于其余activity保存json数据使用
    public static List<Result> readData = new ArrayList<Result>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)){
            sdCardDir= Environment.getExternalStorageDirectory();           //获取sdcard目录路径
            jsonFile = new File(sdCardDir+"/result.json");        //设置json文件路径，存储json数据
            initPermission();                                               //初始化权限
        }else{
            Toast.makeText(JsonStore.this,"无SDCard,无法进行后续操作",Toast.LENGTH_SHORT).show();
        }
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
                Log.e(JsonStore.class.getSimpleName(),"拒绝权限，将影响功能实现");
            }
        }
    }

    /**
     * 保存测试结果为json数据
     * activity调用，最后ResultActivity时写入json数据到文件中，后读取文件，输出到页面中
     */
    public void saveJson(String name, String result) {
        saveData.add(new Result(name, result));    //存储json数据到list中
    }

    /**
     * 写入json数据到文件中
     */
    public void saveJsonFile(){
        try{
            FileOutputStream fos = new FileOutputStream(jsonFile);
            //创建JsonWrite对象
            JsonWriter writer = new JsonWriter(new OutputStreamWriter(fos, "utf-8"));
            writer.setIndent("    ");
            writer.beginArray();
            for (Result product : saveData) {     //遍历json数据
                writer.beginObject();
                writer.name("name").value(product.getName());
                writer.name("result").value(product.getResult());
                writer.endObject();
            }
            writer.endArray();
            Log.e(String.valueOf(JsonStore.this),"保存成功");
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * json文件读取
     * @return
     */
    public List<Result> readJson(){
        try{
            FileInputStream fileInputStream = new FileInputStream(jsonFile);
            //创建JsonReader对象
            JsonReader reader = new JsonReader(new InputStreamReader(fileInputStream,"utf-8"));
            reader.beginArray();
            while (reader.hasNext()){
                String name = "";
                String result = "";
                reader.beginObject();
                while (reader.hasNext()){
                    String field = reader.nextName();
                    if(field.equals("name")){
                        name = reader.nextString();
                    }else if (field.equals("result")){
                        result = reader.nextString();
                    }else {
                        reader.skipValue();
                    }
                }
                reader.endObject();
                readData.add(new Result(name,result));
            }
            reader.endArray();
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return readData;
    }
}
