package com.practice.networktest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/16 9:22
 * @description 9.4解析JSON格式数据
 */
public class AnalysisByJson extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_by_json_object);
        Button jsonByJsonObject = findViewById(R.id.jsonByJsonObject);
        Button jsonByGson = findViewById(R.id.jsonByGSON);
        jsonByJsonObject.setOnClickListener(this);
        jsonByGson.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jsonByJsonObject :
                ParseJson();
                break;
            case R.id.jsonByGSON:
                ParseJson();
                break;
                default:
                    break;

        }
    }

    private void ParseJson() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址是电脑本机
                            //对于模拟器来说，10.0.2.2为本地地址127.0.0.1
                            .url("http://10.0.2.2/get_data.json")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJsonWithJSONObject(responseData);
//                    parseJsonWithGson(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJsonWithJSONObject(String jsonData) {
        try {
            //定义一个json数组
            JSONArray jsonArray = new JSONArray(jsonData);
            //遍历数组
            for(int i=0;i<jsonArray.length();i++){
                //每一个元素都是一个JSONObject对象，每一个JSONObject对象中又含有id，name，version这些数据
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.d("MainActivity","id="+id);
                Log.d("MainActivity","name"+name);
                Log.d("MainActivity","version="+version);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseJsonWithGson(String jsonData){

    }
}
