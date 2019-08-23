package com.kenny.differentitem;

/**
 * @author 罗火金
 * @description
 * @date 2019/8/22
 */
public class ItemBean2 extends BaseItem {
    private String name = null;
    private String name2 = null;


    public ItemBean2(int item_type, String name, String name2) {
        super(item_type);
        this.name = name;
        this.name2 = name2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String imagePath) {
        this.name2 = name2;
    }

    public int getItemType(){
        return super.getItem_type();
    }

    public void setItemType(int itemType){
        super.setItem_type(itemType);
    }
}
