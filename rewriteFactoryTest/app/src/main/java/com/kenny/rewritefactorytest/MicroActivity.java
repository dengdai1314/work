package com.kenny.rewritefactorytest;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.zz.iflytekenginelib.IWakeListener;
import com.zz.iflytekenginelib.IflytekEngineManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MicroActivity extends BaseActivity implements View.OnClickListener, IWakeListener {
    public static final String TAG = MicroActivity.class.getSimpleName();
    private static int micro_red = 1;
    private static int micro_green =2;
    private IflytekEngineManager iflytekEngineManager;                  //实例化ifly
    TextView micro_result;                                              //麦克风结果项
    Button micro_up;                                                    //按钮+
    Button micro_down;                                                  //按钮-
    String resPath = "xiaoouxiaoou.jet";                                //原文件
    String targetDir = getSDPath() + "/AIUI/ivw60/";                    //迁移原文件到对应的目标文件夹
    String mResPath = getSDPath() + "/AIUI/ivw60/xiaoouxiaoou.jet";     //目标文件路径
    private int currentPosition =1;                                     //当前item position
    private String micro_angle ;                                        //当前麦克风监听角度
    private String micro_beam ;                                         //当前麦克风
    //用于MicroAdapter
    private Context microContext;
    private ListView micro_list;
    private MicroAdapter microAdapter = null;                           //适配器
    List<Micro> microdata;                                              //列表数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_micro);
        micro_result = findViewById(R.id.micro_result);
        micro_up = findViewById(R.id.key_up);
        micro_down = findViewById(R.id.key_down);
        micro_up.setOnClickListener(this);
        micro_down.setOnClickListener(this);
        initPermission();
        //判断目标文件夹是否存在
        if (!new File(targetDir).exists()){
            new File(targetDir).mkdirs();                                               //创建目录
        }
        try {
            if(getAssets().open(resPath) != null){                                      //打开assets文件下原文件
                Util.copyFileFromAssets(MicroActivity.this,resPath,mResPath);    //复制文件
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        microContext = MicroActivity.this;
        micro_list = findViewById(R.id.micro_list);
        microdata = new LinkedList<Micro>();
        microdata.add(new Micro("麦克风0",10,10,0));
        microdata.add(new Micro("麦克风1",20,20,0));
        microdata.add(new Micro("麦克风2",30,30,0));
        microdata.add(new Micro("麦克风3",40,40,0));
        microdata.add(new Micro("麦克风4",50,50,0));
        microdata.add(new Micro("麦克风5",60,60,0));
        microAdapter = new MicroAdapter((LinkedList<Micro>) microdata,microContext);
        micro_list.setAdapter(microAdapter);
    }

    /**
     * 获取当前sdcard目录
     * @return
     */
    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();                                         //获取根目录
        }
        return sdDir.toString();
    }

    /**
     *初始化ifly
     */
    public void initifly(){
        boolean cae = IflytekEngineManager.getInstance().createCAEEngine(mResPath);
        iflytekEngineManager = IflytekEngineManager.getInstance();
        //iflytekEngineManager.setJniLog(false);
        //iflytekEngineManager.createCAEEngine(mResPath);
        iflytekEngineManager.startRecording();
        // iflytekEngineManager.stopRecording();
        iflytekEngineManager.setCAEListener(this);
    }

    /**
     * 唤醒
     * @param s
     */
    @Override
    public void onWakeup(String s) {
        Log.e(TAG, "Wakeup:" + s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            micro_angle = jsonObject.getString("angle");
            micro_beam = jsonObject.getString("beam");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAudio(byte[] bytes, int i, int i1, int i2) {

    }

    @Override
    public void onError(int i, String s) {

    }

    /**
     * 按钮点击
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.key_up:
                clickEvent(micro_red);
                currentPosition++;
                break;
            case R.id.key_down:
                clickEvent(micro_green);
                currentPosition++;
                break;
            default:
                break;
        }
    }

    /**
     * 按钮点击，根据按钮改变上一个position颜色，显示当前position数据
     * 当点击第六次的时候，改变麦克风5的颜色，显示测试完成
     * 当点击第七次的时候，跳转活动
     * @param color
     */
    public void clickEvent(int color){
        if (currentPosition <=6){
            microAdapter.change(currentPosition,color);
            int position = currentPosition-1;    //用于存储正确数据
            if(color == micro_red){
                saveJson("麦克风" + position, "成功");
            }else if(color == micro_green){
                saveJson("麦克风" + position, "失败");
            }
            if(currentPosition == 6){
                micro_result.setText("测试完成\n请按+/-键进入结果页面");
            }
        }
        else if (currentPosition == 7) {
//            Toast.makeText(MicroActivity.this,"跳转",Toast.LENGTH_SHORT).show();
            skip(MicroActivity.this,ResultRecycleActivity.class);
        }
    }
}
