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
    private String result_name;                              //结果名
    private String result;                                   //结果：成功/失败
    public  Result(String resultname,String result){
        this.result_name = resultname;
        this.result = result;
    }
    public String getName(){return result_name;}
    public String getResult(){return result;}
    public void setName(String resultname){this.result_name=resultname;}
    public void setResult(String result){this.result=result;}
}
