package com.kenny.rewritefactorytest;
/*
 *
 * File: Result.java
 * Author: 29003
 * Create: 2019/8/19 16:10
 * Changes (from 2019/8/19)
 * -----------------------------------------------------------------
 * 2019/8/19 : Create Result.java (29003);
 * -----------------------------------------------------------------
 * description:Result实例
 */
/**
 * @author 29003
 * @description
 * @date 2019/8/19
 */
public class Result {
    private String name;
    private String result;
    public  Result(String name,String result){
        this.name = name;
        this.result = result;
    }
    public String getName(){return name;}
    public String getResult(){return result;}
    public void setName(String name){this.name=name;}
    public void setResult(String result){this.result=result;}
}
