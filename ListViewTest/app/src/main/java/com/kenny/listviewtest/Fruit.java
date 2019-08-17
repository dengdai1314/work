package com.kenny.listviewtest;

/**
 * @author 29003
 * @description
 * @date 2019/7/11
 */
public class Fruit {
    private String name;
    private int imageId;

    public Fruit(String name, int imageId){
        this.name=name;
        this.imageId=imageId;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}
