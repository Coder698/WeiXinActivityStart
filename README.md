##WeiXinActivityStart
仿照微信开启关闭activity的动画,手势关闭activity的动画

自定义布局, 监听手势滑动,做出activity关闭动画

其实就是把上一个activity的布局view绘制城bitmap, 然后包在新开启的activity布局外面,监听里面那层view的滑动事件,做出处理,监听滑动后的位置,如果到了临界点,那么关闭界面,这个时候我们把activity的关闭动画取消,让activity立即小时即可

同理,可以写出下拉关闭,上拉关闭,右滑关闭

也可以使用viewDrawHelp实现,并且更加简单,但是需要导入一个第三方jar

ps: 代码只提供思路

![image](https://github.com/Zhaoss/WeiXinActivityStart/blob/master/image/1.png?raw=true)
![image](https://github.com/Zhaoss/WeiXinActivityStart/blob/master/image/2.png?raw=true)
