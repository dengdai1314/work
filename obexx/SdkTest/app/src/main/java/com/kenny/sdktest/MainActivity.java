package com.kenny.sdktest;
/*
 *
 * File: MainActivity.java
 * Author: luohuojin
 * Create: 2019/9/3 16:38
 * Changes (from 2019/9/3)
 * -----------------------------------------------------------------
 * 2019/9/3 : Create MainActivity.java (29003);
 * -----------------------------------------------------------------
 * description:sdk接入
 */
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kenny.sdktest.model.RoleBean;
import com.kenny.sdktest.model.SceneBean;
import com.kenny.sdktest.model.StateBean;
import com.kenny.sdktest.model.UiBean;
import com.obex.lib.UnityCallbackListener;
import com.obex.lib.UnityManager;
import com.obex.lib.UnityPlayerActivity;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;
import org.angmarch.views.SpinnerTextFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends UnityPlayerActivity implements UnityCallbackListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    Button showhide;
    Button weather;
    Button change_role;
    Button change_scene;
    Button change_state;
    Button showUi;
    Button hideUi;
    NiceSpinner spinner_role;
    NiceSpinner spinner_scene;
    NiceSpinner spinner_state;
    NiceSpinner spinner_ui;
    List<RoleBean> roleList;
    List<SceneBean> sceneList;
    List<StateBean> stateList;
    List<UiBean> uiList;
    LinearLayout ll_3d;
    //当前选择项
    RoleBean currentRole;
    SceneBean currentScene;
    StateBean currentState;
    UiBean currentUi;
    boolean isShowU3D = true;
    boolean isUnityIinitSuccess = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initcontrols();
        ll_3d.addView(mUnityPlayer);
        UnityManager.getInstance().setLogLevel(4);//设置Log等级
        UnityManager.getInstance().addCallBackListener(this);
        initdata();
        addOrRemoveViewClick();
    }

    /**
     * 初始化控件
     */
    public void initcontrols(){
        showhide = findViewById(R.id.showhide);
        weather = findViewById(R.id.weather);
        change_role = findViewById(R.id.change_role);
        change_scene = findViewById(R.id.change_scene);
        change_state = findViewById(R.id.change_state);
        change_state = findViewById(R.id.change_role);
        showUi = findViewById(R.id.show_ui);
        hideUi = findViewById(R.id.hide_ui);
        spinner_role = findViewById(R.id.spinner_role);
        spinner_scene = findViewById(R.id.spinner_scene);
        spinner_state = findViewById(R.id.spinner_state);
        spinner_ui = findViewById(R.id.spinner_ui);
        ll_3d = findViewById(R.id.ll_3d);
    }

    /**
     * 初始化列表
     */
    public void initdata(){
        //创建Gson实例。为后续解析json文件做好准备
        Gson gson = new Gson();

        //初始化角色数据
        //读取文件获取角色数据
        String roleResult = getJson("RoleDatabase.json");
        //将json字符串自动解析成一个json对象；json数组的话需要借助TypeToken将期望解析成的数据类型传入到fromJson方法中
        //解析为list,new TypeToken<List<x>>{}.getType
        roleList = gson.fromJson(roleResult, new TypeToken<List<RoleBean>>() {
        }.getType());

        //初始化场景数据
        String sceneResult = getJson("SceneDatabase.json");
        sceneList = gson.fromJson(sceneResult, new TypeToken<List<SceneBean>>() {
        }.getType());

        //初始化动作数据
        String stateResult = getJson("StateDatabase.json");
        stateList = gson.fromJson(stateResult, new TypeToken<List<StateBean>>() {
        }.getType());

        //初始化UI数据
        String uiResult = getJson("UiDatabase.json");
        uiList = gson.fromJson(uiResult, new TypeToken<List<UiBean>>() {
        }.getType());

        //初始化列表
        updataUi(MainActivity.this);
    }

    /**
     * 切换线程初始化列表
     * @param context
     */
    public void updataUi(final Context context){
        ((MainActivity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRoleSpinner();
                initSceneSpinner();
                initStateSpinner();
                initUISpinner();
            }
        });
    }

    /**
     * 初始化显示角色数据
     */
    private void initRoleSpinner(){
        SpinnerTextFormatter text_role= new SpinnerTextFormatter<RoleBean>() {
            @Override
            public Spannable format(RoleBean item) {
                return new SpannableString(item.getStrName());
            }
        };
        spinner_role.setSpinnerTextFormatter(text_role);
        spinner_role.setSelectedTextFormatter(text_role);
        spinner_role.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                if (spinner_role.getSelectedItem() instanceof RoleBean){
                    currentRole = (RoleBean) spinner_role.getSelectedItem();
                    List<SceneBean> scenes;
                    Log.e(TAG,"name==="+currentRole.toString());
                    if(currentRole.getStrName().contains("女")){
                        scenes = refreshscene(sceneList,2);
                        spinner_scene.attachDataSource(scenes);
                    }else {
                        scenes = refreshscene(sceneList,1);
                        spinner_scene.attachDataSource(scenes);
                    }
                }
            }
        });
        spinner_role.attachDataSource(roleList);
    }

    /**
     * 刷新场景列表数据
     * 根据Role选择的item改变当前Scene列表数据
     * @param sceneList
     * @param type
     * @return
     */
    private List<SceneBean> refreshscene(List<SceneBean> sceneList,int type){
        List<SceneBean> list = new ArrayList<>();
        for(SceneBean sceneBean:sceneList){
            if(sceneBean.getGender() == type){
                list.add(sceneBean);
            }
        }
        return list;
    }

    /**
     * 初始化显示场景数据
     */
    private void initSceneSpinner(){
        SpinnerTextFormatter text_scene= new SpinnerTextFormatter<SceneBean>() {
            @Override
            public Spannable format(SceneBean item) {
                return new SpannableString(item.getStrScene());
            }
        };
        spinner_scene.setSpinnerTextFormatter(text_scene);
        spinner_scene.setSelectedTextFormatter(text_scene);
        spinner_scene.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                if(spinner_scene.getSelectedItem() instanceof SceneBean){
                    currentScene = (SceneBean) spinner_scene.getSelectedItem();
                    Log.e(TAG,"name=== "+currentScene.toString());
                    List<StateBean> states;
                    states = refreshState(stateList,currentScene.getWdID());
                    spinner_state.attachDataSource(states);
                }
            }
        });
        //默认场景数据
        List<SceneBean> sceneBeans = refreshscene(sceneList,2);
        spinner_scene.attachDataSource(sceneBeans);
    }

    /**
     * 刷新动作列表数据
     * 根据Scene选择的item改变当前State列表数据
     * @param stateList
     * @param type
     * @return
     */
    private List<StateBean> refreshState(List<StateBean> stateList, int type){
        List<StateBean> list = new ArrayList<>();
        for(StateBean stateBean : stateList){
            if(stateBean.getNSceneID() == type){
                list.add(stateBean);
            }
        }
        return list;
    }
    /**
     * 初始化显示动作数据
     */
    private void initStateSpinner(){
        SpinnerTextFormatter text_state= new SpinnerTextFormatter<StateBean>() {
            @Override
            public Spannable format(StateBean item) {
                return new SpannableString(item.getStrDes());
            }
        };
        spinner_state.setSpinnerTextFormatter(text_state);
        spinner_state.setSelectedTextFormatter(text_state);
        spinner_state.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                if(spinner_state.getSelectedItem() instanceof StateBean){
                    currentState = (StateBean) spinner_state.getSelectedItem();
                    Log.e(TAG,"name==="+currentState.toString());
                }
            }
        });
        //默认动作数据
        List<StateBean> state= refreshState(stateList,1);
        spinner_state.attachDataSource(state);
    }

    /**
     * 初始化显示ui数据
     */
    private void initUISpinner(){
        SpinnerTextFormatter text_ui= new SpinnerTextFormatter<UiBean>() {
            @Override
            public Spannable format(UiBean item) {
                return new SpannableString(item.getStrName());
            }
        };
        spinner_ui.setSpinnerTextFormatter(text_ui);
        spinner_ui.setSelectedTextFormatter(text_ui);
        spinner_ui.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                if(spinner_ui.getSelectedItem() instanceof UiBean){
                    currentUi = (UiBean) spinner_ui.getSelectedItem();
                    Log.e(TAG,"name==="+currentUi.toString());
                }
            }
        });
        spinner_ui.attachDataSource(uiList);
    }

    /**
     * 读取json文件
     */
    public String getJson(String filename) {
        //创建实例stringBuilder以拼接字符串
        StringBuilder stringBuilder = new StringBuilder();
        String data = null;
        try {
            //调用方法访问asset文件夹，.//访问assets文件夹下的a目录
            //InputStreamReader:通过相应的字符编码方式读取字节输入流解码为字符输入流，处理字节
            //BufferedReader:字符输入流中的子类,读取字符流，处理字符文本，并缓冲字符
            AssetManager assetManager = MainActivity.this.getAssets();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(assetManager.open(filename)));
            String line;
            //readLine:读取字符流，并将其返回为字符串。若无数据可读，则返回null
            //循环的从缓冲区读取数据（一行一行读取），拼接到stringBuilder中
            while ((line = bfr.readLine()) != null) {
                stringBuilder.append(line);
            }
            data = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void addOrRemoveViewClick(){
        showhide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        change_role.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentRole!=null&&currentRole.getWdID()!=0){
                    UnityManager.getInstance().changeRole("ChangeRole",currentRole.getWdID());
                }
            }
        });

        change_scene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentScene!=null&&currentScene.getWdID()!=0){
                    UnityManager.getInstance().changeRole("ChangeScene",currentScene.getWdID());
                }

            }
        });

        change_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentState!=null&&currentState.getWdID()!=0){
                    UnityManager.getInstance().playAnimation("PlayAnimation",currentState.getWdID());
                }
            }
        });

        showUi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        hideUi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void initComplete() {

    }

    @Override
    public void changeSceneSuccess(int i) {

    }

    @Override
    public void playAnimComplete(int i) {

    }

    @Override
    public void changeRoleSuccess() {

    }
}
