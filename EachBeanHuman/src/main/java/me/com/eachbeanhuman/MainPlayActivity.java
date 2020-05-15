package me.com.eachbeanhuman;

import me.std.webwap.view.CYWebView;
import me.std.webwap.view.activity.CommonWebActivity;

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/4/7.
 */
public class MainPlayActivity extends CommonWebActivity {
    @Override
    protected void addWebViewAttribute(CYWebView webView) {
        super.addWebViewAttribute(webView);
        webView.getSettings().setDatabaseEnabled(true);
    }

    @Override
    public void loadUrl(String url) {
        super.loadUrl("file:///android_asset/index_me.html");
    }
}
