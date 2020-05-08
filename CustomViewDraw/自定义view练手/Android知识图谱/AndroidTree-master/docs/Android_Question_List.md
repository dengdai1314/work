## Android Question List

> 基础

### 1.Activity生命周期

* 正常情况下的生命周期
  ![activity_lifecycle](../img/activity_lifecycle.png)
* 复用Activity情况下的生命周期

onNewIntent，在Activity启动模式为SingleTop/SingleTask时，复用Activity时，会回调onNewIntent方法

* Activity异常销毁时的生命周期

系统会先调用 `onSaveInstanceState()`，然后再使 Activity 变得易于销毁。系统会向该方法传递一个 `Bundle`，您可以在其中使用 `putString()` 和 `putInt()` 等方法以名称-值对形式保存有关 Activity 状态的信息。然后，如果系统终止您的应用进程，并且用户返回您的 Activity，则系统会重建该 Activity，并将 `Bundle` 同时传递给 `onCreate()` 和 `onRestoreInstanceState()`。您可以使用上述任一方法从 `Bundle` 提取您保存的状态并恢复该 Activity 状态。如果没有状态信息需要恢复，则传递给您的 `Bundle` 是空值（如果是首次创建该 Activity，就会出现这种情况）。

 ![restore_instance](../img/restore_instance.png)

### 2.Activity启动模式（LaunchMode）

### 3.ActivityThread

### 4.ActivityManagerService

### 5.Handler 、MessageQueue 、Looper三者的关系和原理

![Hadler机制图解](../img/handler.png)

### 6.AsyncTask 、HanlderThread 、IntentService 的原理和使用场景

AsyncTask这个API已经废弃好多年不用了

顾名思义，HandlerThread就是一个自带Looper的Thread

IntentService，自带HanderThread的轻量级服务，在handleMessage中处理逻辑，handleMessage处理完成后自己会调用stopSelf结束服务。

使用场景的话，自从有了RxJava，我是真没想到AsyncTask有啥使用场景了。

### 7.IPC通信机制

android首选进程通讯机制（IPC），android是建立在linux kernel之上的所以他支持linux的IPC方式，例如：网络链接进程通讯（Internet Process Connection）：管道（Pipe），信号（Signal）和跟踪（Trace），插口（Socket），报文队列（Message），共享内存（Share Memory）和信号量（Semaphore）。那么为什么还要出现binder机制那是因为它是针对移动端这种时效性快，资源消耗低而设计出来了，是移动端首选的进程通讯方式。从binder应用的范围就知道他重要性，除了Zygote进程和SystemServer进程之间使用的socket通讯之外，基本上其他进程通讯都是用的binder方式。

1. Android进程间通信（IPC）的实现方式：Bundle，文件共享，AIDL，Messenger，ContentProvider，Socket，BroadCastReceiver
2. Android进程间通信（IPC）的机制：Binder Parcelable（此处不在叙述Serializable）Parcelable接口在完成数据的序列化过程之后，通过Binder进行传输。实质上Parcelable包含序列化和反序列化

- 那么问题还是来了，为啥需要多进程？如何实现多进程？
  - 为啥要多进程？
    1. Android对单个应用所使用的最大内存做了限制。
    2. 在不同的应用之间共享数 据。

- 如何实现多进程？Manifest清单文件中声明process属性

- WebView开发的时候为啥一般会指定独立进程？ 
  - WebView导致的OOM问题
  - Android版本不同，采用了不同的内核，兼容性Crash
  - WebView代码质量，WebView和Native版本不一致，导致Crash

- 最近两年的开发没有使用到AIDL和Binder，知识点记住，面试要能答上来，具体细节可以在使用的时候查询。   

- 详见“跟面试官讲Binder系列”

  ```
  Binder驱动其实跟硬件没有什么关系，它就是一段运行在内核空间的代码，通过一个叫”/dev/binder”的文件在内核空间和用户空间来回搬数据。
  
  它是整个Binder机制的核心。正是通过它，Binder才能实现进程间的通信。
  
  因为Server, Client和ServiceManager是运行在用户空间，不同的进程，彼此是不能互相访问的。
  
  那么在不同的进程间，要进行数据的传递，只有通过内核空间来传递数据，
  
  而只有Binder驱动是工作在内核空间中的。
  
  它负责Binder节点的建立，负责Binder在进程间的传递，负责对Binder引用的计数管理，在不同进程间传输数据包等底层操作。
  ```


> Android View体系

### 1.View的工作原理/绘制过程

View的绘制工作是从ViewRoot的performTraversals方法开始，它经过measure/layout/draw三个过程才能最终将一个View绘制出来。ViewRoot对应于ViewRootImpl类，它是连接WindowManager和DecorView的纽带。View的三大流程均是通过ViewRoot来完成的。在ActivityThread中，当Activity对象被创建完毕后，会将DecorView添加到WIndow中，同时会创建ViewRootImpl对象，并将ViewRootImpl对象和DecorView建立关联。

