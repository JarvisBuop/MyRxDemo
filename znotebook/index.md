###
Q:Android播放短暂声音

A:使用[SoundManager.java](../ToolLib/zlibrary/src/main/java/com/zjy/zlibrary/util/SoundManager.java)

---

Q:TextView展示多行中文 行尾不对齐。

A:使用[AlignTextView.java](../ToolLib/zlibrary/src/main/java/com/zjy/zlibrary/widget/AlignTextView.java)&&查看[github](https://github.com/androiddevelop/AlignTextView)

---
Q:Android Studio 如何支持lambda表达式

A:[链接](http://gaomf.cn/2016/09/26/%E5%9C%A8Android%20Studio%E4%B8%AD%E4%BD%BF%E7%94%A8Lambda%E8%A1%A8%E8%BE%BE%E5%BC%8F/)

   遇到了一个错误
   ```
   编译器 (1.8.0_40) 中出现异常错误。 如果在 Bug Parade 中没有找到该错误, 请在 Java Developer Connection (http://java.sun.com/webapps/bugreport) 中建立 Bug。请在报告中附上您的程序和以下诊断信息。谢谢。
   com.sun.tools.javac.code.Symbol$CompletionFailure: 找不到java.lang.invoke.MethodType的类文件
   ```
   忘了在library添加 apply plugin: 'me.tatarka.retrolambda'

---
Q:Java比较字符串时间比较方便的方法

A:[查看](http://blog.csdn.net/exceptional_derek/article/details/10823845)

---
Q:如何调试混淆引入的问题

A:将debug 的混淆配置也设为true 进行调试

---
Q:CountDownLatch 简介

A:在一些应用场合中，某段程序需要等待某个条件达到要求后才能执行，或者等待一定长的时间后此行，从jdk1.5开始就可以使用CountDownLatch实现，
    CountDownLatch类是一个同步倒数计数器,构造时传入int参数,该参数就是计数器的初始值，每调用一次countDown()方法，计数器减1,
    计数器大于0 时，await()方法会阻塞后面程序执行，直到计数器为0，await(long timeout, TimeUnit unit)，是等待一定时间，然后执行，不管计数器是否到0了。
---
Q:用Windows AS下载gradle plugin 翻墙了也老是卡住

A:[查看](http://tikitoo.github.io/2016/05/26/android-studio-gradle-build-run-faster/)

---
Q:FastJson 解析json为Map的时候没按照顺序排列

A:解析为LinkedHashMap
