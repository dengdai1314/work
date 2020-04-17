package com.practice.networktest.util;

import com.practice.networktest.HttpCallbackListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/1610:28
 * @description 9.5网络编程的最佳实践
 */
public class HttpUtil {
    public static void sendHttpRequest(final String address, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener != null) {
                        //回调onFinish()方法
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        //回调onError方法
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();

//        使用：
//        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
//            @Override
//            public void onFinish(String response) {
//                //在这里根据返回内容执行具体逻辑
//            }
//
//            @Override
//            public void onError(Exception e) {
//                //在这里对异常情况进行处理
//            }
//        });

    }


    //okhttp3.callback为OkHttp自带的一个回调接口
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        //调用enqueue方法，Okhttp在enqueue方法内部已经开好子线程，然后会在子线程中去执行HTTP请求，并回调结果到okHttp3.Callback
        client.newCall(request).enqueue(callback);

//        使用：
//        HttpUtil.sendOkHttpRequest("http://www.baidu.com", new okhttp3.Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                //在这里对异常情况进行处理
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                //得到服务器返回的具体内容
//                String responseData = response.body().string();
//            }
//        });
    }
}
