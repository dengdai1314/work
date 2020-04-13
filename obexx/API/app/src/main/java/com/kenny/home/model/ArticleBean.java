package com.kenny.home.model;

import java.util.List;

/**
 * @author 29003
 * @description
 * @date 2019/9/11
 */
public class ArticleBean extends BaseBean {

    /**
     * data : {"curPage":1,"datas":[{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10655,"link":"https://juejin.im/post/5dd24854f265da0c02111fe8","niceDate":"1小时前","niceShareDate":"1小时前","origin":"","prefix":"","projectLink":"","publishTime":1575358181000,"selfVisible":0,"shareDate":1575358181000,"shareUser":"lcfspark","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"菜鸡面试第一弹","type":0,"userId":28772,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10654,"link":"https://www.jianshu.com/p/43e21be8d849","niceDate":"6小时前","niceShareDate":"6小时前","origin":"","prefix":"","projectLink":"","publishTime":1575338709000,"selfVisible":0,"shareDate":1575338709000,"shareUser":"吊儿郎当","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"面试经常问的Handler机制，以及Handler的错误使用场景","type":0,"userId":2554,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":73,"chapterName":"面试相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10641,"link":"https://www.jianshu.com/p/8c52922e5d1e?utm_source=desktop&amp;utm_medium=timeline","niceDate":"16小时前","niceShareDate":"16小时前","origin":"","prefix":"","projectLink":"","publishTime":1575303633000,"selfVisible":0,"shareDate":1575303206000,"shareUser":"鸿洋","superChapterId":185,"superChapterName":"热门专题","tags":[],"title":"Android 中高级面试必知必会","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":313,"chapterName":"字节码","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10642,"link":"https://www.jianshu.com/p/abd1b1b8d3f3?utm_source=desktop&amp;utm_medium=timeline","niceDate":"16小时前","niceShareDate":"16小时前","origin":"","prefix":"","projectLink":"","publishTime":1575303615000,"selfVisible":0,"shareDate":1575303235000,"shareUser":"鸿洋","superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"ASM Core Api 详解","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":229,"chapterName":"AOP","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10643,"link":"https://www.jianshu.com/p/0799aa19ada1","niceDate":"16小时前","niceShareDate":"16小时前","origin":"","prefix":"","projectLink":"","publishTime":1575303597000,"selfVisible":0,"shareDate":1575303286000,"shareUser":"鸿洋","superChapterId":227,"superChapterName":"注解 & 反射 & AOP","tags":[],"title":"一文读懂 AOP | 你想要的最全面 AOP 方法探讨","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10625,"link":"https://www.jianshu.com/p/8c9dae858193","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1575273237000,"selfVisible":0,"shareDate":1575273237000,"shareUser":"吊儿郎当","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"LruCache缓存机制，深入浅出，发现了一个源码bug","type":0,"userId":2554,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":507,"chapterName":"BlockCanary","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10612,"link":"https://juejin.im/post/5de336d96fb9a0716d5c0814","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1575215824000,"selfVisible":0,"shareDate":1575215798000,"shareUser":"鸿洋","superChapterId":461,"superChapterName":"常见开源库源码解析","tags":[],"title":"主流开源框架之BlockCanary深入了解","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":313,"chapterName":"字节码","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10598,"link":"https://www.jianshu.com/p/e5062d62a3d1","niceDate":"1天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1575215751000,"selfVisible":0,"shareDate":1575188328000,"shareUser":"鸿洋","superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"认识 .class 文件的字节码结构","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":126,"chapterName":"绘图相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10610,"link":"https://juejin.im/post/5de36c43f265da05de5881e8","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1575215732000,"selfVisible":0,"shareDate":1575208588000,"shareUser":"AprilEyon","superChapterId":126,"superChapterName":"自定义控件","tags":[],"title":"高级 UI 成长之路 (四) Paint 渲染/滤镜/xfermode 使用","type":0,"userId":3559,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":182,"chapterName":"JNI编程","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10611,"link":"https://juejin.im/post/5de3dad9e51d456ab0531a2a","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1575215718000,"selfVisible":0,"shareDate":1575214588000,"shareUser":"蓝师傅","superChapterId":182,"superChapterName":"JNI","tags":[],"title":"手撸网易云进阶课程-性能优化之NDK高效加载GIF","type":0,"userId":1871,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10608,"link":"https://blog.csdn.net/hfy8971613/article/details/103336951","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1575195507000,"selfVisible":0,"shareDate":1575195507000,"shareUser":"胡飞洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"监听网络变化--含7.0以上适配","type":0,"userId":31360,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>RT，挺有意思的问题，为什么呢？<\/p>\r\n\r\n<p>这是星球里面一位朋友的提问，<a style=\"font-size:14px;\" href=\"https://wanandroid.com/blog/show/2701\">想加入可以点这里<\/a>。<\/p>\r\n","envelopePic":"","fresh":false,"id":10482,"link":"https://www.wanandroid.com/wenda/show/10482","niceDate":"2天前","niceShareDate":"2019-11-26 00:10","origin":"","prefix":"","projectLink":"","publishTime":1575118168000,"selfVisible":0,"shareDate":1574698219000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 | Activity启动流程中，大部分都是用Binder通讯，为啥跟Zygote通信的时候要用socket呢？","type":0,"userId":2,"visible":1,"zan":14},{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10571,"link":"https://juejin.im/post/5de1db1c51882560a2323966","niceDate":"2019-11-30 15:16","niceShareDate":"2019-11-30 15:16","origin":"","prefix":"","projectLink":"","publishTime":1575098208000,"selfVisible":0,"shareDate":1575098188000,"shareUser":"1063523767","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"[即学即用] Android开发&mdash;&mdash;状态模式","type":0,"userId":6924,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"谷歌开发者","chapterId":415,"chapterName":"谷歌开发者","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10589,"link":"https://mp.weixin.qq.com/s/kO_Sj_bZSqtgxbhL8GD-dg","niceDate":"2019-11-30 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1575043200000,"selfVisible":0,"shareDate":1575119346000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/415/1"}],"title":"Flutter 启动流程简析 | 开发者说&middot;DTalk","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10562,"link":"https://blog.csdn.net/Rain_9155/article/details/103318029","niceDate":"2019-11-29 21:14","niceShareDate":"2019-11-29 21:14","origin":"","prefix":"","projectLink":"","publishTime":1575033258000,"selfVisible":0,"shareDate":1575033252000,"shareUser":"rain9155","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"你知道有多少种方式实现单例模式？","type":0,"userId":12884,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10544,"link":"https://juejin.im/post/5dd22694e51d4561da085408","niceDate":"2019-11-29 00:38","niceShareDate":"2019-11-28 21:54","origin":"","prefix":"","projectLink":"","publishTime":1574959112000,"selfVisible":0,"shareDate":1574949244000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"羞！扒开字节码，我竟发现这个.....","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10548,"link":"https://www.jianshu.com/p/248ef6db02c5","niceDate":"2019-11-29 00:38","niceShareDate":"2019-11-29 00:20","origin":"","prefix":"","projectLink":"","publishTime":1574959100000,"selfVisible":0,"shareDate":1574958045000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"知乎 Android 客户端三方库敏感代码扫描机制 - FindDanger","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"code小生","chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10579,"link":"https://mp.weixin.qq.com/s/rDzDZCR6MSgvu7g4MVBVHg","niceDate":"2019-11-29 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1574956800000,"selfVisible":0,"shareDate":1575118733000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"无侵入式获取全局 Context","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"郭霖","chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10583,"link":"https://mp.weixin.qq.com/s/sTDUzMKcslY--gDq4iL3fw","niceDate":"2019-11-29 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1574956800000,"selfVisible":0,"shareDate":1575118985000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"讲一讲移动端跨平台技术的演进之路","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10538,"link":"https://juejin.im/post/5dde5f305188257a0d22ae25","niceDate":"2019-11-28 11:04","niceShareDate":"2019-11-28 11:04","origin":"","prefix":"","projectLink":"","publishTime":1574910287000,"selfVisible":0,"shareDate":1574910273000,"shareUser":"winlee28","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android Jetpack架构组件 &mdash; Room入坑详解 ","type":0,"userId":25211,"visible":1,"zan":0}],"offset":0,"over":false,"pageCount":380,"size":20,"total":7600}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * curPage : 1
         * datas : [{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10655,"link":"https://juejin.im/post/5dd24854f265da0c02111fe8","niceDate":"1小时前","niceShareDate":"1小时前","origin":"","prefix":"","projectLink":"","publishTime":1575358181000,"selfVisible":0,"shareDate":1575358181000,"shareUser":"lcfspark","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"菜鸡面试第一弹","type":0,"userId":28772,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10654,"link":"https://www.jianshu.com/p/43e21be8d849","niceDate":"6小时前","niceShareDate":"6小时前","origin":"","prefix":"","projectLink":"","publishTime":1575338709000,"selfVisible":0,"shareDate":1575338709000,"shareUser":"吊儿郎当","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"面试经常问的Handler机制，以及Handler的错误使用场景","type":0,"userId":2554,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":73,"chapterName":"面试相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10641,"link":"https://www.jianshu.com/p/8c52922e5d1e?utm_source=desktop&amp;utm_medium=timeline","niceDate":"16小时前","niceShareDate":"16小时前","origin":"","prefix":"","projectLink":"","publishTime":1575303633000,"selfVisible":0,"shareDate":1575303206000,"shareUser":"鸿洋","superChapterId":185,"superChapterName":"热门专题","tags":[],"title":"Android 中高级面试必知必会","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":313,"chapterName":"字节码","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10642,"link":"https://www.jianshu.com/p/abd1b1b8d3f3?utm_source=desktop&amp;utm_medium=timeline","niceDate":"16小时前","niceShareDate":"16小时前","origin":"","prefix":"","projectLink":"","publishTime":1575303615000,"selfVisible":0,"shareDate":1575303235000,"shareUser":"鸿洋","superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"ASM Core Api 详解","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":229,"chapterName":"AOP","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10643,"link":"https://www.jianshu.com/p/0799aa19ada1","niceDate":"16小时前","niceShareDate":"16小时前","origin":"","prefix":"","projectLink":"","publishTime":1575303597000,"selfVisible":0,"shareDate":1575303286000,"shareUser":"鸿洋","superChapterId":227,"superChapterName":"注解 & 反射 & AOP","tags":[],"title":"一文读懂 AOP | 你想要的最全面 AOP 方法探讨","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10625,"link":"https://www.jianshu.com/p/8c9dae858193","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1575273237000,"selfVisible":0,"shareDate":1575273237000,"shareUser":"吊儿郎当","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"LruCache缓存机制，深入浅出，发现了一个源码bug","type":0,"userId":2554,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":507,"chapterName":"BlockCanary","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10612,"link":"https://juejin.im/post/5de336d96fb9a0716d5c0814","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1575215824000,"selfVisible":0,"shareDate":1575215798000,"shareUser":"鸿洋","superChapterId":461,"superChapterName":"常见开源库源码解析","tags":[],"title":"主流开源框架之BlockCanary深入了解","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":313,"chapterName":"字节码","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10598,"link":"https://www.jianshu.com/p/e5062d62a3d1","niceDate":"1天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1575215751000,"selfVisible":0,"shareDate":1575188328000,"shareUser":"鸿洋","superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"认识 .class 文件的字节码结构","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":126,"chapterName":"绘图相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10610,"link":"https://juejin.im/post/5de36c43f265da05de5881e8","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1575215732000,"selfVisible":0,"shareDate":1575208588000,"shareUser":"AprilEyon","superChapterId":126,"superChapterName":"自定义控件","tags":[],"title":"高级 UI 成长之路 (四) Paint 渲染/滤镜/xfermode 使用","type":0,"userId":3559,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":182,"chapterName":"JNI编程","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10611,"link":"https://juejin.im/post/5de3dad9e51d456ab0531a2a","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1575215718000,"selfVisible":0,"shareDate":1575214588000,"shareUser":"蓝师傅","superChapterId":182,"superChapterName":"JNI","tags":[],"title":"手撸网易云进阶课程-性能优化之NDK高效加载GIF","type":0,"userId":1871,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10608,"link":"https://blog.csdn.net/hfy8971613/article/details/103336951","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1575195507000,"selfVisible":0,"shareDate":1575195507000,"shareUser":"胡飞洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"监听网络变化--含7.0以上适配","type":0,"userId":31360,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>RT，挺有意思的问题，为什么呢？<\/p>\r\n\r\n<p>这是星球里面一位朋友的提问，<a style=\"font-size:14px;\" href=\"https://wanandroid.com/blog/show/2701\">想加入可以点这里<\/a>。<\/p>\r\n","envelopePic":"","fresh":false,"id":10482,"link":"https://www.wanandroid.com/wenda/show/10482","niceDate":"2天前","niceShareDate":"2019-11-26 00:10","origin":"","prefix":"","projectLink":"","publishTime":1575118168000,"selfVisible":0,"shareDate":1574698219000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 | Activity启动流程中，大部分都是用Binder通讯，为啥跟Zygote通信的时候要用socket呢？","type":0,"userId":2,"visible":1,"zan":14},{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10571,"link":"https://juejin.im/post/5de1db1c51882560a2323966","niceDate":"2019-11-30 15:16","niceShareDate":"2019-11-30 15:16","origin":"","prefix":"","projectLink":"","publishTime":1575098208000,"selfVisible":0,"shareDate":1575098188000,"shareUser":"1063523767","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"[即学即用] Android开发&mdash;&mdash;状态模式","type":0,"userId":6924,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"谷歌开发者","chapterId":415,"chapterName":"谷歌开发者","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10589,"link":"https://mp.weixin.qq.com/s/kO_Sj_bZSqtgxbhL8GD-dg","niceDate":"2019-11-30 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1575043200000,"selfVisible":0,"shareDate":1575119346000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/415/1"}],"title":"Flutter 启动流程简析 | 开发者说&middot;DTalk","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10562,"link":"https://blog.csdn.net/Rain_9155/article/details/103318029","niceDate":"2019-11-29 21:14","niceShareDate":"2019-11-29 21:14","origin":"","prefix":"","projectLink":"","publishTime":1575033258000,"selfVisible":0,"shareDate":1575033252000,"shareUser":"rain9155","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"你知道有多少种方式实现单例模式？","type":0,"userId":12884,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10544,"link":"https://juejin.im/post/5dd22694e51d4561da085408","niceDate":"2019-11-29 00:38","niceShareDate":"2019-11-28 21:54","origin":"","prefix":"","projectLink":"","publishTime":1574959112000,"selfVisible":0,"shareDate":1574949244000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"羞！扒开字节码，我竟发现这个.....","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10548,"link":"https://www.jianshu.com/p/248ef6db02c5","niceDate":"2019-11-29 00:38","niceShareDate":"2019-11-29 00:20","origin":"","prefix":"","projectLink":"","publishTime":1574959100000,"selfVisible":0,"shareDate":1574958045000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"知乎 Android 客户端三方库敏感代码扫描机制 - FindDanger","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"code小生","chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10579,"link":"https://mp.weixin.qq.com/s/rDzDZCR6MSgvu7g4MVBVHg","niceDate":"2019-11-29 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1574956800000,"selfVisible":0,"shareDate":1575118733000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"无侵入式获取全局 Context","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"郭霖","chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10583,"link":"https://mp.weixin.qq.com/s/sTDUzMKcslY--gDq4iL3fw","niceDate":"2019-11-29 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1574956800000,"selfVisible":0,"shareDate":1575118985000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"讲一讲移动端跨平台技术的演进之路","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10538,"link":"https://juejin.im/post/5dde5f305188257a0d22ae25","niceDate":"2019-11-28 11:04","niceShareDate":"2019-11-28 11:04","origin":"","prefix":"","projectLink":"","publishTime":1574910287000,"selfVisible":0,"shareDate":1574910273000,"shareUser":"winlee28","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android Jetpack架构组件 &mdash; Room入坑详解 ","type":0,"userId":25211,"visible":1,"zan":0}]
         * offset : 0
         * over : false
         * pageCount : 380
         * size : 20
         * total : 7600
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * apkLink :
             * audit : 1
             * author :
             * chapterId : 502
             * chapterName : 自助
             * collect : false
             * courseId : 13
             * desc :
             * envelopePic :
             * fresh : true
             * id : 10655
             * link : https://juejin.im/post/5dd24854f265da0c02111fe8
             * niceDate : 1小时前
             * niceShareDate : 1小时前
             * origin :
             * prefix :
             * projectLink :
             * publishTime : 1575358181000
             * selfVisible : 0
             * shareDate : 1575358181000
             * shareUser : lcfspark
             * superChapterId : 494
             * superChapterName : 广场Tab
             * tags : []
             * title : 菜鸡面试第一弹
             * type : 0
             * userId : 28772
             * visible : 1
             * zan : 0
             */

