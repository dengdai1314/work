package com.kenny.json_read_write;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/14 14:31
 * @description json数据读取与写入
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button wjson;
    Button rjson;
    TextView test;
    private int PERMISSION_REQUEST_CODE=0;

    File sdCardDir;
    File jsonFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test = findViewById(R.id.ttvwJson);
        wjson = findViewById(R.id.btnSingleWJson);
        rjson = findViewById(R.id.btnSingleRJson);
        wjson.setOnClickListener(this);
        rjson.setOnClickListener(this);
        sdCardDir= Environment.getExternalStorageDirectory();
        jsonFile=new File(sdCardDir+"/result.json");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSingleWJson:
                // 判断有无写SD卡的权限
                //申请写 SD 卡的权限
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                } else {

                }
                break;
            case R.id.btnSingleRJson:
                readJson();
                break;
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
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//            saveJson();
        }else {
            Toast.makeText(this,"拒绝权限，将无法使用程序。", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    /**
     * 添加json数据
     */
    public void saveJson(String name,String result) {
        Toast.makeText(this, "保存独立Json文件", Toast.LENGTH_LONG).show();
        //打开要写入的json文件
        try{
            FileOutputStream fos = new FileOutputStream(jsonFile);
            //json数据
            ArrayList<Product> products = new ArrayList<Product>();
            products.add(new Product("1", "2"));
//            创建JsonWrite对象
            JsonWriter writer = new JsonWriter(new OutputStreamWriter(fos, "utf-8"));
            writer.setIndent("    ");
            writer.beginArray();
            for (Product product : products) {
                writer.beginObject();
                writer.name("name").value(product.getName());
                writer.name("result").value(product.getResult());
                writer.endObject();
            }
            writer.endArray();
            Log.e(String.valueOf(MainActivity.this),"保存成功");
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readJson(){
        try {
            FileInputStream fis = new FileInputStream(jsonFile);
            ArrayList<Product> readProduct = new ArrayList<Product>();
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
                readProduct.add(new Product(name,result));
            }
            reader.endArray();
            reader.close();
            if(readProduct != null){
                String result = "";
                for (Product product : readProduct){
                    result+="name:"+product.getName()+"result"+product.getResult()+"\n";
                    test.setText(result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
