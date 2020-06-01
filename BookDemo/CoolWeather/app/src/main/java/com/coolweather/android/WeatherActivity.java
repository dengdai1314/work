package com.coolweather.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coolweather.android.gson.Forecast;
import com.coolweather.android.gson.Weather;
import com.coolweather.android.service.AutoUpdateService;
import com.coolweather.android.util.HttpUtil;
import com.coolweather.android.util.Utility;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/23 16:39
 * @description 请求天气数据以及将数据展示到界面上
 */
public class WeatherActivity extends AppCompatActivity {

    //初始化各控件
    @BindView(R.id.weather_layout) ScrollView weatherLayout;
    @BindView(R.id.title_city) TextView titleCity;
    @BindView(R.id.title_update_time) TextView titleUpdateTime;
    @BindView(R.id.degree_text) TextView degreeText;
    @BindView(R.id.weather_info_text) TextView weatherInfoText;
    @BindView(R.id.forecast_layout) LinearLayout forecastLayout;
    @BindView(R.id.somatosensory_text) TextView somaText;
    @BindView(R.id.humidity_text) TextView humText;
    @BindView(R.id.comfort_text) TextView comfortText;
    @BindView(R.id.clothes_text) TextView clothesText;
    @BindView(R.id.sport_text) TextView sportText;
    @BindView(R.id.influenza_text) TextView influText;
    @BindView(R.id.UV_text) TextView uvText;
    @BindView(R.id.bing_pic_img) ImageView bingPicImg;
    @BindView(R.id.swipe_refresh) SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.nav_button) Button navButton;

    private String mWeatherId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除系统状态栏，使背景图与状态栏融合到一起
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            //获取当前活动的DecorView
            View decorView = getWindow().getDecorView();
            //调用setSystemUiVisibility改变系统UI显示
            // View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE表示活动的布局会显示在状态栏上面
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            //将状态栏设置为透明色
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);

        // 初始化各控件
        ButterKnife.bind(this);

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        //获取SharedPreferences实例
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //通过SharedPreferences实例获取weather缓存
        String weatherString = prefs.getString("weather",null);
        if(weatherString != null){
            //有缓存时直接解析天气数据
            Weather weather = Utility.handleWeatherResponse(weatherString);
            mWeatherId = weather.basic.weatherId;
            showWeatherInfo(weather);
        }else {
            //无缓存时去服务器查询天气
            mWeatherId = getIntent().getStringExtra("weather_id");//从Intent中取出天气ID
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(mWeatherId);//从服务器请求天气数据
        }

        //下拉刷新
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this);
                String weatherId = prefs.getString("weather_id",mWeatherId);
                requestWeather(weatherId);
            }
        });

        //获取图片缓存
        String bingPic = prefs.getString("bing_pic",null);
        if(bingPic!=null){
            Glide.with(this).load(bingPic).into(bingPicImg);
        }else {
            loadBingPic();
        }

        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    /**
     * 根据天气ID请求城市天气信息
     * @param weatherId
     */
    public void requestWeather(final String weatherId){
        String weatherUrl = "https://free-api.heweather.net/s6/weather/?location="+weatherId+"&key=39d77d4bf6644d498690b413b6e32e5d";
//        String weatherUrl = "http://guolin.tech/api/weather?cityid="+weatherId+"&key=aab49854e8574280b984d7ad25774314";//拼接接口地址
//        this.mWeatherId = weatherId;
        //发出请求，服务器会将相应城市的天气信息以json格式返回
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this,"获取天气信息失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String responseText = response.body().string();//获取返回数据
                Log.d("test",responseText);
                final Weather weather = Utility.handleWeatherResponse(responseText);//将返回的数据转换成Weather对象
                //切换子线程
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //如果返回数据中status为ok,就说明请求成功
                        if(weather!=null&&"ok".equals(weather.status)){
                            //保存数据到SharedPreferences,并展示数据
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather",responseText);
                            editor.apply();
                            showWeatherInfo(weather);
                        }else {
                            Toast.makeText(WeatherActivity.this,"获取天气信息失败",Toast.LENGTH_SHORT).show();
                        }
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        });
        loadBingPic();
    }

    /**
     * 处理并展示Weather实体类中的数据
     * @param weather
     */
    private void showWeatherInfo(Weather weather) {
        String cityName = weather.basic.cityName;
        String updateTime = weather.update.updateTime.split(" ")[1];
        String degree = weather.now.temperature + "℃";
        String weatherInfo = weather.now.info;
        titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);
        somaText.setText(weather.now.feeling);
        humText.setText(weather.now.humidity);
        forecastLayout.removeAllViews();
        for (Forecast forecast : weather.forecastList) {
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false);
            TextView dateText = (TextView) view.findViewById(R.id.date_text);
            TextView infoText = (TextView) view.findViewById(R.id.info_text);
            TextView maxText = (TextView) view.findViewById(R.id.max_text);
            TextView minText = (TextView) view.findViewById(R.id.min_text);
            TextView humText = (TextView) view.findViewById(R.id.hum_text);
            TextView rainfailText = (TextView) view.findViewById(R.id.rainfail_text);
            dateText.setText(forecast.date);
            infoText.setText(forecast.more);
            maxText.setText(forecast.max);
            minText.setText(forecast.min);
            humText.setText(forecast.humidity);
            rainfailText.setText(forecast.rainfall);
            forecastLayout.addView(view);//动态加载布局
        }
        String comfort = null;
        String clothes = null;
        String sport = null;
        String influenza = null;
        String UV = null;
        for(int i=0;i<weather.suggestionList.size();i++){
            switch (weather.suggestionList.get(i).type){
                case "comf":
                    comfort = "舒适度：" + weather.suggestionList.get(i).suggestion;
                    break;
                case "drsg":
                    clothes = "穿衣指数：" + weather.suggestionList.get(i).suggestion;
                    break;
                case "sport":
                    sport = "运动建议：" + weather.suggestionList.get(i).suggestion;
                    break;
                case "flu":
                    influenza = "感冒指数：" +weather.suggestionList.get(i).suggestion;
                    break;
                case "uv":
                    UV = "UV指数："+weather.suggestionList.get(i).suggestion;
                default:break;
            }
        }
        comfortText.setText(comfort);
        clothesText.setText(clothes);
        sportText.setText(sport);
        influText.setText(influenza);
        uvText.setText(UV);
        weatherLayout.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, AutoUpdateService.class);
        startService(intent);
    }

    /**
     * 加载必应每日一图
     */
    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("bing_pic",bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }
        });
    }
}
