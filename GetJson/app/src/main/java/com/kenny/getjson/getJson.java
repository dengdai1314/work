package com.kenny.getjson;

import android.Manifest;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 29003
 * @description
 * @date 2019/7/16
 */
public class getJson {
    //        用的时候将下面代码粘贴到MainActivity.java里的oncreate方法里
    //        //StringBuilder：拼接字符串
    StringBuilder stringBuilder = new StringBuilder();
    try {
        //getclass:获取对象当前的class类型;
        // getClassLoader:得到当前类型的类加载器;
        // getResourceAsStream:调用类加载器的getResourceAsStream（）方法加载资源
        //InputStreamReader isr = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("assets/"+"RoleDatabase.json"));

        //创建InputStreamReader对象
        //读取assets文件夹下文件，getAssets().open()
        //InputStreamReader:通过相应的字符编码方式读取字节输入流解码为字符输入流，处理字节
        InputStreamReader isr = new InputStreamReader(MainActivity.this.getAssets().open("RoleDatabase.json"));

        //BufferedReader:字符输入流中的子类,读取字符流，处理字符文本
        //创建BufferedReader类对象
        //把byte数据输入流读取到缓冲区
        //对字符输入流读取文本并将字符存入缓冲区
        BufferedReader bfr = new BufferedReader(isr);

        String line;

        //readLine():读取一个文本行，并将其返回为字符串。若无数据可读，则返回null
        //循环的从缓存区读取数据（一行一行读取），拼接到一个字符串中
        while((line = bfr.readLine())!=null){
            //将指定的字符串追加到此字符序列。
            stringBuilder.append(line);
        }

        //json字符串转换，后放到一个实体类中
        //jsonArray:json数组
        JSONArray jsonArray = new JSONArray(stringBuilder.toString());

        //创建集合/数组
        List<Role> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){

            //读取数组，将数组变为对象
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            //创建对象
            Role role = new Role();

            //optInt:获取指定字段的int值，若字段不存在，返回0
            //optString:获取指定字段的string值，若字段不存在，返回null
            //getInt: 获取指定字段的int值，若字段不存在，则抛出异常
            role.setWdID(jsonObject.optInt("wdID"));
            role.setStrName(jsonObject.optString("strName"));

            //将对象存储到list数组里
            list.add(role);
        }
        Log.d("onCreate",list.toString());
    }catch (IOException e) {
        e.printStackTrace();
    }//InputStreamReader错误时异常
    catch (JSONException e) {
        e.printStackTrace();
    }//BufferedReader错误时异常

//      String result = getJson("RoleDatabase.json");
//      try{
//          List<Role> list = new ArrayList<>();
//                JSONArray jsonArray = new JSONArray(result);
//                for (int i = 0; i < jsonArray.length(); i++){
//                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
//                    Role role = new Role();
//                    role.setWdID(jsonObject.optInt("wdID"));
//                    role.setStrName(jsonObject.optString("strName"));
//                    list.add(role);
//                }
//                Log.d("onCreate",list.toString());
//            }
//            catch (JSONException e) { e.printStackTrace(); }
//        }
//
//    public String getJson (String filename){
//        StringBuilder stringBuilder = new StringBuilder();
//        try {
//            AssetManager assetManager = MainActivity.this.getAssets();
//            BufferedReader bfr = new BufferedReader(new InputStreamReader(assetManager.open(filename)));
//            String line;
//            while ((line = bfr.readLine()) != null) {
//                stringBuilder.append(line);
//            }
//        }
//            catch (IOException e) {
//                e.printStackTrace();
//            }
//            return stringBuilder.toString();
//    }

}
