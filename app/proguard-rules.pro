#-dontshrink
-optimizationpasses 2
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify

-keepattributes *Annotation*, Signature
#-keep @**annotation** class * {*;}

-dontwarn org.mortbay.**
-dontwarn org.slf4j.**
-dontwarn org.apache.log4j.**
-dontwarn org.apache.commons.logging.**
-dontwarn org.apache.commons.codec.binary.**
-dontwarn org.dom4j.**
-dontwarn javax.activation.**
-dontwarn javax.xml.**, javax.mail.**, java.awt.**, javax.swing**, java.beans
-dontwarn nu.xom.**
-dontwarn sun.security.**
-dontwarn org.xmlpull.**
-dontwarn org.jdom.**
-dontwarn org.codehaus.**
-dontwarn net.sf.**
-dontwarn android.content.**
-dontwarn org.joda.**
-dontwarn com.thoughtworks.**
-dontwarn com.ibm.**
-dontwarn android.support.v4.**
-dontwarn com.baidu.location.**
-dontwarn android.support.v7.app.**
-dontwarn java.nio.file.**

-dontwarn cn.sharesdk.**
-dontwarn com.flurry.**
-dontwarn uk.co.senab.photoview.**
-dontwarn android.net.http.**
-dontwarn com.mixpanel.**
-dontwarn com.baidu.location.**
-dontwarn java.nio.file.**
-dontwarn android.support.v7.app.**
-dontwarn com.squareup.picasso.**

-dontwarn com.alipay.apmobilesecuritysdk.face.**

-dontnote


-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*


-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep public class com.alipay.**
-keep public class com.weibo.net.**
-keep public class javax.**
-keep class com.baidu.mapapi.** {*;}

-keep public class com.squareup.okhttp.**
-keep public class okia.**
-keep class org.jdom.**
-keep class org.jdom.** {*;}
-keep class com.ibm.**
-keep class com.ibm.** {*;}
-keep class org.xmlpull.**
-keep class org.xmlpull.** {*;}
-keep class com.thoughtworks.xstream.**
-keep class com.thoughtworks.xstream.** {*;}
-keep class com.tct.hz.unionpay.plugin.**
-keep class com.tct.hz.unionpay.plugin.** {*;}
-keep class com.baidu.android.**{*;}
-keep class com.baidu.voicerecognition.android.**{*;}

-keep class com.ccit.** {*; }
-keep class ccit.** { *; }
-keep class mm.purchasesdk.**
-keep class mm.purchasesdk.** {*;}

-keep class android.support.v4.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment

-keep class  android.support.v7.view.** { *; }
-keep interface  android.support.v7.view.** { *; }

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-dontwarn com.tencent.**
-keep class com.tencent.**{*;}
-keep class * extends android.app.Dialog

-dontwarn com.igexin.**
-keep class com.igexin.** { *; }
-keep class org.json.** { *; }
-keep class com.meizu.** { *; }
-dontwarn com.meizu.**
-keep class org.apache.http.entity.mime.** { *; }
-keep class com.xiaomi.** { *; }
-dontwarn com.xiaomi.push.**
-keep class org.apache.thrift.** { *; }

-keep public class com.crittercism.**
-keepclassmembers public class com.crittercism.*{*;}

-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}

-keep public class com.crittercism.**
-keepclassmembers public class com.crittercism.* { *; }

-keep public class cn.sharesdk.**
-keepclassmembers public class cn.sharesdk.* { *; }

# 视频咨询插件
-keep class com.justalk.cloud.** { *; }
-keep interface com.justalk.cloud.** { *; }

-dontwarn com.amap.api.**
-keep class com.amap.api.** { *; }

# g7anno-core-starts
-dontwarn javax.**

-keepclassmembers class com.meichis.login.JavaScriptInterface {
  public *;
}

# 银联支付
-dontwarn com.unionpay.**
-keep public class com.unionpay.** {*;}
-keep public class org.simalliance.** {*;}

# ProGuard configurations for NetworkBench Lens 听云
#-keep class com.networkbench.** { *; }
#-dontwarn com.networkbench.**
#-keepattributes Exceptions, Signature, InnerClasses
#-keepattributes SourceFile,LineNumberTable
# End NetworkBench Lens


#高德相关混淆文件
# As described in tools/proguard/examples/android.pro - ignore all warnings.
#如果有其它包有warning，在报出warning的包加入下面类似的-dontwarn 报名
-dontwarn com.amap.api.**
-dontwarn com.autonavi.aps.**

#Location
-keep   class com.amap.api.location.**{*;}
-keep   class com.amap.api.fence.**{*;}
-keep   class com.autonavi.aps.amapapi.model.**{*;}
# End 高德混淆

-dontwarn okhttp3.**

# OkHttp
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

#热更新
-keepclassmembers @interface com.alipay.euler.andfix.annotation.** { *; }
-keep class * extends java.lang.annotation.Annotation
-keepclasseswithmembernames class * {
    native <methods>;
}

