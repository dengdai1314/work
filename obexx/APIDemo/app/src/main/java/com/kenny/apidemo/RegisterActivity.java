package com.kenny.apidemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    private final String TAG = RegisterActivity.class.getSimpleName();
    UserMessage register_userMessage;
    OkHttpClient registerclient;
    EditText register_account;
    EditText register_password;
    EditText register_repassword;
    String input_reaccount;
    String input_password;
    String input_repassword;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register_account = findViewById(R.id.register_account);
        register_password = findViewById(R.id.register_password);
        register_repassword = findViewById(R.id.register_repassword);
        register = findViewById(R.id.register_register);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_register:
                input_reaccount = register_account.getText().toString();
                input_password = register_password.getText().toString();
                input_repassword = register_repassword.getText().toString();
                postRegister();
                break;
                default:
                    break;
        }
    }
    public void postRegister(){
        registerclient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("username",input_reaccount)
                .add("password",input_password)
                .add("repassword",input_repassword)
                .build();
        final Request request = new Request.Builder()
                .url("https://www.wanandroid.com/user/register")
                .post(requestBody)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = registerclient.newCall(request).execute();
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
        register_userMessage = gson.fromJson(jsonData,UserMessage.class);
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(register_userMessage.getErrorMsg() == ""){
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegisterActivity.this,register_userMessage.getErrorMsg(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
