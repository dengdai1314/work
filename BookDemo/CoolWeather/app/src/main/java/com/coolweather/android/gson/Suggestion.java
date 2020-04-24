package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/2314:16
 * @description suggestion json数据
 */
public class Suggestion {
    @SerializedName("type")
    public String type;
    @SerializedName("txt")
    public String suggestion;

}
