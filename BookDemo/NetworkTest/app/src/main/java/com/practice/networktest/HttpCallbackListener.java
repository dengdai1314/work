package com.practice.networktest;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/1610:51
 * @description
 */
public interface HttpCallbackListener {
    //表示当服务器成功响应我们请求的时候调用，参数代表着服务器返回的数据
    void onFinish(String response);
    //表示当进行网络操作出现错误的时候调用，参数代表着错误的详细信息
    void onError(Exception e);
}
