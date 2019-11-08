package com.kenny.initiatingmode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class singleTopActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("singleTopActivity",this.toString());
        setContentView(R.layout.activity_singletop);
        Button click = findViewById(R.id.click);
        click.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.click:
                Intent intent = new Intent(singleTopActivity.this,MainActivity.class);
                startActivity(intent);
                break;
                default:
                    break;
        }
    }

}
