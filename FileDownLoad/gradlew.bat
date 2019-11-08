package com.obex.lib;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.obex.baselib.event.AlarmStartEvent;
import com.obex.baselib.event.MessageEvent;
import com.obex.baselib.model.NLPResult;
import com.obex.baselib.player.ObexAIUIPlayer;
import com.obex.baselib.player.ObexAudioPlayer;
import com.obex.baselib.player.ObexKKBOXPlayer;
import com.obex.baselib.utils.GlobalDataManager;
import com.obex.baselib.utils.StatuUtils;
import com.obex.baselib.utils.ThreadPoolManager;
import com.obex.lib.callback.AlarmCallBack;
import com.obex.lib.callback.UnityCallbackListener;
import com.obex.lib.enums.UnityDataType;
import com.obex.lib.model.MusicDataModel;
import com.obex.lib.scene.AnimalDataManager;
import com.obex.lib.scene.AnimalStateModel;
import com.obex.lib.scene.IdleScenneManager;
import com.obex.lib.scene.ScenesManager;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.obex.lib.enums.UnityDataType.ChangeRoleEnd;
import static com.obex.lib.enums.UnityDataType.ChangeSenceEnd;
import static com.obex.lib.enums.UnityDataType.ManagerInitEnd;
import static com.obex.lib.enums.UnityDataType.PRELOADEND;
import static com.obex.lib.enums.UnityDataType.PlayAniEnd;
import static com.obex.lib.enums.UnityDataType.SmartHomeEnd;
import static com.obex.lib.enums.UnityDataType.UnityInit;

public class UnityManager {
    private static String TAG = "UnityManager";
    private static UnityManager sInstance;
    public AndroidProxy mUnityAndroidProxy;

    UnityCallbackListener callbackListener;//设置P2A照片的回调
    //根据FunctionKey，添加UnityCallBackListener,每次回调后都是回调到哪里去
    private HashMap<String, UnityCallbackListener> unitHashMap = new Hash