# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/zhoujunyou/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# 保留Serializable序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {    static final long serialVersionUID;    private static final java.io.ObjectStreamField[] serialPersistentFields;    !static !transient <fields>;    !private <fields>;    !private <methods>;    private void writeObject(java.io.ObjectOutputStream);    private void readObject(java.io.ObjectInputStream);    java.lang.Object writeReplace();    java.lang.Object readResolve();}

# RxJava RxAndroid
-dontwarn sun.misc.**
-dontnote rx.internal
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
# Retrofit
-dontnote retrofit2.adapter.rxjava.**
-dontwarn retrofit2.adapter.rxjava.**
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

-dontnote okhttp3.**


# Gson
-dontnote com.google.gson.**
-keep class com.google.gson.stream.** { *; }
#utilcode 混淆
-keep class com.blankj.utilcode.** { *; }
-keepclassmembers class com.blankj.utilcode.** { *; }
-dontwarn com.blankj.utilcode.**
-dontnote com.blankj.utilcode.**

-dontnote org.jivesoftware.**
-keep class org.jivesoftware.smack.** { *; }
-keep class org.jivesoftware.smackx.** { *; }

-dontnote com.afollestad.materialdialogs.**
-keep class com.afollestad.materialdialogs.** { *; }

-dontnote com.roughike.bottombar.**

-dontwarn okio.**
-dontnote okio.**

-dontnote org.apache.harmony.javax.security.auth.**
-keep class org.apache.harmony.javax.security.auth.** { *; }

### greenDAO 3
-dontwarn org.greenrobot.greendao.database.**
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties
-keep class com.zjy.baselib.data.source.local.AppDB { *; }
-keep @org.greenrobot.greendao.annotation.Entity class * {
     <fields>;}
#ARouter
-keep  class com.alibaba.android.arouter.routes.**{*;}
 -keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

 #bugly
 -dontwarn com.tencent.bugly.**
 -keep public class com.tencent.bugly.**{*;}
 
 #glide
 -keep public class * implements com.bumptech.glide.module.GlideModule
 -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
   **[] $VALUES;
   public *;
 }

  -dontwarn  org.greenrobot.greendao.**
  -dontwarn com.alibaba.fastjson.**
  -dontwarn com.alibaba.android.arouter.**


