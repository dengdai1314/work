package com.kenny.factorytest;
/*
 *
 * File: BaseActivity.java
 * Author: 29003
 * Create: 2019/8/3 11:51
 * Changes (from 2019/8/3)
 * -----------------------------------------------------------------
 * 2019/8/3 : Create BaseActivity.java (29003);
 * -----------------------------------------------------------------
 * description:基类活动
 */

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.JsonWriter;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class BaseActivity extends AppCompatActivity {
//  SharedPreferences sharedPreferences;
    File jsonFile;
    File sdCardDir;
    private int PERMISSION_REQUEST_CODE=0;
    FileOutputStream fos;
    public static ArrayList<ResultJson> products = new ArrayList<ResultJson>();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //去除标题
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }
//   sharedPreferences = getSharedPreferences("result", MODE_PRIVATE);
           check();
        }

        /**
         * 请求权限
         */
        public void check(){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            } else {
            }
        }

        /**
         * 权限回调
         * @param requestCode
         * @param permissions
         * @param grantResults
         */
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == PERMISSION_REQUEST_CODE){
            }else {
                Toast.makeText(this,"拒绝权限，将无法使用程序。", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        /**
         * 跳转活动
         * @param oldactivity
         * @param newactivity
         */
        public void skip(Context oldactivity, Class newactivity) {
            Intent intent = new Intent(oldactivity, newactivity);
            startActivity(intent);
            finish();
        }

        /**
         * 保存测试结果为json数据
         */
        public void saveJson(String name, String result) {
            //json数据
            products.add(new ResultJson(name, result));
        }

        /**
         * 写入json数据到文件中
         */
        public void saveFile(){
            //创建JsonWrite对象
            try{
                sdCardDir= Environment.getExternalStorageDirectory();
                jsonFile=new File(sdCardDir+"/result.json");
                fos = new FileOutputStream(jsonFile);
                JsonWriter writer = new JsonWriter(new OutputStreamWriter(fos, "utf-8"));
                writer.setIndent("    ");
                writer.beginArray();
                for (ResultJson product : products) {
                    writer.beginObject();
                    writer.name("name").value(product.getName());
                    writer.name("result").value(product.getResult());
                    writer.endObject();
                }
                writer.endArray();
                Log.e(String.valueOf(BaseActivity.this),"保存成功");
                writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

//        /**
//         * 保存测试结果(sharedpreferences)
//         * @param key
//         * @param value
//         */
//        public void save (String key,String value){
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString(key,value);
//            editor.apply();
//        }


//        /**
//         * 保存测试结果
//         * @param key
//         * @param value
//         */
//        public void save (String key,String value){
//            FileOutputStream out = null;
//            BufferedWriter writer = null;
//            try{
//                out = openFileOutput("data", Context.MODE_PRIVATE);
//                writer = new BufferedWriter(new OutputStreamWriter(out));
//                writer.write(key:value);
//            }catch (IOException e){
//                e.printStackTrace();
//            }finally {
//                try{
//                    if(writer!=null){
//                        writer.close();
//                    }
//                }catch (IOException E){
//                    E.printStackTrace();
//                }
//            }
//        }

//        /**
//         * 读取文件
//         */
//        public String load(){
//            FileInputStream in = null;
//            BufferedReader reader = null;
//            StringBuilder content = new StringBuilder();
//            try{
//                in = openFileInput("data");
//                reader = new BufferedReader(new InputStreamReader(in));
//                String line = "";
//                while ((line = reader.readLine())!=null){
//                    content.append(line);
//                }
//            }catch (IOException e){
//                e.printStackTrace();
//            }finally {
//                if(reader != null){
//                    try{
//                        reader.close();
//                    }catch (IOException e){
//                        e.printStackTrace();
//                    }
//                }
//            }
//            return content.toString();
//        }

}






