package com.practice.networktest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/1517:17
 * @description
 */
public class AnalysisXML extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysisxml);
        Button xmlByPull = findViewById(R.id.xmlByPull);
        Button xmlBySax = findViewById(R.id.xmlBySax);
        xmlByPull.setOnClickListener(this);
        xmlBySax.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.xmlByPull:
                sendRequestWithOkHttp();
                break;
            case R.id.xmlBySax:
                break;
                default:break;
        }
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址是电脑本机
                            //对于模拟器来说，10.0.2.2为本地地址127.0.0.1
                            .url("http://10.0.2.2/get_data.xml")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseXMLWithPull(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseXMLWithPull(String xmlData) {
        try{
            //获取到XmlPullParserFactory实例
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            //通过实例得到XmlPullParser对象
            XmlPullParser xmlPullParser = factory.newPullParser();
            //调用XmlPullParser的setInput方法将服务器返回的xml数据设置进去就可以开始解析了
            xmlPullParser.setInput(new StringReader(xmlData));
            //通过getEventType获取当前的解析事件
            int eventType = xmlPullParser.getEventType();
            //进行解析
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT){
                //获取当前节点的名字
                String nodeName = xmlPullParser.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:{
                        if("id".equals(nodeName)){
                            id = xmlPullParser.nextText();
                        }else if("name".equals(nodeName)){
                            name = xmlPullParser.nextText();
                        }else if("version".equals(nodeName)){
                            version = xmlPullParser.nextText();
                        }
                    }
                    break;
                    case XmlPullParser.END_TAG:{
                        if("app".equals(nodeName)){
                            Log.d("MainActivity","id="+id);
                            Log.d("MainActivity","name="+name);
                            Log.d("MainActivity","version="+version);
                        }
                    }
                    break;
                    default:break;
                }
                //如果当前的解析事件不等于，说明解析工资还没完成，调用next方法就可以获取下一个解析事件
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
