package com.coolweather.android.db;

import org.litepal.crud.LitePalSupport;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/2215:53
 * @description city 数据库类
 */
public class City extends LitePalSupport {
    private int id;

    private String cityName;

    private int cityCode;

    private int provinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

}
