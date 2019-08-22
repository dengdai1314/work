package com.kenny.selectlist;

/**
 * @author 29003
 * @description
 * @date 2019/8/22
 */
public class Even {
    private int image;
    private String title;
    private String describe;
    public  Even(int image,String title,String describe){

        this.image = image;
        this.title = title;
        this.describe = describe;
    }
    public int getImage(){return image;}
    public String getTitle(){return title;}
    public String getDescribe(){return describe;}
    public void setImage(int image){this.image=image;}
    public void setTitle(String title){this.title=title;}
    public void setDescribe(String describe){this.describe=describe;}
}
