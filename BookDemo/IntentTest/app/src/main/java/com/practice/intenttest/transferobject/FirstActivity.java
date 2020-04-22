package com.practice.intenttest.transferobject;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/2210:49
 * @description
 */
public class FirstActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Person person = new Person();
        person.setName("Tom");
        person.setAge(20);

        Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
        intent.putExtra("person_data",person);

        Airplane airplane = new Airplane();
        airplane.setName("Tom");
        airplane.setAge(20);

        intent.putExtra("person",airplane);
        startActivity(intent);
    }
}
