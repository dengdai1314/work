package com.kenny.api.model;

import java.util.List;

/**
 * @author 29003
 * @description
 * @date 2019/9/10
 */
public class ImageBannerBean {

    /**
     * data : [{"desc":"腾讯、百度、京东面试真题，你能答对几道？","id":27,"imagePath":"https://www.wanandroid.com/blogimgs/9f40797e-4bf0-42fd-bcc6-9df7fdbda16a.jpeg","isVisible":1,"order":1,"title":"腾讯、百度、京东面试真题，你能答对几道？","type":0,"url":"https://mp.weixin.qq.com/s/yPeMJUw5k3MPg36-wVZzdA"},{"desc":"","id":6,"imagePath":"https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png","isVisible":1,"order":1,"title":"我们新增了一个常用导航Tab~","type":1,"url":"http://www.wanandroid.com/navi"},{"desc":"","id":20,"imagePath":"https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png","isVisible":1,"order":2,"title":"flutter 中文社区 ","type":1,"url":"https://flutter.cn/"},{"desc":"一起来做个App吧","id":10,"imagePath":"https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png","isVisible":1,"order":11,"title":"一起来做个App吧","type":1,"url":"http://www.wanandroid.com/blog/show/2"}]
     * errorCode : 0
     * errorMsg :
     */

    private int errorCode;
    private String errorMsg;
    private List<DataBean> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * desc : 腾讯、百度、京东面试真题，你能答对几道？
         * id : 27
         * imagePath : https://www.wanandroid.com/blogimgs/9f40797e-4bf0-42fd-bcc6-9df7fdbda16a.jpeg
         * isVisible : 1
         * order : 1
         * title : 腾讯、百度、京东面试真题，你能答对几道？
         * type : 0
         * url : https://mp.weixin.qq.com/s/yPeMJUw5k3MPg36-wVZzdA
         */

        private String desc;
        private int id;
        private String imagePath;
        private int isVisible;
        private int order;
        private String title;
        private int type;
        private String url;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public int getIsVisible() {
            return isVisible;
        }

        public void setIsVisible(int isVisible) {
            this.isVisible = isVisible;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
