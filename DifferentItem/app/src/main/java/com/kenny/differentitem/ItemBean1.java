package com.kenny.differentitem;

/**
 * @author 罗火金
 * @description
 * @date 2019/8/22
 */
public class ItemBean1 extends BaseItem {
    private String name = null;
    private String imagePath = null;


    public ItemBean1(int item_type, String name, String imagePath) {
        super(item_type);
        this.name = name;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getItemType(){
        return super.getItem_type();
    }

    public void setItemType(int itemType){
        super.setItem_type(itemType);
    }
}
