package com.kenny.home.model;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * @author 29003
 * @description
 * @date 2019/12/3
 */
public class BaseBean implements Serializable {
    public String toJson() {
        return new Gson().toJson(this);
    }

    public String toFormatJson() {
        return JsonFormatUtils.format(toJson());
    }
}
