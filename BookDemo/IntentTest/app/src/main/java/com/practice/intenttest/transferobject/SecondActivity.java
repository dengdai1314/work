package com.practice.intenttest.transferobject;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/2210:51
 * @description
 */
public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Person person = (Person) getIntent().getSerializableExtra("person_data");
        Airplane airplane = getIntent().getParcelableExtra("person");
        Log.d("Serializable data =",person.getName()+person.getAge());
        Log.d("Parcelable data = ",airplane.getName()+airplane.getAge());
    }
}
