package me.std.webwap.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/21.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class CYWebView extends WebView {
    private Map<String, String> headers;

    public CYWebView(Context context) {
        super(context);
        setAttribute();
    }

    public CYWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttribute();
    }

    public CYWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttribute();
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public void loadUrl(String url) {
        if (headers != null && headers.size() > 0) {
            loadUrl(url, headers);
            return;
        }
        super.loadUrl(url);
    }

    private void setAttribute() {
        WebSettings settings = getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);//将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true); //缩放至屏幕的大小
        settings.setSaveFormData(true);
        settings.setJavaScriptEnabled(true);//设置允许与js交互
        settings.setSupportZoom(false);//是否支持缩放，这里禁止了缩放
        settings.setDisplayZoomControls(false);//隐藏原生的缩放控件
        settings.setDomStorageEnabled(true);// 开启 DOM storage API 功能
        String userAgent = settings.getUserAgentString();
//        settings.setUserAgentString(userAgent + " " + NetworkConfig.getUserAgent());
        settings.setAllowFileAccess(false);//禁止js访问本地文件
        settings.setAllowFileAccessFromFileURLs(false);//禁止通过file url加载的js代码，去读取其他的本地文件
        settings.setAllowUniversalAccessFromFileURLs(false);//// 禁止通过file url加载的js可以访问其他的源(包括http、https等源)

        settings.setBlockNetworkImage(true);
        settings.setBlockNetworkLoads(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //允许https和http混用
//            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }
}