            private String apkLink;
            private int audit;
            private String author;
            private int chapterId;
            private String chapterName;
            private boolean collect;
            private int courseId;
            private String desc;
            private String envelopePic;
            private boolean fresh;
            private int id;
            private String link;
            private String niceDate;
            private String niceShareDate;
            private String origin;
            private String prefix;
            private String projectLink;
            private long publishTime;
            private int selfVisible;
            private long shareDate;
            private String shareUser;
            private int superChapterId;
            private String superChapterName;
            private String title;
            private int type;
            private int userId;
            private int visible;
            private int zan;
            private List<?> tags;

            private boolean top;
            private int originId;

            public boolean isTop() {
                return top;
            }

            public void setTop(boolean top) {
                this.top = top;
            }

            public int getOriginId() {
                return originId;
            }

            public void setOriginId(int originId) {
                this.originId = originId;
            }

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public int getAudit() {
                return audit;
            }

            public void setAudit(int audit) {
                this.audit = audit;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public boolean isFresh() {
                return fresh;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getNiceShareDate() {
                return niceShareDate;
            }

            public void setNiceShareDate(String niceShareDate) {
                this.niceShareDate = niceShareDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public int getSelfVisible() {
                return selfVisible;
            }

            public void setSelfVisible(int selfVisible) {
                this.selfVisible = selfVisible;
            }

            public long getShareDate() {
                return shareDate;
            }

            public void setShareDate(long shareDate) {
                this.shareDate = shareDate;
            }

            public String getShareUser() {
                return shareUser;
            }

            public void setShareUser(String shareUser) {
                this.shareUser = shareUser;
            }

            public int getSuperChapterId() {
                return superChapterId;
            }

            public void setSuperChapterId(int superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
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

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

            public List<?> getTags() {
                return tags;
            }

            public void setTags(List<?> tags) {
                this.tags = tags;
            }
        }
    }
}
