package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/2314:06
 * @description basic json数据
 */
public class Basic {
    @SerializedName("location")
    public String cityName;
    @SerializedName("cid")
    public String weatherId;

}
