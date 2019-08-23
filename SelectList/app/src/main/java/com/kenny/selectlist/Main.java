package com.kenny.selectlist;

/**
 * @author 29003
 * @description
 * @date 2019/8/22
 */
public class Main {
    private int result_image;
    private String result_name;                              //结果名
    private String result_describe;
    public  Main(int result_image,String result_name,String result_describe){
        this.result_image = result_image;
        this.result_name = result_name;
        this.result_describe = result_describe;
    }
    public int getImage(){return result_image;}
    public String getName(){return result_name;}
    public String getResult_describe(){return result_describe; }
    public void setimage(int result_image){this.result_image=result_image;}
    public void setName(String result_name){this.result_name=result_name;}
    public void setdescribe(String result_describe){this.result_describe=result_describe;}
}
