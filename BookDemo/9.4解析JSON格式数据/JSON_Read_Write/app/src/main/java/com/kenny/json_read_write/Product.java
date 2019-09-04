package com.kenny.json_read_write;

/**
 * @author 29003
 * @description
 * @date 2019/8/16
 */
public class Product {
    private String name;
    private String result;
    public  Product(String name,String result){
        this.name = name;
        this.result = result;
    }
    public String getName(){return name;}
    public String getResult(){return result;}
    public void setName(String name){this.name=name;}
    public void setResult(String result){this.result=result;}

}
