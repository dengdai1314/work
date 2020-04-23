package com.coolweather.android.gson;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/2314:13
 * @description aqi json数据
 */
public class AQI {
    public AQICity city;
    public class AQICity{
        public String aqi;
        public String pm25;
    }

}
