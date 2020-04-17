package com.practice.networktest.data;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/1610:19
 * @description
 */
public class App {
    private String id;
    private String name;
    private String version;

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version == null ? "" : version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


}
