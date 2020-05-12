package com.practise.androidthreadtest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/14 13:56
 * @description 10.2多线程练习
 */
public class MainActivity extends AppCompatActivity {
    TextView text;
    Button changeText;//子线程
    Button changeText2;//异步消息处理
    Button threadTest1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        changeText = findViewById(R.id.change_text);
        changeText2 = findViewById(R.id.change_text2);
        threadTest1 = findViewById(R.id.thread_test1);
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
        threadTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ThreadTest.class);
                startActivity(intent);
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

    /**
     * 方法中所有代码都会在子线程中运行，应该在这里去处理所有耗时任务。
     * 通过return返回任务结果。可通过修改AsyncTask中第三个参数为void就可以不返回任务执行结果
     * 注：不可以进行UI操作
     * 如果需要更新UI元素，比如说反馈当前任务的执行进度，可以调用publishProgress (Progress...) 方法来完成
     * @param voids
     * @return
     */
    @Override
    protected Boolean doInBackground(Void... voids) {
        return null;
    }

    /**
     * 在后台任务开始执行之前调用，用于进行一下界面上的初始化操作，比如显示一个进度条对话框等
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * 当在后台任务中调用了publishProgress(Progress...) 方法后，onProgressUpdate (Progress...) 方法就会很快被调用，
     * 该方法中携带的参数就是在后台任务中传递过来的
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    /**
     * 当后台任务执行完毕并通过return 语句进行返回时，这个方法就
     * 很快会被调用。返回的数据会作为参数传递到此方法中，可以利用
     * 返回的数据来进行一些UI操作，比如说提醒任务执行的结果，以及
     * 关闭掉进度条对话框等。
     * @param aBoolean
     */
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }


}
