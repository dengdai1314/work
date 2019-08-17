package com.kenny.demotest;
/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by obex, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: WeatherModel.java
 * Author: liuhai
 * Version: V100R001C01
 * Create: 2019/7/6 15:02
 * Changes (from 2019/7/6)
 * -----------------------------------------------------------------
 * 2019/7/6 : Create WeatherModel.java (liuhai);
 * -----------------------------------------------------------------
 * description:
 */

public class WeatherModel {
    public int airData;
    public String airQuality = "优";
    public String city = "深圳";
    public String date = "";
    public long dateLong;
    public String date_for_voice;
    //private Exp exp;
    public String humidity = "1";
    public String img = "";
    public String lastUpdateTime;
    public String pm25 = "";
    public int temp=27;
    public String tempHigh = "30C";
    public String tempLow = "26C";
    public String tempRange = "26~30";
    public String warning = "";
    public String weather = "";
    public String weatherDescription = "有点热，适合穿短袖短裙等夏季清凉衣物。";
    public String weatherDescription3 = "";
    public String weatherDescription7 = "";
    public int weatherType;
    public String week = "东北风3-4级";
    public String wind = "东北风3-4级";
    public int windLevel;



    @Override
    public String toString() {
        return "WeatherModel{" +
                "airData=" + airData +
                ", airQuality='" + airQuality + '\'' +
                ", city='" + city + '\'' +
                ", date=" + date +
                ", dateLong=" + dateLong +
                ", date_for_voice='" + date_for_voice + '\'' +

                ", humidity=" + humidity +
                ", img='" + img + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", pm25='" + pm25 + '\'' +
                ", temp=" + temp +
                ", tempHigh='" + tempHigh + '\'' +
                ", tempLow='" + tempLow + '\'' +
                ", tempRange='" + tempRange + '\'' +
                ", warning='" + warning + '\'' +
                ", weather='" + weather + '\'' +
                ", weatherDescription='" + weatherDescription + '\'' +
                ", weatherDescription3='" + weatherDescription3 + '\'' +
                ", weatherDescription7='" + weatherDescription7 + '\'' +
                ", weatherType=" + weatherType +
                ", week='" + week + '\'' +
                ", wind='" + wind + '\'' +
                ", windLevel=" + windLevel +
                '}';
    }
}

