package com.practise.androidthreadtest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/5/14 16:00
 * @description 轻量级异步任务类，可以在线程池中执行后台任务，然后把执行的进度和结果传递给主线程并且在主线程中更新UI
 */
public class AsyncTaskTest extends AppCompatActivity {

    // 线程变量
    MyTask myTask;

    // 主布局中的UI组件
    Button button,cancel; // 加载、取消按钮
    private static TextView text; // 更新的UI组件
    private static ProgressBar progressBar; // 进度条

    /**
     * 步骤1：创建AsyncTask子类
     * 注：
     *   a. 继承AsyncTask类
     *   b. 为3个泛型参数指定类型；若不使用，可用java.lang.Void类型代替
     *      此处指定为：输入参数Params = String类型、执行进度progress = Integer类型、执行结果result = String类型
     *   c. 根据需求，在AsyncTask子类内实现核心方法
     */
    static class MyTask extends AsyncTask<String,Integer,String>{

        // 方法1：onPreExecute（）
        // 作用：执行 线程任务前的操作
        // 在主线程中执行，任务开启前的准备工作
        // 注：根据需求复写
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            text.setText("加载中");
            // 执行前显示提示
        }

        // 方法2：doInBackground（）
        // 作用：接收输入参数、执行任务中的耗时操作、返回 线程任务执行的结果
        // 此处通过计算从而模拟“加载进度”的情况
        // 开启子线程执行后台任务
        // 注：必须复写，从而自定义线程任务
        @Override
        protected String doInBackground(String... strings) {
            // 自定义的线程任务
            try {
                int count = 0;
                int length = 1;
                while (count<100){
                    count += length;
                    // 可调用publishProgress（）显示进度, 之后将执行onProgressUpdate（）
                    publishProgress(count);
                    // 模拟耗时任务
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        // 方法3：onProgressUpdate（）
        // 作用：在主线程 显示线程任务执行的进度
        // 在主线程中执行，更新UI进度
        // 注：根据需求复写
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            text.setText("loading..."+values[0]+"%");
        }

        // 方法4：onPostExecute（）
        // 作用：接收线程任务执行结果、将执行结果显示到UI组件
        // 在主线程中执行，异步任务执行完成后执行，它的参数时doInbackground()的返回值
        // 注：必须复写，从而自定义UI操作
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // 执行完毕后，则更新UI
            text.setText("加载完毕");
        }

        // 方法5：onCancelled()
        // 作用：将异步任务设置为：取消状态
        @Override
        protected void onCancelled() {
            super.onCancelled();
            text.setText("已取消");
            progressBar.setProgress(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_task_test);

        button = (Button) findViewById(R.id.button);
        cancel = (Button) findViewById(R.id.cancel);
        text = (TextView) findViewById(R.id.text);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        /**
         * 步骤2：创建AsyncTask子类的实例对象（即 任务实例）
         * 注：AsyncTask子类的实例必须在UI线程中创建
         */
        myTask = new MyTask();

        // 加载按钮按按下时，则启动AsyncTask
        // 任务完成后更新TextView的文本
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 步骤3：手动调用execute(Params... params) 从而执行异步线程任务
                 * 注：
                 *    a. 必须在UI线程中调用
                 *    b. 同一个AsyncTask实例对象只能执行1次，若执行第2次将会抛出异常
                 *    c. 执行任务中，系统会自动调用AsyncTask的一系列方法：onPreExecute() 、doInBackground()、onProgressUpdate() 、onPostExecute()
                 *    d. 不能手动调用上述方法
                 */
                //AsyncTask线程只能运行一次，不能重复运行，也就是说按钮不能点两次
                myTask.execute();

            }
        });
        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取消一个正在执行的任务,onCancelled方法将会被调用
                myTask.cancel(true);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myTask.cancel(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}

