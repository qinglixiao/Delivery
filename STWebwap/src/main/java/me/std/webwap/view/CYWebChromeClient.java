package me.std.webwap.view;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.GeolocationPermissions;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

import me.std.common.core.ThreadPool;
import me.std.webwap.R;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/21.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class CYWebChromeClient extends WebChromeClient {
    private Activity activity;
    private WeakReference<Bitmap> mDefaultVideo;
    private WeakReference<View> mVideoProgressView;
    private WeakReference<View> mCustomView;
    private FullscreenHolder mFullscreenContainer;
    private CustomViewCallback mCustomViewCallback;
    private int mOriginalOrientation;
    private Delegate delegate;

    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
    }

    /**
     * custom view layout param.
     */
    protected static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS =
            new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

    private Runnable mResetOrentationAction = new Runnable() {

        @Override
        public void run() {
            activity.setRequestedOrientation(mOriginalOrientation);
        }
    };


    public CYWebChromeClient(Activity activity) {
        this.activity = activity;
    }

    @Override
    public Bitmap getDefaultVideoPoster() {
        if (mDefaultVideo != null && mDefaultVideo.get() != null) {
            return mDefaultVideo.get();
        } else {
            mDefaultVideo = new WeakReference<>(BitmapFactory.decodeResource(activity.getResources(),
                    R.drawable.default_video));
        }

        return mDefaultVideo.get();
    }

    @Override
    public View getVideoLoadingProgressView() {
        if (mVideoProgressView != null && mVideoProgressView.get() != null) {
            return mVideoProgressView.get();
        } else {
            mVideoProgressView = new WeakReference<>(activity.getLayoutInflater()
                    .inflate(R.layout.view_video_loading_progress, null));
        }

        return mVideoProgressView.get();
    }


    @Override
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        callback.invoke(origin, true, false);
    }

    // 解决视频播放不能横屏问题 BEGIN
    @Override
    public void onHideCustomView() {
        hideCustomView();
    }

    @Override
    public void onShowCustomView(@NonNull View view, @NonNull CustomViewCallback callback) {
        onShowCustomView(view, activity.getRequestedOrientation(), callback);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onShowCustomView(@NonNull View view, int requestedOrientation, @NonNull CustomViewCallback callback) {
        showCustomView(view, requestedOrientation, callback);
    }

    /**
     * 隐藏 custom view。
     */
    private void hideCustomView() {
        if (mCustomView == null) {
            return;
        }

        setFullscreen(activity, false);
        FrameLayout decor = (FrameLayout) activity.getWindow().getDecorView();
        decor.removeView(mFullscreenContainer);
        mFullscreenContainer = null;
        mCustomView = null;
        mCustomViewCallback.onCustomViewHidden();

        // 在小米2上全屏播放视频，如果改为横向播放，再退出时，立即setRequestedOrientation会导致WebViewCore崩溃。。。
        if (TextUtils.equals(Build.MODEL, "MI 2")) {
            final long delayMillis = 1000;
            ThreadPool.postDelay(mResetOrentationAction, delayMillis);
        } else {
            activity.setRequestedOrientation(mOriginalOrientation);
        }
    }

    /**
     * 显示 customview.
     * 把回调的 custom view 添加到 layout中。显示出来。
     *
     * @param view                 要显示的 customview。
     * @param requestedOrientation customview 申请的 orientation
     * @param callback             callback.
     */
    private void showCustomView(@NonNull View view, int requestedOrientation, @NonNull CustomViewCallback callback) {
        ThreadPool.removeCallbacks(mResetOrentationAction);

        // if a view already exists then immediately terminate the new one
        if (mCustomView != null && mCustomView.get() != null) {
            callback.onCustomViewHidden();
            return;
        }

        mOriginalOrientation = activity.getRequestedOrientation();
        FrameLayout decor = (FrameLayout) activity.getWindow().getDecorView();
        mFullscreenContainer = new FullscreenHolder(activity);
        mFullscreenContainer.addView(view, COVER_SCREEN_PARAMS);
        decor.addView(mFullscreenContainer, COVER_SCREEN_PARAMS);
        mCustomView = new WeakReference<>(view);
        setFullscreen(activity, true);
        mCustomViewCallback = callback;
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    }

    /**
     * 设置 activity是否全屏.
     *
     * @param activity Activity
     * @param enabled  是否全屏。
     */
    public void setFullscreen(@NonNull Activity activity, boolean enabled) {
    }

    /**
     * webview custom view full screen holder. as a container.
     */
    static class FullscreenHolder extends FrameLayout {

        /**
         * constructor.
         *
         * @param ctx Context
         */
        public FullscreenHolder(@NonNull Context ctx) {
            super(ctx);
            //noinspection deprecation
            setBackgroundColor(ctx.getResources().getColor(android.R.color.black));
        }

        @Override
        public boolean onTouchEvent(MotionEvent evt) {
            return true;
        }

    }


    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        if (delegate != null) {
            return delegate.onJsAlert(view, url, message, result);
        }
        return super.onJsAlert(view, url, message, result);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (delegate != null) {
            delegate.onReceivedTitle(view, title);
            return;
        }
        super.onReceivedTitle(view, title);
    }

    /**
     * 获取网页加载进度
     */
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        activity.getWindow().setFeatureInt(Window.FEATURE_PROGRESS,
                newProgress * 100);
        if (delegate != null) {
            delegate.onProgressChanged(view, newProgress);
        }
    }

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        if (delegate != null) {
            return delegate.onShowFileChooser(webView, filePathCallback, fileChooserParams);
        }
        return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
    }

    public interface Delegate {
        boolean onJsAlert(WebView view, String url, String message, JsResult result);

        void onReceivedTitle(WebView view, String title);

        void onProgressChanged(WebView view, int newProgress);

        boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams);
    }
}
