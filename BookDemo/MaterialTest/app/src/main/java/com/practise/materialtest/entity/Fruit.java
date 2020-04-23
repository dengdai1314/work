package com.practise.materialtest.entity;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/2111:12
 * @description
 */
public class Fruit {

    private String name;
    private int imageId;

    public Fruit(String name,int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}

