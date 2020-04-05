package me.std.webwap.contract;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.webkit.JsResult;
import android.webkit.WebView;

import me.std.base.contract.IBasePresenter;
import me.std.base.contract.IBaseView;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/21.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public interface CommonWebActivityContract {
    interface View extends IBaseView<Presenter> {
        WebView getWebView();

        void loadUrl(String url);

        void postUrl(String url, byte[] postData);

        void finish();

        void setTitle(String title);

        void showShareButton(boolean isShow);

        void setNavigateBar(String type);

        void setNavigateBarBackButtonState(boolean isShowBackIcon, boolean isShowCloseIcon);

        void setRightButtonState(String btnText, Drawable btnIcon, boolean visible);

        void setRightButtonState(String btnText, Drawable btnIcon, boolean visible, android.view.View.OnClickListener callBack);

    }

    interface Presenter extends IBasePresenter<View> {
        void init(Intent intent);

        void setCookie(String url);

        boolean matcherUrl(WebView webView, String url);

        void autoInjectNativeJs(WebView webView, String url);

        void execJs(String js);

        boolean onJsAlert(WebView view, String url, String message, JsResult result);

        void share(boolean ignoreBtnVisible, boolean forceDoShare);

        void setBackResult();

        void clickRightButton();
    }
}
