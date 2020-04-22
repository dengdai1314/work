package com.practice.intenttest.transferobject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/2210:54
 * @description
 */
public class Airplane implements Parcelable {

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

    public static final Creator<Airplane> CREATOR = new Creator<Airplane>() {
        @Override
        public Airplane createFromParcel(Parcel in) {
            Airplane airplane = new Airplane();
            airplane.name = in.readString();
            airplane.age = in.readInt();
            return airplane;
        }

        @Override
        public Airplane[] newArray(int size) {
            return new Airplane[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    //将类中的字段一一写出
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }
}