-keep class android.support.multidex.** { *; }
-keep class android.support.v7.** { *; }
-keep class com.github.mikephil.charting.** { *; }
-keep class com.google.zxing.** { *; }
-keep class com.sina.weibo.sdk.auth.** { *; }
-keep class ly.count.android.sdk.** { *; }
-keep class com.squareup.okhttp.** { *; }
-keep class okio.** { *; }
-keep class com.flurry.sdk.** { *; }
-keep class com.loopj.android.http.** { *; }
-keep class com.loc.** { *; }

#-applymapping mapping.txt
#-ignorewarnings

#-useuniqueclassmembernames


-dontwarn com.sun.source.**
-dontwarn com.sun.tools.**

# BroadcastEx
-dontwarn freemarker.**
-dontwarn com.sun.tools.javac.**
-dontwarn javax.**

-keep class me.skyun.broadcastex.api.Action
-keep class me.skyun.broadcastex.api.ReceiverRegistrar
-keep class me.skyun.broadcastex.api.BroadcastExReceiver
-keep @me.skyun.broadcastex.api.Action class *
-keep class * extends me.skyun.broadcastex.api.ReceiverRegistrar
-keepclassmembers class * {
    @me.skyun.broadcastex.api.BroadcastExReceiver <methods>;
}

-dontwarn com.google.zxing.**
-keep public class com.google.zxing.**

-dontwarn com.journeyapps.barcodescanner.**
-keep public class com.journeyapps.barcodescanner.**

-dontwarn com.alipay.euler.**
-keep public class com.alipay.euler.**


-dontwarn com.qiyukf.**
-keep class com.qiyukf.** {*;}

-keepclassmembers class * extends android.webkit.WebChromeClient{
   public void openFileChooser(...);
}

-keep class com.huawei.hms.** { *; }
-dontwarn com.huawei.hms.**

-keep class android.net.** { *; }
-keep interface android.net.** { *; }
-dontwarn android.net.**

-keep class com.android.internal.http.multipart.** { *; }
-keep interface com.android.internal.http.multipart.** { *; }
-dontwarn com.android.internal.http.multipart.**

-keep class org.apache.commons.** { *; }
-keep interface org.apache.commons.** { *; }
-dontwarn org.apache.commons.**

-keep class org.apache.http.** { *; }
-keep interface org.apache.http.** { *; }
-dontwarn org.apache.http.**


-keep class android.support.design.internal.** { *; }
-keep interface android.support.design.internal.** { *; }
-dontwarn android.support.design.internal.**

-keep class android.support.design.widget.** { *; }
-keep interface android.support.design.widget.** { *; }
-dontwarn android.support.design.widget.**

-keep class com.qq.e.** {public protected *;}
-keep class android.support.v4.app.NotificationCompat** {public *;}
-keep class com.baidu.** {public protected *;}

-keep public class pl.droidsonroids.gif.GifIOException{<init>(int, java.lang.String);}
-dontwarn pl.droidsonroids.gif.**

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.GeneratedAppGlideModuleImpl
-dontwarn com.google.auto.service.processor.**
-dontwarn com.google.common.**
-dontwarn java.util.regex.**
-dontwarn java.io.**
-dontwarn android.support.v7.content.res.**

-dontwarn com.getui.**
-keep class com.getui.**{*;}

#支付宝
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}
-keep class com.alipay.sdk.app.H5PayCallback {
    <fields>;
    <methods>;
}
-keep class com.alipay.android.phone.mrpc.core.** { *; }
-keep class com.alipay.apmobilesecuritysdk.** { *; }
-keep class com.alipay.mobile.framework.service.annotation.** { *; }
-keep class com.alipay.mobilesecuritysdk.face.** { *; }
-keep class com.alipay.tscenter.biz.rpc.** { *; }
-keep class org.json.alipay.** { *; }
-keep class com.alipay.tscenter.** { *; }
-keep class com.ta.utdid2.** { *;}
-keep class com.ut.device.** { *;}
-keep class com.coloros.mcssdk.** { *; }
-dontwarn com.coloros.mcssdk.**

-keep class com.huawei.android.** { *; }
-dontwarn com.huawei.android.**
-keep class com.hianalytics.android.** { *; }
-dontwarn com.hianalytics.android.**
-keep class com.huawei.updatesdk.** { *; }
-dontwarn com.huawei.updatesdk.**
#-ignorewarning
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable

-keep class io.flutter.**{*;}
-keep class me.chunyu.flutter.**{*;}
-keep class com.google.gson.**{*;}
-keep class de.greenrobot.**{*;}
-keep class io.reactivex.**{*;}
-keep class org.simple.eventbus.**{*;}
-keep class com.squareup.okhttp3.**{*;}
-keep class okhttp3.**{*;}

-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties { *; }

# If you DO use SQLCipher:
-keep class org.greenrobot.greendao.database.SqlCipherEncryptedHelper { *; }

# If you do NOT use SQLCipher:
-dontwarn net.sqlcipher.database.**
# If you do NOT use RxJava:
-dontwarn rx.**





