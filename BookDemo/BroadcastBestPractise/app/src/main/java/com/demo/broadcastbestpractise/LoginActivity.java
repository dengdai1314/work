package com.demo.broadcastbestpractise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * @author 罗火金
 * @description
 * @date 2020/4/4
 */
public class LoginActivity extends BaseActivity {
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if(account.equals("admin")&&password.equals("123456")){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"account or password invalid", LENGTH_SHORT).show();
                }
            }
        });
    }
}
