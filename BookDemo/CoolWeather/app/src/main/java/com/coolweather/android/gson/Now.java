package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/2314:16
 * @description now json数据
 */
public class Now {
    @SerializedName("tmp")
    public String temperature;
    @SerializedName("cond_txt")
    public String info;
    @SerializedName("fl")
    public String feeling;
    @SerializedName("hum")
    public String humidity;
}
