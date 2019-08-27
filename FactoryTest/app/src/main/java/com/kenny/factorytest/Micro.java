package com.kenny.factorytest;
/*
 *
 * File: Micro.java
 * Author: 29003
 * Create: 2019/8/20 16:45
 * Changes (from 2019/8/20)
 * -----------------------------------------------------------------
 * 2019/8/20 : Create Micro.java (29003);
 * -----------------------------------------------------------------
 * description:麦克风实例
 */
/**
 * @author 29003
 * @description
 * @date 2019/8/20
 */
public class Micro {
    private String micro_name;
    private int angle;
    private int beam;
    private int color;
    public Micro(String micro_name,int angle, int beam,int color){
        this.micro_name = micro_name;
        this.angle = angle;
        this.beam = beam;
        this.color = color;
    }
    public String getMicro_name(){return micro_name;}
    public int getAngle(){
        return angle;
    }
    public int getBeam(){
        return beam;
    }
    public int getColor(){return color;}
    public void setMicro_name(String micro_name){this.micro_name = micro_name; }
    public void setAngle(int angle){this.angle = angle; }
    public void setBeam (int beam){this.beam = beam;}
    public void setColor(int color){this.color = color;}
}
