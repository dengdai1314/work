package com.coolweather.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/23 14:20
 * @description 开发酷欧天气(含自定义view)
 */
public class MainActivity extends AppCompatActivity {

    private long mExitTime;
    View toastRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences
                (this);
        if (prefs.getString("weather", null) != null) {
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
            finish();
        }
        toastRoot = getLayoutInflater().inflate(R.layout.my_toast,null);
    }


    //实现再按一次退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //自定义toast
//                Toast toast = new Toast(getApplicationContext());
//                toast.setView(toastRoot);
//                TextView tv = toastRoot.findViewById(R.id.TextViewInfo);
//                tv.setText("说明：这是一个自定义边框和底色的提示框");
//                toast.show();
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if((currentTime-mExitTime)>=2000) {
            //让Toast的显示时间和等待时间相同
            Toast.makeText(this, "再按一次退出", (int)mExitTime).show();
            mExitTime = currentTime;
        }else {
            finish();
        }
    }
}

