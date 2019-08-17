package com.kenny.refreshsingledata;

/**
 * @author 29003
 * @description
 * @date 2019/8/14
 */
public class Micro {
    private String micro_name;
    private int angle;
    private int beam;
    public Micro(String micro_name, int angle, int beam){
        this.micro_name = micro_name;
        this.angle = angle;
        this.beam = beam;
    }
    public String getMicro_name(){return micro_name;}
    public int getAngle(){
        return angle;
    }
    public int getBeam(){
        return beam;
    }
    public void setMicro_name(String micro_name){this.micro_name = micro_name; }
    public void setAngle(int angle){this.angle = angle; }
    public  void  setBeam (int beam){this.beam = beam;}
}