![绘制过程](../img/dispatch_draw.jpg)
![绘制过程](../img/dispatch_draw_1.jpg)

View的三种测量模式：

* UNSPECIFIED：父容器不对View有任何限制，要多大给多大。
* EXACTLY： 父容器已经检测出View所需要的精确大小，这个时候View的大小就是SpecSIze所指定的值。他对应于LayoutParams中的match_parent和具体的数值这两种模式。
* AT_MOST：父容器指定了一个可用大小SpecSize。View的大小不能超过这个值，它对应于LayoutParams的wrap_content。（例如：父布局width或者height设置一个具体值，或者match_parent,子布局设置为wrap_content。此时子布局的最大width或者height就是父布局的width或者height）。使用这种测量模式的View，设置的一定是wrap_content。

### 2.Window View Activity三者的关系

追问Acitivty如何和Window关联

* Window

```
Abstract base class for a top-level window look and behavior policy.  An
instance of this class should be used as the top-level view added to the
window manager. It provides standard UI policies such as a background, title
area, default key processing, etc.
```

* View 负责实际绘制/显示内容
* Activity

Android中真正展示给用户的是window和view，activity在android中所的作用主要是处理一些逻辑问题，比如生命周期的管理、建立窗口等。在android中，窗口的管理还是比较重要的一块，因为他直接负责把内容展示给用户，并和用户进行交互。响应用户的输入等。 View是真正显示的矩形区域，DecorView是顶层View，也就是主View。 相互之间的关系可以理解为一个Activity包含了一个Window，这个Window其实是一个PhoneWindow，在PhoneWindow中包含了DecorView，变量名称为mDecor，mDecor有一个子View，这个子View的布局方式根据设定的主题来确定，在这个子View的xml布局中包含了一个FrameLayout元素，这个FrameLayout元素的id为content，这个content对应于PhoneWindow中的mContentParent变量，用户自定义的布局作为mContentParent的子View存在,一般情况下mContentParnet只有一个子View，如果在Activity调用addView方式实际上是给PhoneWindow中的mContentParent添加子View，由于mContentParent是一个FrameLayout,因此新的子view会覆盖通过setContentView添加的子view。

https://www.cnblogs.com/aademeng/articles/6538926.html

https://blog.csdn.net/ali18510953445/article/details/77915702

https://www.jianshu.com/p/049df709ddbf

* ViewRootImpl

ViewRoot 对应于ViewRootImpl类，View的三大流程都是通过ViewRoot来完成的。在ActivityThread中，当Activity对象被创建完毕之后，会将DecorView添加到Window中，同时会创建ViewRootImpl对象，并将ViewRootImpl对象和DecorView建立关联。

* DecorView 顶级View

### 3.自定义View的具体过程

对于自定义View来讲，重写onDraw，在onDraw方法中写入自己的绘制逻辑

对于自定义ViewGroup来讲，要根据自己的需求重新onMeasure方法，测量并计算子View的位置，然后重写onLayout方法，将计算好的位置传递给子View的layout方法，逐层递归调用

1. 自定义View已经基本掌握（精通），自定义ViewGroup的关键在于精通onMeasure中关于测量的方法和MeasureSpec的三种不同的模式，onLayout只是简单地把子View布置到该放置的地方，因此onMeasure是自定义ViewGroup的精髓所在。
2. 要精准地理解View绘制的流程/方法/概念。

### 4.onMeasure的具体过程，先measure子view还是自己

调用measureChildren去测量每一个子View的尺寸并记录，最后根据子View的尺寸来计算父View的尺寸，并调用父View的super.onMeasure/setMeasureDemension方法，将测量的结果保存

### 5.onDraw的具体过程，先draw子view还是自己

onDraw在整个绘制流程中，是用于绘制主体的方法，对于ViewGroup本身而言，onDraw方法是一个空实现，onDraw调用完成后，会调用dispatchDraw，递归地去调用子View的onDraw方法，绘制子View。

### 6.事件分发机制

关于事件分发机制，可以从两个角度讲解

* 其一是事件分发的原理和流程

其实质上，是从Activity向下逐层递归调用dispatchTouchEvent()方法，dispatchTouchEvent()本身是一个调度方法，其内部调用onInterceptTouchEvent，判断当前View是否要拦截该事件，如果不拦截，接着调用onTouchEvent，将事件向下分发，直到有子View返回true，消费该事件。

其中，onTouchEvent中，如果子View在该事件的action.DOWN事件返回了false，那么本次触摸以后的事件子View都不会收到

* 其二是处理事件冲突的具体思路：

