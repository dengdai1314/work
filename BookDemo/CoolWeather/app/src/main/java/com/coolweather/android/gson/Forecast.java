package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/2314:17
 * @description Forecast json数据类
 */
public class Forecast {
    @SerializedName("date")
    public String date;
    @SerializedName("cond_txt_d")
    public String more;
    @SerializedName("tmp_max")
    public String max;
    @SerializedName("tmp_min")
    public String min;
    @SerializedName("hum")
    public String humidity;
    @SerializedName("pop")
    public String rainfall;
}
