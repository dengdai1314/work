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
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;
import android.view.Window;
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

public class BaseActivity extends AppCompatActivity {

    File jsonFile;
    File sdCardDir;
    private int PERMISSION_REQUEST_CODE=0;//请求权限码
    public static List<ResultJson> saveProducts = new ArrayList<ResultJson>();//设置为静态，用于其余activity使用
    List<ResultJson> readProduct;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //去除标题
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }

            onCheckPermission();
            sdCardDir= Environment.getExternalStorageDirectory();//获取sdcard目录路径
            jsonFile=new File(sdCardDir+"/result.json");//设置json文件路径
        }

        /**
         * 请求权限
         */
        public void onCheckPermission(){
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
         * activity调用，最后ResultActivity时写入json数据到文件中，后读取文件，输出到页面中
         */
        public void saveJson(String name, String result) {
            //json数据
            saveProducts.add(new ResultJson(name, result));//存储json数据到list中
        }

        /**
         * 写入json数据到文件中
         */
        public void saveFile(){
            //创建JsonWrite对象
            try{
                FileOutputStream fos = new FileOutputStream(jsonFile);
                JsonWriter writer = new JsonWriter(new OutputStreamWriter(fos, "utf-8"));
                writer.setIndent("    ");
                writer.beginArray();
                for (ResultJson product : saveProducts) {
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

        /**
         * json文件读取
         */
        public List<ResultJson> readJson(){
            try {
                readProduct = new ArrayList<ResultJson>();
                FileInputStream fis = new FileInputStream(jsonFile);
                JsonReader reader = new JsonReader(new InputStreamReader(fis, "utf-8"));
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
                    readProduct.add(new ResultJson(name,result));
                }
                reader.endArray();
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(BaseActivity.this,readProduct.toString(),Toast.LENGTH_SHORT).show();
            return readProduct;
        }

//        SharedPreferences sharedPreferences;
//        sharedPreferences = getSharedPreferences("result", MODE_PRIVATE);
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
//         * 文件保存结果
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






