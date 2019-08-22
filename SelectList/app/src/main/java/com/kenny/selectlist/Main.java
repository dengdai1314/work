package com.kenny.selectlist;

/**
 * @author 29003
 * @description
 * @date 2019/8/22
 */
public class Main {
    private String result_name;                              //结果名
    public  Main(String resultname){
        this.result_name = resultname;
    }
    public String getName(){return result_name;}
    public void setName(String resultname){this.result_name=resultname;}
}
