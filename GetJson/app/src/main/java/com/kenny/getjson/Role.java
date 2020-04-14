package com.kenny.getjson;

/**
 * @author 29003
 * @description
 * @date 2019/7/15
 */
public class Role {
    private int wdID;
    private String strName;

    public int getWdID() {
        return wdID;
    }

    public void setWdID(int wdID) {
        this.wdID = wdID;
    }

    public String getStrName() {
        return strName == null ? "" : strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "wdID=" + wdID +
                ", strName='" + strName + '\'' +
                '}';
    }
}
