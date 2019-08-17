package com.kenny.factorytest;

/**
 * @author 29003
 * @description
 * @date 2019/8/14
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
