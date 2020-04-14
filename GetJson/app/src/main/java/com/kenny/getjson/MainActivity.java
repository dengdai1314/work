package com.kenny.getjson;

import android.content.res.AssetManager;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.angmarch.views.NiceSpinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/14 14:28
 * @description 读取json数据
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        Gson gson = new Gson();

        //初始化角色数据
        String roleResult = getJson("RoleDatabase.json");
        //将json字符串自动解析成一个json对象；json数组的话需要借助TypeToken将期望解析成的数据类型传入到fromJson方法中
        List<Role> roleList = gson.fromJson(roleResult,new TypeToken<List<Role>>(){}.getType());

        //初始化场景数据
        String sceneResult = getJson("SceneDatabase.json");
        //将json字符串自动解析成一个json对象；json数组的话需要借助TypeToken将期望解析成的数据类型传入到fromJson方法中
        List<Scene> sceneList = gson.fromJson(sceneResult,new TypeToken<List<Scene>>(){}.getType());

        //初始化动作数据
        String animResult = getJson("StateDatabase.json");
        //将json字符串自动解析成一个json对象；json数组的话需要借助TypeToken将期望解析成的数据类型传入到fromJson方法中
        List<State> animList = gson.fromJson(animResult,new TypeToken<List<State>>(){}.getType());

        //初始化UI数据
        final LinkedList<String> uiData = new LinkedList<>(Arrays.asList("天气UI","音乐UI"));

        //初始化角色列表数据
        LinkedList<String> roleData = new LinkedList<>();
        for (Role role : roleList){
            roleData.add(role.getStrName());
        }
        spinnerList(roleData,R.id.spinner_role);
        //初始化场景列表数据
        LinkedList<String> sceneData = new LinkedList<>();
        for (Scene scene : sceneList){
            sceneData.add(scene.getStrScene());
        }
        spinnerList(sceneData, R.id.spinner_scene);
        //初始化动作列表数据
        LinkedList<String> animData = new LinkedList<>();
        for (State state : animList){
            animData.add(state.getStrDes());
        }
        spinnerList(animData,R.id.spinner_anim);
        //初始化UI列表数据
        spinnerList(uiData,R.id.spinner_ui);
    }

    public String getJson (String filename){
        StringBuilder stringBuilder = new StringBuilder();
        String data =null;
        try {
            AssetManager assetManager = MainActivity.this.getAssets();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(assetManager.open(filename)));
            String line;
            while ((line = bfr.readLine()) != null) {
                stringBuilder.append(line);
            }
            data = stringBuilder.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    public void spinnerList(LinkedList<String> listdata,int id){
        final NiceSpinner spinnerlist = (NiceSpinner) findViewById(id);
        spinnerlist.attachDataSource(listdata);
    }


}
