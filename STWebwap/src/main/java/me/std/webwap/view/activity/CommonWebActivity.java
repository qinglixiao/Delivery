package me.std.webwap.view.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

import org.json.JSONObject;

import java.io.File;

import me.std.base.base.STActivity;
import me.std.base.core.ActionBar;
import me.std.base.view.EmptyView;
import me.std.common.utils.AppContextUtil;
import me.std.common.utils.FileUtil;
import me.std.webwap.R;
import me.std.webwap.comm.H5Args;
import me.std.webwap.contract.CommonWebActivityContract;
import me.std.webwap.presenter.CommonWebPresenter;
import me.std.webwap.view.CYWebChromeClient;
import me.std.webwap.view.CYWebView;
import me.std.webwap.view.CYWebViewClient;

/**
 * Description: 需将webView放在独立进程
 * Author: lixiao
 * Create on: 2018/11/20.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class CommonWebActivity extends STActivity implements CYWebViewClient.Delegate, CYWebChromeClient.Delegate, CommonWebActivityContract.View {
    private CYWebView mWebView;
    private CYWebChromeClient mWebChromeClient;
    private CYWebViewClient mWebViewClient;
    private CommonWebActivityContract.Presenter presenter;
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private String mTitle = "";
    private boolean isAutoSetTitle = false;
    protected boolean mActionBarClose = false;//是否将返回按钮展示未关闭按钮
    private String mUrl = "";
    private boolean isLoadPageError = false;
    private EmptyView emptyView;
    private ViewStub vEmptyToggle;
    private boolean mBackClose = false;//点击返回按键关闭当前activity

    @Override
    protected void onActionBar(ActionBar.Builder navBuilder) {
        navBuilder.setTitle(mTitle)
                .setBackClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mBackClose) {
                            finish();
                            return;
                        }
                        onBackPressed();
                    }
                });
    }

    @Override
    public void parseIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            mTitle = TextUtils.isEmpty(intent.getStringExtra(H5Args.ARG_WEB_TITLE)) ? "" : intent.getStringExtra(H5Args.ARG_WEB_TITLE);
            isAutoSetTitle = intent.getBooleanExtra(H5Args.ARG_AUTO_SET_TITLE, false);
            mActionBarClose = intent.getBooleanExtra(H5Args.ARG_ACTION_BAR_CLOSE, false);
        }

    }

    public void setNavigateBar(String type) {
//        if ("green".equals(type)) {
//            actionBar.getHeader().setBackgroundColor(getResources().getColor(R.color.A1));
//            actionBar.setBackIcon(R.drawable.action_bar_back_normal);
//            actionBar.getTitle().setTextColor(getResources().getColor(R.color.A9));
//        } else if ("normal".equals(type)) {
//            actionBar.getHeader().setBackgroundColor(getResources().getColor(R.color.A7));
//            actionBar.setBackIcon(getResources().getDrawable(R.drawable.action_bar_back_normal));
//            actionBar.getTitle().setTextColor(getResources().getColor(R.color.A2));
//        }
    }

    /**
     * 刷新是否显示debugtool
     * <a href="https://git.chunyu.me/frontend/ourwork/blob/master/docs/h5_native/debug_tool.md">参考文档</a>
     */
    private void autoInjectDebugTool() {
//        if (H5ToolUtil.getInstance(this).isShowDebug()) {
//            try {
//                H5ToolUtil helper = H5ToolUtil.getInstance(getApplicationContext());
//                helper.injectJS(mWebView, helper.getInjectJS());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_web);
        presenter = new CommonWebPresenter(this);
        mWebView = findViewById(R.id.webview);
        vEmptyToggle = findViewById(R.id.v_empty_tag);
        init();
        presenter.init(getIntent());
    }

    private void init() {
//        mWebView.setHeaders(NetworkConfig.getInstance().getCustomHeader());
        mWebChromeClient = new CYWebChromeClient(this);
        mWebViewClient = new CYWebViewClient();
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.setWebChromeClient(mWebChromeClient);
        mWebViewClient.setDelegate(this);
        mWebChromeClient.setDelegate(this);
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition,
                                        String mimetype, long contentLength) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_uri = Uri.parse(url);
                intent.setData(content_uri);
                startActivity(intent);
            }
        });
        addWebViewAttribute(mWebView);
    }

    protected void addWebViewAttribute(CYWebView webView) {

    }

    @Override
    public WebView getWebView() {
        return mWebView;
    }

    public void loadUrl(String url) {
        presenter.autoInjectNativeJs(mWebView, url);
        mUrl = url;
        presenter.setCookie(url);
        mWebView.loadUrl(url);
    }

    @Override
    public void postUrl(String url, byte[] postData) {
        mUrl = url;
        mWebView.postUrl(url, postData);
    }

    @Override
    public void setTitle(String title) {
        if (TextUtils.isEmpty(mTitle)) {
            actionBar.setTitle(title);
        }
    }

    @Override
    public void showShareButton(boolean isShow) {
        actionBar.getRightButton1().setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setNavigateBarBackButtonState(boolean isShowBackIcon, boolean isShowCloseIcon) {
        if (isShowBackIcon) {
            actionBar.show(actionBar.FLAG_VISUAL_BACK);
        } else {
            actionBar.gone(actionBar.FLAG_VISUAL_BACK);
        }

        if (isShowCloseIcon) {
            actionBar.show(actionBar.FLAG_VISUAL_CLOSE);
        } else {
            actionBar.gone(actionBar.FLAG_VISUAL_CLOSE);
        }
    }

    @Override
    public void setRightButtonState(String btnText, Drawable btnIcon, boolean visible) {
        if (visible) {
            actionBar.show(actionBar.FLAG_VISUAL_RIGHT_BUTTON1);
            if (!TextUtils.isEmpty(btnText)) {
                actionBar.setRightButton1(btnText, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.clickRightButton();
                    }
                });
            }

        } else {
            actionBar.gone(actionBar.FLAG_VISUAL_RIGHT_BUTTON1);
        }

    }

    @Override
    public void setRightButtonState(String btnText, Drawable btnIcon, boolean visible, View.OnClickListener callBack) {
        if (visible) {
            actionBar.show(actionBar.FLAG_VISUAL_RIGHT_BUTTON1);
            if (!TextUtils.isEmpty(btnText)) {
                actionBar.setRightButton1(btnText, callBack);
            }

        } else {
            actionBar.gone(actionBar.FLAG_VISUAL_RIGHT_BUTTON1);
        }

    }


    @Override
    public void setPresenter(CommonWebActivityContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();

        //等待js执行完成
        try {
            Thread.sleep(200);
        } catch (Exception e) {

        }
        destroyWebView();
        android.os.Process.killProcess(Process.myPid());//杀死此进程，防止内存泄漏（webview已经放在独立进程）
        super.onDestroy();
    }

    public void destroyWebView() {
        if (mWebView != null) {
            // 如果先调用mWebView.destroy()方法，则会命中if (isDestroyed()) return;这一行代码。
            // 所以需要先onDetachedFromWindow()，再destory()
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }
            try {
                mWebView.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int resultCode, Intent data) {
        if (mUploadCallbackAboveL == null) {
            return;
        }

        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {

            } else {
                String dataString = data.getDataString();
                ClipData clipData = data.getClipData();

                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }

                if (dataString != null) {
                    results = new Uri[]{Uri.parse(dataString)};
                }
            }
        }
        mUploadCallbackAboveL.onReceiveValue(results);
        mUploadCallbackAboveL = null;
        return;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void uploadFile(int resultCode, Intent data) {
        if (null == mUploadMessage && null == mUploadCallbackAboveL) {
            return;
        }
        Uri result = data == null || resultCode != Activity.RESULT_OK ? null : data.getData();
        if (mUploadCallbackAboveL != null) {
            onActivityResultAboveL(resultCode, data);
        } else if (mUploadMessage != null) {
            if (result != null) {
                String path = FileUtil.getPath(this, result);
                if (!TextUtils.isEmpty(path)) {
                    result = Uri.fromFile(new File(path));
                }
            }
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
    }

    @Override
    public void showLoading(boolean isShow) {
        if (isShow) {
            actionBar.show(actionBar.FLAG_VISUAL_PROGRESS);
        } else {
            actionBar.gone(actionBar.FLAG_VISUAL_PROGRESS);
        }
    }

    @Override
    public void onBackPressed() {
        if (getWebView() != null) {
            try {
                JSONObject dict = new JSONObject();
                dict.put("type", "beforeBack");
                getWebView().loadUrl(
                        "javascript:_nativeInvoke(" + dict.toString() + ");"
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (isCanGoBack() && mWebView != null) {
            mWebView.goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void finish() {
        if (presenter != null) {
            presenter.setBackResult();
        }
        try {
            hideSoftInput();//退出网页时收起键盘
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.finish();
    }

    private void hideSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) AppContextUtil.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    public boolean isCanGoBack() {
        return (mWebView != null && mWebView.canGoBack());
    }

    //========================WebViewClient===========================
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        showLoading(true);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        showLoading(false);
        presenter.autoInjectNativeJs(view, mUrl);
        if (isLoadPageError) {
            mWebView.setVisibility(View.GONE);
        } else {
            showLoading(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mWebView.setVisibility(View.VISIBLE);
                }
            }, 300);
        }

        mWebView.getSettings().setBlockNetworkImage(false);
        autoInjectDebugTool();
        if (isAutoSetTitle) {
            setTitle(view.getTitle());
        }
        //返回和关闭按钮全由initBackTitile控制，每次加载完网页，都重新设置一下
        if (mActionBarClose) {
            //当只想展示一个关闭按钮时，只展示一个返回按钮(变成了关闭)，隐藏写死的关闭按钮
            setNavigateBarBackButtonState(true, false);
        } else {
            //如果不强制要求只展示一个关闭，且现在的网页可以返回，则同时展示2个按钮
            if (isCanGoBack()) {
                setNavigateBarBackButtonState(false, true);
            }
        }
    }


    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        isLoadPageError = true;
        mWebView.setVisibility(View.GONE);
        if (emptyView == null) {
            emptyView = (EmptyView) vEmptyToggle.inflate();
        }
//        emptyView.showError(getString(R.string.listview_load_data_failed_and_retry),
//                R.drawable.icon_load_error);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        boolean result = presenter.matcherUrl(view, url);
        if (!result) {
            presenter.setCookie(url);
        }
        return result;
    }
    //=========================End======================================


    //=========================WebChromeClient==========================
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return presenter.onJsAlert(view, url, message, result);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
//        setTitle(title);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        actionBar.setProgress(newProgress);
    }

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        mUploadCallbackAboveL = filePathCallback;
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");
        startActivityForResult(
                Intent.createChooser(i, "File Browser"),
                100);
        return true;
    }

    //===========================End====================================
}
