package com.obex.lib;


public interface AndroidProxy {

    /**
     * @param functionkey
     * @param config      json格式的配置数据
     */
    void InitConfig(String functionkey, String config);


    /**
     * 播放动画
     *
     * @param functionkey 接口标识
     * @param aniNameId   动画ID
      */
    public void PlayAnimation(String functionkey, int aniNameId);


    /**
     * 显示任务UI
     *
     * @param functionkey
     * @param canvasID
     * @param javaObject
     */
    public void ShowUI(String functionkey, int canvasID, Object javaObject);

    /**
     * 隐藏任务UI
     *
     * @param functionkey
     * @param canvasID
     */
    public void HideUI(String functionkey, int canvasID);

    /// <summary>
    /// 播放转场动画
    /// </summary>
    public void PlayTransAnimation(String functionkey, Object javaObject);

    /// <summary>
    /// 设置位置和旋转 javaObject 包含字段float[] posArray 长度为6 前三位是位置 后三位是旋转
    /// </summary>
    /// <param name="javaObject"></param>
    public void SetPlayerPosAndRot(String functionkey, Object javaObject);

    /// <summary>
    /// 切换场景
    /// </summary>
    /// <param name="sceneID"></param>
    public void ChangeScene(String functionkey, int sceneID);



    /// <summary>
    /// 刷新模块数据
    /// </summary>
    /// <param name="functionkey"></param>
    /// <param name="modelType">模块类型  参见ModelDefine枚举</param>
    /// <param name="javaObject">模块数据</param>

    public void RefreshData(String functionkey, int modelType, Object javaObject);

    /// <summary>
    /// 更换人物角色
    /// </summary>
    /// <param name="functionKey"></param>
    /// <param name="roleID">角色ID</param>
    public void ChangeRole(String functionKey, int roleID);


    /// <summary>
    /// 设置Log输出方式
    /// </summary>
    /// <param name="functionKey"></param>
    /// <param name="logLevel">等级，int值，0为无log，1为Error，2为warning，3为info，4为全部</param>
    public void SetLogLevel(String functionKey, int logLevel);
}
