package com.kenny.apidemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = MainActivity.class.getSimpleName();
    UserMessage login_userMessage;
    OkHttpClient loginclient;
    EditText login_account;
    EditText login_password;
    String input_account;
    String input_password;
    Button login;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_account = findViewById(R.id.login_account);
        login_password = findViewById(R.id.login_password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.login_register);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                input_account = login_account.getText().toString();
                input_password = login_password.getText().toString();
                postLogin();
                break;
            case R.id.login_register:
                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
                break;
            default:
                    break;
        }
    }

    public void postLogin(){
        loginclient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("username",input_account)
                .add("password",input_password)
                .build();
        final Request request = new Request.Builder()
                .url("https://www.wanandroid.com/user/login")
                .post(requestBody)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = loginclient.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.e(TAG,responseData);
                    parseJson(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJson(String jsonData){
        login_userMessage = gson.fromJson(jsonData,UserMessage.class);
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(login_userMessage.getErrorMsg() == ""){
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this,login_userMessage.getErrorMsg(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