1. 实现自定义触摸反馈，直接在onTouchEvent中处理事件就OK
2. 父View想要事件，直接在onInterceptTouchEvent中返回true进行拦截。此时，父View对给子View分发一个action.CANCEL的事件，通知子View恢复状态。
3. 子View不想要父View拦截，调用requestDisallowInterceptTouchEvent()方法，请求父View不要拦截，该方法只对本地事件有效。

### 7.Android Framework层有没有了解过，说说 Window 窗口添加的过程

https://www.jianshu.com/p/049df709ddbf

每一个Activity组件都有一个关联的Window对象，用来描述一个应用程序窗口。每一个应用程序窗口内部又包含有一个View对象，用来描述应用程序窗口的视图。上文分析了创建DecorView的过程，现在则要把DecorView添加到Window对象中。而要了解这个过程，我们首先要简单先了解一下Activity的创建过程：
 首先，在ActivityThread#handleLaunchActivity中启动Activity，在这里面会调用到Activity#onCreate方法，从而完成上面所述的DecorView创建动作，当onCreate()方法执行完毕，在handleLaunchActivity方法会继续调用到ActivityThread#handleResumeActivity方法，

先看①号代码处，实例化了ViewRootImpl类，接着，在②号代码处，调用ViewRootImpl#setView方法，并把DecorView作为参数传递进去，在这个方法内部，会通过跨进程的方式向WMS（WindowManagerService）发起一个调用，从而将DecorView最终添加到Window上，在这个过程中，ViewRootImpl、DecorView和WMS会彼此关联，至于详细过程这里不展开来说了。
 最后通过WMS调用ViewRootImpl#performTraverals方法开始View的测量、布局、绘制流程

### 8. 动画（主要是属性动画）

这块的内容从hencoder网站及笔记复习

### 9.RecyclerView 和 ListView 的相同和不同点，在 item 回收上有什么不同

* RecyclerView支持三种布局方式，更加灵活
  1. LinearLayoutManager，可以支持水平和竖直方向上滚动的列表。
  2. StaggeredGridLayoutManager，可以支持交叉网格风格的列表，类似于瀑布流或者Pinterest。
  3. GridLayoutManager，支持网格展示，可以水平或者竖直滚动，如展示图片的画廊。
* 实现item动画更方便，RecyclerView.ItemAnimator则被提供用于在RecyclerView添加、删除或移动item时处理动画效果
* RecyclerView.ItemDecoration可以更个性化地实现分割线
* 将测量和布局过程完全委托给LayoutManager
* ItemTouchHelper/PagerSnapHelper辅助API实现拖动等定制化效果

https://www.aliyun.com/jiaocheng/47916.html 

https://blog.csdn.net/qq_23012315/article/details/50807224

源码解读

```
public class RecyclerView extends ViewGroup implements ScrollingView, NestedScrollingChild2 
类的声明来看，RecyclerView实现了ScrollingView，可以水平/竖直方向滚动，同时还实现了NestedScrollingChild2，支持内部滚动，可以作为NestScrollingParent的子View使用，通过layoutBehavior来限定nestedScroll的响应
```

核心关键类：

RecyclerView

LayoutManger

Recycler

总结：

* 支持三种布局
* 支持NestedScrolling
* 测量布局委托给LayoutManager，专注于Recycler（View的回收和复用）

那些情况下可以直接使用ListView

ListView设计层级比RecyclerView要简单，在列表数据简单，ItemView层级简单，且很少进行刷新操作的时候，完全可以使用ListView实现，RecyclerView会创建Recycler()等内部集合，不太合算。

### 10. App性能优化

港真，以前的两个公司，对App性能都不是很关注，导致对性能优化居然没有概念。总结一下，分为：

* 内存检测 防止Crash和ANR，Monitor（AndroidStudio自带工具）/Leak Canary

  * 一些典型的ANR问题场景

    1 最常见的错误，UI 线程等待其他线程释放某个锁，导致UI线程无法处理用户输入
    2 游戏中每帧动画都进行了比较耗时的大量计算，导致CPU忙不过来
    3 Web应用中，网络状态不稳定，而界面在等待网络数据
    4 UI线程中进行了一些磁盘IO(包括数据库，SD卡等等)的耗时操作
    5 手机被其他App占用着CPU，自己获取不到足够的CPU时间片

  *  通过ANR日志定位问题

     当ANR发生时，往往通过Logcat和traces文件(目录/data/anr)的相关信息输出来定位问题，主要包括以下几个方面：
     1 基本信息，包括进程号名，进程号，包名，系统build号，ANR类型等等
     2 CPU使用信息，包括活跃进程的CPU平均占用率，IO情况等
     3 线程堆栈信息，所属进程包括发生ANR的进程，其父进程，最近有活动的3个进程等

