package com.practice.intenttest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/14 14:31
 * @description 2.3使用Intent在活动之间穿梭
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button explicit;
    Button implicit;
    Button open_website;
    Button tel_phone;
    Button send_data;
    Button up_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        explicit = findViewById(R.id.explicit);
        implicit = findViewById(R.id.implicit);
        open_website = findViewById(R.id.website);
        tel_phone = findViewById(R.id.telphone);
        send_data = findViewById(R.id.send_data);
        up_data = findViewById(R.id.up_data);
        explicit.setOnClickListener(this);
        implicit.setOnClickListener(this);
        open_website.setOnClickListener(this);
        tel_phone.setOnClickListener(this);
        send_data.setOnClickListener(this);
        up_data.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.explicit:
                explicitIntent();
                break;
            case R.id.implicit:
                implicitIntent();
                break;
            case R.id.website:
                OpenWebsite();
                break;
            case R.id.telphone:
                TelPhone();
                break;
            case R.id.send_data:
                sendData();
                break;
            case R.id.up_data:
                upData();
                break;
                default:
                    break;
        }
    }

    private void explicitIntent(){
        Intent intent = new Intent(MainActivity.this,ExplicitIntent.class);
        startActivity(intent);
    }
    private void implicitIntent(){
        Intent intent = new Intent("com.example.activitytest.ACTION_START");
        intent.addCategory("com.example.activitytest.MY_CATEGORY");
        startActivity(intent);
    }
    private void OpenWebsite(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.baidu.com"));
        startActivity(intent);
    }
    private void TelPhone(){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }
    private void sendData(){
        Intent intent = new Intent(MainActivity.this,DataActivity.class);
        String data = "这是我想传输的数据";
        intent.putExtra("键",data);
        startActivity(intent);
    }
    private void upData(){
        Intent intent = new Intent(MainActivity.this,DataActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String returnData = data.getStringExtra("data_return");
                    Toast.makeText(MainActivity.this,"返回的数据："+returnData,Toast.LENGTH_SHORT).show();
                }
                break;
                default:
                    break;
        }
    }
}
