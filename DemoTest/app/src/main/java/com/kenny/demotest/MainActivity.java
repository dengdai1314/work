package com.kenny.demotest;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

import androidx.core.content.ContextCompat;

public class MainActivity extends UnityPlayerActivity implements UnityCallbackListener {

    String TAG = "MainActivity_Unity";

    List<Role> roleList = new ArrayList();  //角色列表
    List<Scene> sceneList = new ArrayList();//场景列表
    List<State> stateList = new ArrayList();//动作列表
    List<Ui> uiList = new ArrayList();      //ui列表

    NiceSpinner spinner_role;
    NiceSpinner spinner_scene;
    NiceSpinner spinner_state;
    NiceSpinner spinner_ui;
    Button add1;
    Button weather;
    Button change_role;
    Button change_scene;
    Button play_anim;
    Button show;
    Button hide;
    LinearLayout ll_3d;

    Role roleItem;              //当前选中角色
    Scene sceneItem;            //当前选中场景
    State stateItem;            //当前选中动作
    Ui uiItem;                  //当前选中ui

    boolean isShowU3D = true;                               //是否显示3Dui
    boolean isUnityIinitSuccess = false;                    //是否Unity初始化成功
    WeatherSceneManager weatherSceneManager = null;         //天气模拟类初始化

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);                 //主活动必须要有的
        setContentView(R.layout.activity_main);             //定义布局引用文件
        ll_3d = findViewById(R.id.ll_3d);
        ll_3d.addView(mUnityPlayer);                        //将UnityPlayer 添加到对应的View ；必须添加，否则不能初始化Unity

        add1 = findViewById(R.id.add1);
        weather = findViewById(R.id.weather);
        change_role = findViewById(R.id.change_sex);
        change_scene = findViewById(R.id.change_scene);
        play_anim = findViewById(R.id.play_animal);
        show = findViewById(R.id.show_ui);
        hide = findViewById(R.id.hide_ui);
        spinner_role = findViewById(R.id.spinner_role);
        spinner_scene = findViewById(R.id.spinner_scene);
        spinner_state = findViewById(R.id.spinner_state);
        spinner_ui = findViewById(R.id.spinner_ui);

        initData();                                                            //初始化列表数据
        addOrRemoveViewClick();                                                //SHOW/HIDE View事件
        UnityManager.getInstance().setLogLevel(4);                             //设置unityLog等级error
        UnityManager.getInstance().addCallBackListener(this);//添加unity回调事件
    }

    /**
     * 初始化列表数据
     */
    public void initData() {
        //创建Gson实例。为后续解析json文件做好准备
        Gson gson = new Gson();

        //初始化角色数据
        //读取文件获取角色数据
        String roleResult = getJson("RoleDatabase.json");
        //将json字符串自动解析成一个json对象；json数组的话需要借助TypeToken将期望解析成的数据类型传入到fromJson方法中
        //解析为list,new TypeToken<List<x>>{}.getType
        roleList = gson.fromJson(roleResult, new TypeToken<List<Role>>() {
        }.getType());

        //初始化场景数据
        String sceneResult = getJson("SceneDatabase.json");
        sceneList = gson.fromJson(sceneResult, new TypeToken<List<Scene>>() {
        }.getType());

        //初始化动作数据
        String stateResult = getJson("StateDatabase.json");
        stateList = gson.fromJson(stateResult, new TypeToken<List<State>>() {
        }.getType());

        //初始化UI数据
        String uiResult = getJson("UiDatabase.json");
        uiList = gson.fromJson(uiResult, new TypeToken<List<Ui>>() {
        }.getType());

        //初始化按钮点击选项，如果未初始化，点击按钮，应用崩溃
        roleItem = roleList.get(0);
        sceneItem = sceneList.get(0);
        stateItem = stateList.get(0);
        uiItem = uiList.get(0);

        initRoleSpinner();
        initSceneSpinner();
        initStateSpinner();
        initUiSpinner();

        //设置动作列表，为AnimalManager.java文件初始化
        if (stateList != null && stateList.size() != 0) {
            AnimalManager.getInstance().setAnimalList(stateList);
        }
    }

    /**
     * 初始化角色列表
     */
    public void initRoleSpinner() {
        //角色列表数据显示规则(json读取出来的格式显示与要求不符)
        //显示形式调整
        SpinnerTextFormatter text_role = new SpinnerTextFormatter<Role>() {
            @Override
            public Spannable format(Role item) {
                return new SpannableString(item.getStrName());   //获取json文件读取出来数据中的strname
            }
        };
        spinner_role.setSpinnerTextFormatter(text_role);         //设置spinner已选择显示内容文本格式
        spinner_role.setSelectedTextFormatter(text_role);        //设置选择时待选择内容文本格式
        //spinner_role监听点击事件
        spinner_role.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                //instanceof:判断对象是否是类或子类所创建，返回true/false
                //如果被选中项属于Role创建，返回true
                if (spinner_role.getSelectedItem() instanceof Role) {
                    roleItem = (Role) spinner_role.getSelectedItem(); //roleItem(当前选中的角色)存储被选中项数据
                    //更新场景数据
                    List<Scene> scenes;
                    //如果被选中项数据中的strname=="女"，设置scenes=2，
                    //用于后续与scene.gender比对，获取被选中项对应的场景数据列表
                    if (roleItem.getStrName().contains("女")) {
                        //调用getScene:获取scene.gender=2的scene数据并将其放到一个列表中，随后更新场景列表数据
                        scenes = getScene(sceneList, 2);
                        spinner_scene.attachDataSource(scenes);
                    } else {
                        scenes = getScene(sceneList, 1);
                        spinner_scene.attachDataSource(scenes);
                    }
                }
            }
        });
        spinner_role.attachDataSource(roleList);                 //添加列表内容
    }

    /**
     * 更新场景数据
     */
    //获取角色对应场景列表数据，用于刷新场景列表
    //根据男（1）/女（2）获取scene.gender,并将scene对应数据添加到现有scene列表数据
    private List<Scene> getScene(List<Scene> sceneList, int type) {
        List<Scene> list = new ArrayList<>();                   //创建list集合
        //遍历场景列表，寻找scene.gender=type(1/2)的数据，并将寻找出来的数据添加到列表中，以备调用
        for (Scene scene : sceneList) {
            if (scene.getGender() == type) {
                list.add(scene);
            }
        }
        return list;
    }

    /**
     * 初始化场景列表
     */
    public void initSceneSpinner() {
        SpinnerTextFormatter text_scene = new SpinnerTextFormatter<Scene>() {
            @Override
            public Spannable format(Scene item) {
                return new SpannableString(item.getStrScene());
            }
        };

        spinner_scene.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                if (spinner_scene.getSelectedItem() instanceof Scene) {
                    sceneItem = (Scene) spinner_scene.getSelectedItem();
                    List<State> states;
                    states = getState(stateList, sceneItem.getWdID());
                    spinner_state.attachDataSource(states);
                }
            }
        });
        //首次进入页面加载默认女模对应场景数据
        List<Scene> scene;
        scene = getScene(sceneList, 2);
        spinner_scene.attachDataSource(scene);
    }

    /**
     * 初始化动作列表
     */
    public void initStateSpinner() {
        SpinnerTextFormatter text_state = new SpinnerTextFormatter<State>() {
            @Override
            public Spannable format(State item) {
                return new SpannableString(item.getStrDes());
            }
        };
        spinner_state.setSpinnerTextFormatter(text_state);
        spinner_state.setSelectedTextFormatter(text_state);
        spinner_state.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                if (spinner_state.getSelectedItem() instanceof State) {
                    stateItem = (State) spinner_state.getSelectedItem();
                }
            }
        });
        //首次进入页面加载默认场景对应动作数据
        List<State> state;
        state = getState(stateList, 1);
        spinner_state.attachDataSource(state);
    }

    /**
     * 更新动作数据
     */
    //根据scene.wdId获取stata.nsceneID,并将state对应数据添加到现有state列表数据
    private List<State> getState(List<State> statelist, int statetype) {
        List<State> list = new ArrayList<>();
        for (State state : statelist) {
            if (state.getNSceneID() == statetype) {
                list.add(state);
            }
        }
        return list;
    }

    /**
     * 初始化ui列表
     */
    public void initUiSpinner() {
        SpinnerTextFormatter text_ui = new SpinnerTextFormatter<Ui>() {
            @Override
            public Spannable format(Ui item) {
                return new SpannableString(item.getStrName());
            }
        };
        spinner_ui.setSpinnerTextFormatter(text_ui);
        spinner_ui.setSelectedTextFormatter(text_ui);
        spinner_ui.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                //点击UI列表切换，现有UI隐藏。未有此项，切换UI时，原有Ui不隐藏，显示被选择的UI
                if (uiItem != null && uiItem.getWID() != 0) {
                    UnityManager.getInstance().hideUI("hideUi", uiItem.getWID());
                }
                if (spinner_ui.getSelectedItem() instanceof Ui) {
                    uiItem = (Ui) spinner_ui.getSelectedItem();
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

    /**
     * Unity 未初始化完成不能调用接口
     */
    public boolean isUnityInit() {
        if (!isUnityIinitSuccess) {
            Toast.makeText(MainActivity.this, "Unity未初始化完成，暂时不能调用Unity接口", Toast.LENGTH_SHORT).show();
        }
        return isUnityIinitSuccess;
    }

    /**
     * Unity初始化成功，只有这个地方回调Unity初始化成功，才能调用操作接口，如切换成功、播放动作等
     */
    @Override
    public void initComplete() {
        isUnityIinitSuccess = true;
        Log.d(TAG, "Unity初始化成功");
        Toast.makeText(MainActivity.this, "Unity初始化完成", Toast.LENGTH_SHORT).show();
        UnityManager.getInstance().changeScene("changeDefault", 1);//切换默认场景
    }

    /**
     * UnityPlayer其实也是一个自定义View，我们需要将view加到想要显示的控件上，
     * 由用户自己控制
     */
    public void addOrRemoveViewClick() {
        /**
         * SHOW/HIDE按钮监听事件
         */
        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isShowU3D = !isShowU3D;
                ShowOrHide(isShowU3D);
            }
        });

        /**
         * 切换性别
         * 切换性别其实就是切换角色ID 每个角色ID都有自己的形象
         */
        change_role.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isUnityInit()) {
                    return;
                }
                if (roleItem != null && roleItem.getWdID() != 0) {
                    UnityManager.getInstance().changeRole("PlayAnimal", roleItem.getWdID());
                }
            }
        });

        /**
         * 切换选中的场景
         */
        change_scene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isUnityInit()) {
                    return;
                }
                if (sceneItem != null && sceneItem.getWdID() != 0) {
                    UnityManager.getInstance().changeScene("ChangeScene", sceneItem.getWdID());
                }
            }
        });

        /**
         * 切换选中的动作
         */
        play_anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isUnityInit()) {
                    return;
                }
                if (stateItem != null && stateItem.getWdID() != 0) {
                    UnityManager.getInstance().playAnimation("ChangeAnim", stateItem.getWdID());
                    Toast.makeText(MainActivity.this, "动作播放成功", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**
         * 显示UI按钮点击事件
         */
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isUnityInit()) {
                    return;
                }
                //根据UIlist中的wid切换天气/音乐UI；不能更换2/3，已与uI数据绑定，更换会出现场景错乱
                if (uiItem != null && uiItem.getWID() != 0) {
                    switch (uiItem.getWID()) {
                        case 2:
                            UnityManager.getInstance().showUI("ShowUi", uiItem.getWID(), new WeatherModel());
                            break;
                        case 3: {
                            UnityMusicModel unityMusicModel = new UnityMusicModel();
                            unityMusicModel.setIndex(2);//设置当前播放歌曲的索引
                            ArrayList list = new ArrayList<>();
                            list.add(new NLPSongInfo("刘德华", "忘情水"));  //音乐歌单列表
                            list.add(new NLPSongInfo("周杰伦", "七里香"));
                            list.add(new NLPSongInfo("张学友", "吻别"));
                            unityMusicModel.setNlpSongInfos(list.toArray());
                            UnityManager.getInstance().showUI("ShowUi", 3, unityMusicModel);
                            break;
                        }
                    }
                }
            }
        });

        /**
         * 隐藏按钮点击按钮事件
         */
        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isUnityInit()) {
                    return;
                }
                if (uiItem != null && uiItem.getWID() != 0) {
                    UnityManager.getInstance().hideUI("HideUi", uiItem.getWID());
                }
            }
        });

        /**
         * 播放天气场景
         */
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isUnityInit()) {
                    return;
                }
                //初始化天气场景（进入应用后直接点击播放天气按钮）
                if (weatherSceneManager == null) {
                    for (int i = 0; i < sceneList.size(); i++) {
                        if (sceneList.get(i).getWdID() == 2) {
                            sceneItem = sceneList.get(i);
                            //设置天气场景，用于调用更换天气场景
                            weatherSceneManager = new WeatherSceneManager(sceneItem);
                            break;
                        }
                    }
                }
                weatherSceneManager.changeScene();
            }
        });
    }

    /**
     * 显示或者隐藏UI  此处只是做动画缩放，用户可以直接操作View 父控件add或者move
     * flag  true显示  false隐藏
     */
    public void ShowOrHide(final Boolean flag) {
        if (flag) {
            //设置背景颜色为空，即不设置
            ll_3d.setBackground(null);
        }
        //定义动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(ll_3d, "scaleX", flag ? 0.15f : 1.0f, flag ? 1.0f : 0.15f);//x轴比例
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(ll_3d, "scaleY", flag ? 0.15f : 1.0f, flag ? 1.0f : 0.15f);//y轴比例
        ObjectAnimator tranX = ObjectAnimator.ofFloat(ll_3d, View.TRANSLATION_X, flag ? 0f : px2Dp(ll_3d.getWidth() - 100));//改变后x
        ObjectAnimator tranY = ObjectAnimator.ofFloat(ll_3d, View.TRANSLATION_Y, flag ? 0f : px2Dp(ll_3d.getHeight() - 100));//改变后Y
        //AnimatorSet是对属性动画的一个集合，可以让很多动画按一定顺序或者 同时进行。
        //使用AnimatorSet整合动画
        AnimatorSet set = new AnimatorSet();
        //动画一起进行
        set.play(tranX).with(tranY).with(scaleX).with(scaleY);
        //表示该动画要在1秒内播放完成
        set.setDuration(1000);
        set.start();
        //对AnimatorSet监听
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Log.d("StartAnimal","动画开始");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Log.d("EndAnimal", "动画结束");
                if (flag) {
                    ll_3d.setBackground(null);
                } else {
                    ll_3d.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.background));
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    /**
     * 切换角色成功。
     * sceneId 场景回调ID
     */
    @Override
    public void changeRoleSuccess() {
        Log.d(TAG, "切换角色成功");
        Toast.makeText(MainActivity.this, "切换角色成功", Toast.LENGTH_SHORT).show();
        //男女角色的天气场景及UI不一样
        if (weatherSceneManager == null) {
            weatherSceneManager = new WeatherSceneManager(sceneItem);
        }
        //设置角色对应的天气场景，如未设置，男女模调用同一个天气场景，男模天气tts未播报，动作不播放，女模角色播放第一个动作后，后续动作不播
        if (roleItem.getStrName().contains("女") ) {
            weatherSceneManager.setScene(sceneList.get(1));
        }
        else if (roleItem.getStrName().contains("男")) {
            weatherSceneManager.setScene(sceneList.get(9));
        }
    }

    /**
     * 切换场景成功。调用切换场景方法后都会在此处回调场景切换成功,然后根据场景做不同的操作
     * sceneId 场景回调ID
     */
    @Override
    public void changeSceneSuccess(int sceneId) {
        Toast.makeText(MainActivity.this, "切换" + sceneId + "场景成功了", Toast.LENGTH_SHORT).show();
    }

    /**
     * 动作播放回调，每次用户播放动画结束都会在此处回调，这个时候用户可以接着播放下一个动作
     */
    @Override
    public void playAnimComplete(int animId) {
        Log.d(TAG, "动作播放结束===" + animId);
        Toast.makeText(MainActivity.this, animId + "动作播放结束", Toast.LENGTH_SHORT).show();
    }

    public void onWindowFocusChanged(Boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
        UnityManager.getInstance().removeCallBacListener(this);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public float px2Dp(int pxValue) {
        //得到屏幕的相对密度
        float scale = getResources().getDisplayMetrics().density;
        return (pxValue / scale + 0.5f);
    }
}
