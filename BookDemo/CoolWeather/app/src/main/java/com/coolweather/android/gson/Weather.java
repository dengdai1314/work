package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/2315:33
 * @description
 */
public class Weather {
    public String status;
    public Basic basic;
    public AQI aqi;
    public Now now;
    public Suggestion suggestion;
    //@SerializedName 很重要，不然去掉试试
    //其实主要起一个对应的作用，使用daily_forecast去寻找json中的数据，如果去掉，你会发现forecastList为空
    //最后点位到出错点：Utility.java/handleWeatherResponse方法中的return，然后你会发现问题就出现现在这个文件
    //这个文件主要是起格式化数据的作用
    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;

}
