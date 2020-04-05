package me.std.webwap.view;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/21.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class CYWebViewClient extends WebViewClient {
    private Delegate delegate;

    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (delegate != null) {
            delegate.onPageStarted(view, url, favicon);
            return;
        }
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (delegate != null) {
            delegate.onPageFinished(view, url);
            return;
        }
        super.onPageFinished(view, url);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        if (delegate != null) {
            delegate.onReceivedError(view, request, error);
            return;
        }
        super.onReceivedError(view, request, error);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        if (delegate != null) {
            delegate.onReceivedSslError(view, handler, error);
            return;
        }
        super.onReceivedSslError(view, handler, error);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (delegate != null) {
            return delegate.shouldOverrideUrlLoading(view, url);
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    public interface Delegate {
        void onPageStarted(WebView view, String url, Bitmap favicon);

        void onPageFinished(WebView view, String url);

        void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error);

        void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error);

        boolean shouldOverrideUrlLoading(WebView view, String url);
    }

}
