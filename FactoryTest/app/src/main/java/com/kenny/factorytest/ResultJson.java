package com.kenny.factorytest;
/*
 *
 * File: ResultJson.java
 * Author: 29003
 * Create: 2019/8/19 11:06
 * Changes (from 2019/8/19)
 * -----------------------------------------------------------------
 * 2019/8/19 : Create ResultJson.java (29003);
 * -----------------------------------------------------------------
 * description:ResultJson 实例
 */
/**
 * @author 29003
 * @description
 * @date 2019/8/16
 */
public class ResultJson {
    private String name;
    private String result;
    public  ResultJson(String name,String result){
        this.name = name;
        this.result = result;
    }
    public String getName(){return name;}
    public String getResult(){return result;}
    public void setName(String name){this.name=name;}
    public void setResult(String result){this.result=result;}
}
