package com.kenny.controltest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/14 14:04
 * @description 3.2常用控件的使用方法：TextView,Button,ImageView,ProgressBar,
 *  *              AlertDialog,ProgressDialog
 */
/**
     * 按钮监听事件
     * 匿名类的方式注册监听器
     */
    public class MainActivity extends AppCompatActivity {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                Button button1 = (Button) findViewById(R.id.button_1);
                Button button2 = (Button) findViewById(R.id.button_2);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"you click button_1",Toast.LENGTH_SHORT).show();
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"you click button_2",Toast.LENGTH_SHORT).show();
                    }
                });
            }
    }

    /**
     * 按钮监听事件
     * 使用接口的方式注册监听器
     */
    public class MainActivity extends AppCompatActivity implements View.OnClickListener{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Button button1 = (Button) findViewById(R.id.button_1);
            Button button2 = (Button) findViewById(R.id.button_2);
            button1.setOnClickListener(this);
            button2.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_1:
                    Toast.makeText(MainActivity.this,"you click button_1",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_2:
                    Toast.makeText(MainActivity.this,"you click button_2",Toast.LENGTH_SHORT).show();
                    break;
                    default:
                        break;
            }
        }
}

    /**
     * 点击按钮获取Edittext文本内容并toast展示
     */
    public class MainActivity extends AppCompatActivity implements View.OnClickListener{
        private EditText editText;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Button button1 = (Button) findViewById(R.id.button_1);
            editText = (EditText) findViewById(R.id.edit_text);
            button1.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_1:
                    String inputtext = editText.getText().toString();
                    Toast.makeText(MainActivity.this,inputtext,Toast.LENGTH_SHORT).show();
                    break;
                    default:
                        break;
            }
        }
}

/**
     * 点击按钮获取Edittext文本内容并toast展示
     */
    public class MainActivity extends AppCompatActivity implements View.OnClickListener{
        private EditText editText;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Button button1 = (Button) findViewById(R.id.button_1);
            editText = (EditText) findViewById(R.id.edit_text);
            button1.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_1:
                    String inputtext = editText.getText().toString();
                    Toast.makeText(MainActivity.this,inputtext,Toast.LENGTH_SHORT).show();
                    break;
                    default:
                        break;
            }
        }
}

/**
     * 点击按钮切换切换照片
     */
    public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        private ImageView imageView;
        int x=0;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Button button1= (Button) findViewById(R.id.button_1);
            imageView = (ImageView) findViewById(R.id.image_view);
            button1.setOnClickListener(this);
        }

        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_1:
                    x++;
                    if(x%2==0){
                        imageView.setImageResource(R.drawable.img_1);
                    }
                    else{
                        imageView.setImageResource(R.drawable.img_2);
                    }
                    Toast.makeText(MainActivity.this,x+"",Toast.LENGTH_SHORT).show();
                    break;
                    default:
                        break;
            }

        }
}

        /**
         * 点击按钮切换进度条显示或隐藏
         */
        public class MainActivity extends AppCompatActivity implements View.OnClickListener {
            private ProgressBar progressBar;

            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                Button button1 = (Button) findViewById(R.id.button_1);
                button1.setOnClickListener(this);
                progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            }

            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_1:
                        if (progressBar.getVisibility() == View.GONE)
                            progressBar.setVisibility(View.VISIBLE);
                        else {
                            progressBar.setVisibility(View.GONE);
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        /**
         * 进度条改为水平
         */
        public class MainActivity extends AppCompatActivity implements View.OnClickListener {
            private ProgressBar progressBar;

            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                Button button1 = (Button) findViewById(R.id.button_1);
                button1.setOnClickListener(this);
                progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            }

            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_1:
                        int progress = progressBar.getProgress();
                        progress = progress + 1;
                        progressBar.setProgress(progress);
                        break;
                    default:
                        break;
                }
            }
        }

        /**
         * 点击按钮弹出对话框
         */
        public class MainActivity extends AppCompatActivity implements View.OnClickListener{

            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                Button button1 = (Button) findViewById(R.id.button_1);
                button1.setOnClickListener(this);
            }

            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_1:
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("This is a Dialog");
                        dialog.setMessage("something is important");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        dialog.show();
                        break;
                    default:
                        break;
                }
            }
        }

/**
         * 点击按钮弹出对话框及进度条
         */
        public class MainActivity extends AppCompatActivity implements View.OnClickListener{

            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                Button button1 = (Button) findViewById(R.id.button_1);
                button1.setOnClickListener(this);
            }

            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_1:
                        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                        progressDialog.setTitle("This is a ProgressDialog");
                        progressDialog.setMessage("waiting...");
                        progressDialog.setCancelable(false);//设置对话框是否可关闭，false为不允许关闭
                        progressDialog.show();
                        break;
                    default:
                        break;
                }
            }
        }







