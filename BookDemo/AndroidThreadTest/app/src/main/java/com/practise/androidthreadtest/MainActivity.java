package com.practise.androidthreadtest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView text;
    Button changeText;//子线程
    Button changeText2;//异步消息处理
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        changeText = findViewById(R.id.change_text);
        changeText2 = findViewById(R.id.change_text2);
        changeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        text.setText("测试");
                    }
                }).start();
            }
        });
        changeText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();//创建Message对象
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);//调用Handler的sendMessage()将Message发送出去
                    }
                }).start();
            }
        });
    }

    public static final int UPDATE_TEXT = 1;
    //Handler会收到Message,并在handleMessage()方法中进行处理，此时处理的代码是在主线程中进行处理
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE_TEXT:
                    //进行UI操作
                    text.setText("测试（异步消息）");
                    break;
                    default:break;
            }
        }
    };
}
class DownloadTask extends AsyncTask<Void,Integer,Boolean>{

    @Override
    protected Boolean doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
