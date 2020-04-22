package com.practice.intenttest.transferobject;

import java.io.Serializable;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/2210:46
 * @description serializable序列化对象，表示将一个对象转换成可存储或可传输的状态。
 * 序列化后的对象可以在网络上可以传输，也可以存储到本地
 */
public class Person implements Serializable {
    private String name;
    private int age;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
