package com.obex.demo

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.obex.demo.model.*
import com.obex.lib.UnityCallbackListener
import com.obex.lib.UnityManager
import com.obex.lib.UnityPlayerActivity
import kotlinx.android.synthetic.main.main.*
import org.angmarch.views.SpinnerTextFormatter
import org.jetbrains.anko.doAsync

class MainActivity : UnityPlayerActivity(), UnityCallbackListener {


    val TAG = "MainActivity_Unity"


    var allAnimalList: MutableList<AnimalBean>? = null //动作列表

    var sceneList: MutableList<SceneBean>? = null//场景列表

    var roleList: MutableList<RoleBean>? = null//角色列表

    var currentSceneBean: SceneBean? = null//当前选中的场景类

    var currentAnimal: AnimalBean? = null//当前选中的动作类

    var currentRole: RoleBean? = null//当前角色

    var currentUI: UIBean? = null

    /**
     * 显示UI类 WID代表UI的id showUI接口时必须是对应的UID
     */
    val uiList = listOf<UIBean>(UIBean(3, "音乐UI"), UIBean(2, "天气UI"))

    var isShowU3D = true//是否显示3DUI


    var weatherSceneManager: WeatherSceneManager? = null//天气模拟类

    var isUnityIinitSuccess = false//是否Unity初始化成功


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        //将UnityPlayer 添加到对应的View 必须添加，否则不能初始化Unity
        ll_3d!!.addView(mUnityPlayer)
        addOrRemoveViewClick() //点击或者添加View事件
        UnityManager.getInstance().setLogLevel(4)//设置Log等级
        UnityManager.getInstance().addCallBackListener(this)
        initData()
    }

    /**
     * 初始化场景列表跟动作列表
     */
    private fun initData() {
        doAsync {
            //获取动作列表
            val anim = ResourceUtils.getJson(this@MainActivity, "StateDatabase.json")
            val animalType = object : TypeToken<List<AnimalBean>>() {}.type
            allAnimalList = Gson().fromJson<List<AnimalBean>>(anim, animalType) as MutableList<AnimalBean>?

            //获取场景列表
            val scene = ResourceUtils.getJson(this@MainActivity, "SceneDatabase.json")
            Log.d(TAG, "scene===" + scene)
            val sceneType = object : TypeToken<List<SceneBean>>() {}.type
            sceneList = Gson().fromJson<List<SceneBean>>(scene, sceneType) as MutableList<SceneBean>?


            //获取角色列表
            val role = ResourceUtils.getJson(this@MainActivity, "RoleDatabase.json")
            Log.d(TAG, "scene===" + scene)
            val roleType = object : TypeToken<List<RoleBean>>() {}.type
            roleList = Gson().fromJson<List<RoleBean>>(role, roleType) as MutableList<RoleBean>?

            //默认数据
            Log.d(TAG, "sceneList==" + sceneList.toString())
            currentSceneBean = sceneList?.get(0)
            currentAnimal = allAnimalList?.get(0)
            currentUI = uiList[0]

            runOnUiThread {
                //切换到UI线程操作数据
                //初始化列表
                initRoleSpinner()
                initSceneSpinner()
                initAnimalSceneSpinner()
                initUISpinner()
                allAnimalList?.let { AnimalManager.instant.setAnimalList(it) }
            }

        }
    }

    /**
     * 初始化角色列表数据
     */
    fun initRoleSpinner() {
        val textFormatter = SpinnerTextFormatter<RoleBean> { it ->
            SpannableString(it.strName)
        }
        spinner_role.setSpinnerTextFormatter(textFormatter)
        spinner_role.setSelectedTextFormatter(textFormatter)
        spinner_role.setOnSpinnerItemSelectedListener { parent, view, position, id ->
            currentRole = spinner_role.selectedItem as RoleBean;
            Log.e("MainActivity", "name====" + currentRole.toString())
            refreshScene(sceneList?.filter {
                it.gender == if (currentRole?.strName?.contains("女")!!) 2 else 1
            } as MutableList<SceneBean>)
        }
        roleList?.let {
            spinner_role.attachDataSource<RoleBean?>(it as List<RoleBean?>)
        }

    }

    private fun refreshScene(sceneList: MutableList<SceneBean>) {
        sceneList?.let {
            spinner_scene.attachDataSource(it as List<SceneBean>)
        }
    }

    /**
     * 初始化场景 数据
     */
    fun initSceneSpinner() {
        val textFormatter = SpinnerTextFormatter<SceneBean> { it ->
            SpannableString(it.strScene)
        }
        spinner_scene.setSpinnerTextFormatter(textFormatter)
        spinner_scene.setSelectedTextFormatter(textFormatter)
        spinner_scene.setOnSpinnerItemSelectedListener { parent, view, position, id ->
            currentSceneBean = spinner_scene.selectedItem as SceneBean
            Log.e("MainActivity", "name====" + currentSceneBean.toString())
            //  changeAnimalData()
            //每次选中场景根据场景ID 获取到该场景的所有动作ID 并刷新数据
            refreshAnlmalData(allAnimalList?.filter {
                it.nSceneID == currentSceneBean?.wdID
            } as List<AnimalBean>)
        }
        //首次加载女模场景
        sceneList?.filter {
            it.gender == 2
        }?.let { spinner_scene.attachDataSource<SceneBean?>(it) }
    }

    /**
     * 刷新动作列表数据
     */
    fun refreshAnlmalData(animalList: List<AnimalBean>) {
        animalList?.let {
            spinner_anim.attachDataSource<AnimalBean?>(it)
        }
    }


    /**
     * 初始化动作列表数据
     */
    fun initAnimalSceneSpinner() {
        val textFormatter = SpinnerTextFormatter<AnimalBean> { it ->
            SpannableString(it.strDes)
        }
        spinner_anim.setSpinnerTextFormatter(textFormatter)
        spinner_anim.setSelectedTextFormatter(textFormatter)
        spinner_anim.setOnSpinnerItemSelectedListener { parent, view, position, id ->
            currentAnimal = spinner_anim.selectedItem as AnimalBean;
            Log.e("MainActivity", "name====" + currentAnimal.toString())
        }
        allAnimalList?.filter {
            it.nSceneID == currentSceneBean?.wdID
        }.let {
            spinner_anim.attachDataSource<AnimalBean?>(it as List<AnimalBean?>)
        }


    }

    /**
     * 初始化UI列表数据
     */
    fun initUISpinner() {
        val textFormatter = SpinnerTextFormatter<UIBean> { it ->
            SpannableString(it.name)
        }
        spinner_ui.setSpinnerTextFormatter(textFormatter)
        spinner_ui.setSelectedTextFormatter(textFormatter)
        spinner_ui.setOnSpinnerItemSelectedListener { parent, view, position, id ->
            currentUI?.wID?.let {
                UnityManager.getInstance().hideUI("hideUi",it)//先隐藏之前的UI
            }
            currentUI = spinner_ui.selectedItem as UIBean
            Log.e("MainActivity", "name====" + currentUI.toString())
        }
        uiList?.let {
            spinner_ui.attachDataSource<UIBean?>(it)
        }
    }

    /**
     * Unity 未初始化完成不能调用接口
     */
    fun isUnityInit(): Boolean {
        if (!isUnityIinitSuccess) {
            Toast.makeText(this@MainActivity, "Unity未初始化完成，暂时不能调用Unity接口", Toast.LENGTH_SHORT).show()
        }
        return isUnityIinitSuccess
    }

    /**
     * UnityPlayer其实也是一个自定义View，我们需要将view加到想要显示的控件上，
     * 由用户自己控制
     */
    fun addOrRemoveViewClick() {
        add.setOnClickListener {
            isShowU3D = !isShowU3D
            showOrHide(isShowU3D)
        }

        /**
         * 切换选中的场景
         */
        change_scene.setOnClickListener {
            if (!isUnityInit()) {
                return@setOnClickListener
            }
            currentSceneBean?.wdID?.let { it1 -> UnityManager.getInstance().changeScene("ChangeScene", it1) }
        }

        /**
         * 播放选中的动作
         */
        play_animal.setOnClickListener {
            if (!isUnityInit()) {
                return@setOnClickListener
            }
            currentAnimal?.wdID?.let { it1 -> UnityManager.getInstance().playAnimation("PlayAnimal", it1) }
        }

        /**
         * 切换性别
         * 切换性别其实就是切换角色ID 每个角色ID都有自己的形象
         */
        change_sex.setOnClickListener {
            if (!isUnityInit()) {
                return@setOnClickListener
            }
            currentRole?.wdID?.let { UnityManager.getInstance().changeRole("ChangeRole", it) }
        }

        show_ui.setOnClickListener {
            if (!isUnityInit()) {
                return@setOnClickListener
            }
            currentUI?.wID?.let {
                when (it) {
                    2 -> {//天气UI显示
                        UnityManager.getInstance().showUI("ShowUI", it, WeatherModel())
                    }

                    3 -> {
                        val unityMusicModel = UnityMusicModel()
                        unityMusicModel.setIndex(2)
                        val list = listOf(
                            NLPSongInfo("刘德华", "忘情水"),
                            NLPSongInfo("周杰伦", "七里香"), "张学友", "吻别"
                        )
                        unityMusicModel.setNlpSongInfos(list.toTypedArray())
                        UnityManager.getInstance()
                            .showUI("ShowUI", 3, unityMusicModel)
                    }
                }
            }
        }

        hide_ui.setOnClickListener {
            if (!isUnityInit()) {
                return@setOnClickListener
            }
            currentUI?.wID?.let {
                UnityManager.getInstance().hideUI("HideUi", it)
            }
        }


        /**
         * 播放天气场景
         */
        weather.setOnClickListener {
            if (!isUnityInit()) {
                return@setOnClickListener
            }
            if (weatherSceneManager == null)
                weatherSceneManager = WeatherSceneManager(sceneList?.find {
                    it.wdID == 2
                })
            weatherSceneManager?.changeScene()

        }
    }


    /**
     * 显示或者隐藏UI  此处只是做动画缩放，用户可以直接操作View 父控件add或者move
     * flag  true显示  false隐藏
     */
    fun showOrHide(flag: Boolean) {
        if (flag) {
            ll_3d.background = null
        }
        val scaleX = ObjectAnimator.ofFloat(ll_3d, "scaleX", if (flag) 0.15f else 1.0f, if (flag) 1.0f else 0.15f)
        val scaleY = ObjectAnimator.ofFloat(ll_3d, "scaleY", if (flag) 0.15f else 1.0f, if (flag) 1.0f else 0.15f)
        val tranX = ObjectAnimator.ofFloat(ll_3d, View.TRANSLATION_X, if (flag) 0f else px2Dp(ll_3d.width - 100))
        val tranY = ObjectAnimator.ofFloat(ll_3d, View.TRANSLATION_Y, if (flag) 0f else px2Dp(ll_3d.height - 100))
        val set = AnimatorSet()
        set.play(tranX).with(tranY).with(scaleX).with(scaleY)
        set.duration = 1000
        set.start()

        set.addListener(object : AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                Log.e("EndAnimal", "动画结束")
                ll_3d.background =
                    if (flag) null else ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.background
                    ) as GradientDrawable
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

        })

    }

    /**
     * Unity初始化成功，只有这个地方回调Unity初始化成功，才能调用操作接口，如切换成功、播放动作等
     */
    override fun initComplete() {
        isUnityIinitSuccess = true
        Log.d(TAG, "Unity初始化完成了")
        Toast.makeText(this@MainActivity, "Unity初始化完成了", Toast.LENGTH_LONG).show()
        UnityManager.getInstance().changeScene("ChangeDefault", 1)//切换默认场景
    }

    /**
     * 切换场景成功。调用切换场景方法后都会在此处回调场景切换成功,然后根据场景做不同的操作
     * sceneId 场景回调ID
     */
    override fun changeSceneSuccess(sceneId: Int) {
        Log.e(TAG, "切换场景成功了====" + sceneId + "currentThread==" + Thread.currentThread().name)
        Toast.makeText(this@MainActivity, "切换 $sceneId 场景成功了", Toast.LENGTH_SHORT).show()

    }

    /**
     * 动作播放回调，每次用户播放动画结束都会在此处回调，这个时候用户可以接着播放下一个动作
     */
    override fun playAnimComplete(animId: Int) {
        Log.e(TAG, "动作播放结束====" + animId)
        Toast.makeText(this@MainActivity, "$animId 动作播放结束", Toast.LENGTH_SHORT).show()
    }


    override fun changeRoleSuccess() {
        Log.e(TAG, "切换角色成====")
        Toast.makeText(this@MainActivity, "切换角色成功", Toast.LENGTH_SHORT).show()
        //男女角色的场景及UI都不一样
        weatherSceneManager?.let {
            it.sceneBean = sceneList?.filter { it1 ->
                it1.wdID == if (currentRole?.strName?.contains("女")!!) 2 else 1002
            }?.first()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        mUnityPlayer.windowFocusChanged(hasFocus)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        UnityManager.getInstance().removeCallBacListener(this)
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2Dp(pxValue: Int): Float {
        val scale = getResources().getDisplayMetrics().density;
        return (pxValue / scale + 0.5f)
    }
}