* 卡顿优化 

  * 页面层级过深

    *  xml布局文件优化，使用ConstraintLayout简化布局层次
    *  使用ViewStub延迟加载View
    *  删除控件中的无用属性
  * 绘制性能优化
    * 移除xml中多余的背景
    * 减少过度绘制
    * 自定义View优化，边缘检测，布局超过屏幕边缘不再绘制。使用 [canvas.clipRect()](https://blog.csdn.net/lovexieyuan520/article/details/50698320) 帮助系统识别那些可见的区域，只有在这个区域内才会被绘制。//这个我真真地没有尝试，应该要自己把有效地rect当作参数传递进去吧，不然系统是无法知道要不要绘制的。
  * 刷新优化
    * 减少刷新次数，灵活利用缓存及限时刷新；
    * 缩小刷新区域，局部刷新，避免多余请求；
  * 动画优化
  * 硬件加速？

* 耗电优化

* APK瘦身 Lint工具（代码/布局文件/资源），WebP ，代码混淆

> 热点技术

### 1.热修复

https://www.jianshu.com/p/704cac3eb13d

### 2.插件化

### 3.模块化/组件化的原理，还有一些组件化平时使用的问题

### 4.消息推送有没有做过，推送到达率的问题

项目中使用的推送一般都是用第三方SDK，当然也可以自己写一个心跳。

至于推送到达率，海外的话，使用Firebase，不存在这个问题。

国内的话，只能寄希望于第三方SDK的策略，自己搭建推送服务，成本高，且到达率不易控制。到达率说白了根本还是进程保活，JobScheduler可以做一些后台任务。

> 架构

### 1.说说 apk 打包流程以及多渠道打包
多渠道打包这个非常easy了，重点说一下APK的结构和打包流程
https://www.jianshu.com/p/7c288a17cda8

![APK结构](../img/apk_structure.png)
![打包流程](../img/apk_tool.png)

### 2.MVC MVP MVVM的理解



### 3.应用程序崩溃统计以及数据分析



## 开源框架

### 1. OKHttp 

* [OkHttp请求网络的流程分析](okhttp/OkHttp源码研读.md)
* [OkHttpClient源码解读](okhttp/OkHttp_OkHttpClient.md)
* [Dispatcher源码解读](okhttp/OkHttp_Dispatcher.md)
* [Interceptor源码解读](https://www.jianshu.com/p/64111e0db93c)

Interceptor的解读，[六号表哥](https://www.jianshu.com/u/8173f323f5bb) 的文章写的非常好，不再自行解读。



### 2. Retrofit

### 3. Glide 图片加载的三级缓存机制 以及 Bitmap 优化

* 首先是with方法，参数为非Application时，调用的是with(Activity/Fragment)方法；当with方法传入的是Activity或者Fragment时，会创建一个隐藏的Fragment加载到Activity／Fragment上，用于保证Glide加载图片的生命周期和Actiivty／Fragment一致，当退出Activity/Fragment时，图片停止加载。

   

1. with：创建RequestManager，并根据传入参数，初始化好对应的生命周期
2. load：用于初始化好图片下载、图片解析、图片转换的各个对象，用于后续流程
3. into：在线程池中去执行EngineRunnale的run方法，用HttpURLConnection下载图片、用BitmapFactory.decodeStream完成解码、然后把Bitmap转换成可统一展示的GlideBitmapDrawable、最后通过Handler发消息，在主线程中给ImageView设置图片



内存缓存有两级 LruCache/DiskLruCache



### LinkedHashMap

HashMap和双向链表合二为一即是LinkedHashMap。所谓LinkedHashMap，其落脚点在HashMap，因此更准确地说，它是一个将所有Entry节点链入一个双向链表的HashMap。由于LinkedHashMap是HashMap的子类，所以LinkedHashMap自然会拥有HashMap的所有特性。比如，LinkedHashMap的元素存取过程基本与HashMap基本类似，只是在细节实现上稍有不同。当然，这是由LinkedHashMap本身的特性所决定的，因为它额外维护了一个双向链表用于保持迭代顺序。此外，LinkedHashMap可以很好的支持LRU算法，笔者在第七节便在LinkedHashMap的基础上实现了一个能够很好支持LRU的结构。

### 4. RxJava flatMap和map的作用

map操作符就是对数据源做一个映射，返回的还是一个对象

flatMap操作符是根据数据源，生成一个流，并这个流和当前的流merge合并

### 5.Dagger 2 什么是依赖注入？能说几个依赖注入的库么？你使用过哪些？

### 6.线程和进程概念上的不同？多线程以及多进程使用的场景？

* 多进程的有点及使用场景
  * Android对单个进程有内存限制，超过上限后会oom
  * 一个进程出现异常crash后，不会对主进程产生影响