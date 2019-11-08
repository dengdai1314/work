package com.demo.eventbus;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class EventBusActivity1 extends CommonActivity<ActivityEventBus1Binding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus1);
    }
}
