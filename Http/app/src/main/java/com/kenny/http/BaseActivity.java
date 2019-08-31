package com.kenny.http;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main:
                skip(this,MainActivity.class);
                break;
            case R.id.okhttp:
                skip(this,OkHttp.class);
                break;
            case R.id.postsome:
                skip(this,PostSome.class);
        }
        return true;
    }
    /**
     * 获得栈中最顶层的Activity
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static ComponentName getTopActivity(Context context) {
        android.app.ActivityManager manager = (android.app.ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

        if (runningTaskInfos != null) {
            return (runningTaskInfos.get(0).topActivity);
        } else
            return null;
    }

    public void skip(Context oldactivity,Class newactivity){
        Intent intent = new Intent(oldactivity,newactivity);
        startActivity(intent);
    }
}
