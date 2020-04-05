package me.std.webwap.presenter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import com.std.framework.router.CYRouter;
import com.std.framework.router.interfaces.Capture;
import com.std.network.NetworkConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import me.std.common.core.ThreadPool;
import me.std.webwap.comm.AndroidJs;
import me.std.webwap.comm.H5Args;
import me.std.webwap.comm.ShareJs;
import me.std.webwap.contract.CommonWebActivityContract;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/21.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class CommonWebPresenter implements CommonWebActivityContract.Presenter {
    public static final String JS_INTERFACE_NATIVE = "ChunyuNativeBridge";

    private CommonWebActivityContract.View view;
//    private CommonWebActivityContract.Model model;
    protected AndroidJs mAndroidJs;
//    protected BackJsData mBackJsData = new BackJsData();
    protected List<String> mJsShareCallBacks;
    protected String actionbarType = "normal";//通过js来设置actionbar的类型，目前支持显示为绿色，和常规颜色

    protected boolean mZoomControls = false;

    /**
     * 设置页面是否可以退回之前访问的页面
     */
    protected boolean mCanGoBack = true;
    protected boolean mActionBarClose = false;//是否将返回按钮展示未关闭按钮

    protected String mUrl = "";

    protected String mTitle = "";

    protected boolean mBlockImage = true;

    protected String mShareKey;

    protected boolean isAutoSetTitle = false;

    protected boolean shouldSendCountlyEventWhenShare = false;

    protected String shareCountlyEventTag = "";

    protected boolean mForbidTempPage;

    protected boolean mIsShowCircleLoading;//是否展示页面内圆形loading而不是顶部进度条

//    protected NavParam mNavParam;
    /**
     * post 数据， 770 add for 对接叮当快药
     */
    protected String mPostData;

    protected ShareJs.ShareContent mShareContent;

    protected boolean mShareLocalFirst; //是否优先展示本地传来的shareContent

    protected boolean mShowShareBtn = false; // 分享按钮

    protected boolean mIsShowAd; //是否展示底部独立广告位
    protected String mAdType; //独立广告类型
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private CompositeDisposable mCompositeDisposable;

    public CommonWebPresenter(CommonWebActivityContract.View view) {
        this.view = view;
        mCompositeDisposable = new CompositeDisposable();
//        model = new CommonWebModel(view.getContext());
        addReceiver();
    }

    public void init(Intent intent) {
        if (intent != null) {
            mUrl = intent.getStringExtra(H5Args.ARG_WEB_URL);
            mPostData = intent.getStringExtra(H5Args.ARG_SHARE_CONTENT);
            mShareKey = intent.getStringExtra(H5Args.ARG_WAP_SHARE_KEY);
        }

        mUrl = addHostUrl(urlWrap(mUrl));
        initShare();
        mAndroidJs = new AndroidJs((Activity) view.getContext(), view.getWebView());
        mAndroidJs.getShareJs().setListener(getShareJsListener());
        mAndroidJs.setOnAndroidJs(getOnAndroidJs());
        if (TextUtils.isEmpty(mPostData)) {
            view.loadUrl(mUrl);
        } else {
            view.postUrl(mUrl, mPostData.getBytes());
        }
        if (!TextUtils.isEmpty(mShareKey)) {
            loadShareInfo(mShareKey);
        }

    }

    private void initShare() {
        if (mShareContent == null) {
            mShareContent = new ShareJs.ShareContent();
            mShareContent.init(mTitle, mUrl, "分享");
        } else {
            mShowShareBtn = true;
        }
    }

    protected AndroidJs.onAndroidJs getOnAndroidJs() {
        return new AndroidJs.onAndroidJs() {
//            @Override
//            public void onSetBackBtn(BackJsData backJsData) {
//                if (backJsData == null) {
//                    return;
//                }
//                mBackJsData = backJsData;
//                //关闭web页的功能和设置actionbar按钮合在了一起，先判断是否要关闭网页，如果是的话，就直接关了
//                if (mBackJsData.mDoClose) {
//                    view.finish();
//                }
//                ThreadPool.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (BackJsData.TYPE_CLOSE.equals(mBackJsData.mType)) {
//                            mActionBarClose = true;
//                            //如果h5希望只展示一个关闭按钮，则将返回变为关闭，隐藏关闭按钮
////                            initBackTitile(true, false);
//                            if (view != null) {
//                                view.setNavigateBarBackButtonState(true, false);
//                            }
//                        } else {
//                            mActionBarClose = false;
//                            //如果h5希望展示返回按钮，则同时展示返回和关闭
////                            initBackTitile(false, true);
//                            if (view != null) {
//                                view.setNavigateBarBackButtonState(false, true);
//                            }
//                        }
//                    }
//                });
//            }

            @Override
            public void onSetTitle(final String title) {
                ThreadPool.post(new Runnable() {
                    @Override
                    public void run() {
                        view.setTitle(title);
                    }
                });
            }

            @Override
            public void onSetActionBar(String jsonStr) {

                final String type;
                try {
                    type = new JSONObject(jsonStr).optString("type");
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
                if (actionbarType.equals(type)) {
                    return;
                }
                actionbarType = type;
                ThreadPool.post(new Runnable() {
                    @Override
                    public void run() {
                        if (view != null) {
                            view.setNavigateBar(type);
                        }
                    }
                });
            }

            @Override
            public void onClearCache() {
                view.getWebView().clearCache(true);
            }

            @Override
            public void execJSStr(String jsStr) {
                execJs(jsStr);
            }
        };
    }

    public void execJs(String js) {
        if (view.getWebView() != null && !TextUtils.isEmpty(js)) {
            view.getWebView().loadUrl("javascript:" + js);
        }
    }

    private ShareJs.ShareBtnListener getShareJsListener() {
        if (mShareLocalFirst && mShareContent != null) {
            return null;
        }
        return new ShareJs.ShareBtnListener() {
            @Override
            public void setShareBtn(boolean isShowShareBtn) {
                if (view != null) {
                    view.showShareButton(isShowShareBtn);
                }
            }

            @Override
            public void setShareContent(ShareJs.ShareContent shareContent, List<String> callbacks) {
                mShareContent = shareContent;
                mJsShareCallBacks = callbacks;
            }
        };
    }

    /**
     * 让本地支持js的警告窗
     */
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        try {
            if (TextUtils.isEmpty(message)) {
                return false;
            }
            JSONObject jsonObject = new JSONObject(message);
            String type = jsonObject.optString("type");
            if (TextUtils.isEmpty(type)) {
                return false;
            }
            if (type.equals("chunyu_share")) {
//                mShareContent.title = jsonObject.optString("title");
//                mShareContent.desc = jsonObject.optString("content");
//                mShareContent.img = jsonObject.optString("image");
//                mShareContent.link = jsonObject.optString("url");
//                mAndroidJs.getShareJs().share(mShareContent, null, true, true);
            } else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        } finally {
            // 这句必须加，不然后来webview没法点击了
            result.cancel();
        }
        return true;
    }

    @Override
    public void share(boolean ignoreBtnVisible, boolean forceDoShare) {
        if (mAndroidJs != null) {
//            mAndroidJs.getShareJs().share(mShareContent, mJsShareCallBacks, ignoreBtnVisible, forceDoShare);
        }
    }

    @Override
    public void setBackResult() {
//        if (!TextUtils.isEmpty(mBackJsData.mExecJs)) {
//        Intent intent = new Intent();
//        intent.putExtra(BackJsData.KEY_BACK_EXEC_JS, mBackJsData.mExecJs);
//        intent.putExtra(Constant.KEY_H5_RETURN_DATA, mBackJsData.data);
//        ((Activity) view.getContext()).setResult(Activity.RESULT_OK, intent);
//        }
    }

    @Override
    public void clickRightButton() {

    }

    protected void setShareBtn(final boolean isShowShareBtn) {
        ThreadPool.post(new Runnable() {
            @Override
            public void run() {
                if (view != null) {
                    view.showShareButton(isShowShareBtn);
                }
            }
        });
    }

    public String urlWrap(String url) {
        return url;
    }

    private String addHostUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            url = url.trim();
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = NetworkConfig.getDomain() + mUrl;
            }
            url = appendDeviceInfoToUrl(url);
        }

        return url;
    }

    public void loadShareInfo(String shareKey) {
//        model.loadShareInfo(shareKey, new WebOperation.WebOperationCallback() {
//
//            @Override
//            public void operationExecutedSuccess(WebOperation operation, WebOperation.WebOperationRequestResult result) {
//                if (result == null || result.getData() == null) {
//                    operationExecutedFailed(operation, null);
//                    return;
//                }
//                //如果ShareContent是从JS获取的,则忽略服务器获取的ShareContent
//                if (mShareContent == null ||
//                        !ShareJs.ShareContent.FROM_JS.equals(mShareContent.from)) {
//                    mShareContent = (ShareJs.ShareContent) result.getData();
//                }
//            }
//
//            @Override
//            public void operationExecutedFailed(WebOperation operation, Exception exception) {
//
//            }
//        });
    }

    /**
     * 增加处理#
     */
    protected String appendDeviceInfoToUrl(@NonNull String url) {
        if (!NetworkConfig.getInstance().isForbidHost(url)) {
            return url;
        }

        StringBuilder urlBuilder;
        if (url.contains("#")) {
            urlBuilder = new StringBuilder(url.substring(0, url.indexOf("#")));
        } else {
            urlBuilder = new StringBuilder(url);
        }

        urlBuilder.append(url.contains("?") ? "&" : "?");
        urlBuilder.append(NetworkConfig.getUserAgent());

        if (url.contains("#")) {
            urlBuilder.append(url.substring(url.indexOf("#")));
        }
        return urlBuilder.toString();
    }


    @Override
    public void setCookie(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        String host = Uri.parse(url).getHost();
        if (NetworkConfig.getInstance().isForbidHost(url)) {
            return;
        }

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        String cookieOnline = cookieManager.getCookie(NetworkConfig.getInstance().getOnLineHost());
        String cookieUrl = cookieManager.getCookie(url);

        //以前是全拿出来拼在一起去保存，现在不拼接到一起，挨个去设置cookie，解决偶发的cookie丢失的问题
        if (!TextUtils.isEmpty(cookieOnline)) {
            String[] cookies = cookieOnline.split(";");
            for (String cookie : cookies) {
                cookieManager.setCookie(host, cookie);
            }
        }

        if (!TextUtils.isEmpty(cookieUrl)) {
            String[] cookies = cookieUrl.split(";");
            for (String cookie : cookies) {
                cookieManager.setCookie(host, cookie);
            }
        }

        CookieSyncManager instance = CookieSyncManager.getInstance();
        if (instance != null) {
            instance.sync();
        }
    }

    @Override
    public boolean matcherUrl(WebView webView, String url) {
        autoInjectNativeJs(webView, url);
        return onHandleBusiness(url);
    }

    /**
     * 将url交给相应业务模块进行处理，处理完后，各个业务再将处理结果返回页面做进一步处理
     *
     * @param url
     * @return
     */
    private boolean onHandleBusiness(String url) {
//        if (TextUtils.isEmpty(url)) {
//            return false;
//        }
//        if (url.contains("paas/add/friends/")) {//添加好友
//            RxBus.getDefault().post(new AddFriendMsg(url));
//            return true;
//        }
//        if (url.contains("paas/send/message")) {//QA页
//            openQAMessage(url);
//            return true;
//        }

        return false;
    }

    private void openQAMessage(String url) {
        Uri uri = Uri.parse(url);
        String conversationId = uri.getQueryParameter("convid");
        Map param = new HashMap();
        param.put("activity", view.getContext());
        param.put("conversationId", conversationId);
        CYRouter.build("chunyu://MessageProvider/chat/infinity", param)
                .done(new Capture() {
                    @Override
                    public void exception(Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    /**
     * 接收业务模块的回调
     */
    private void addReceiver() {
//        //接收添加好友处理回调
//        mCompositeDisposable.add(RxBus.getDefault().toObservable(AddFriendResultMsg.class)
//                .subscribe(new Consumer<AddFriendResultMsg>() {
//                    @Override
//                    public void accept(AddFriendResultMsg addFriendResultMsg) throws Exception {
//                        view.finish();
//                    }
//                }));
//        mCompositeDisposable.add(RxBus.getDefault().toObservable(SendArticleFromQAMsg.class)
//                .subscribe(new Consumer<SendArticleFromQAMsg>() {
//                    @Override
//                    public void accept(SendArticleFromQAMsg sendArticleFromQAMsg) throws Exception {
//                        try {
//                            String doctorId = Uri.parse(mUrl).getQueryParameter("doctorId");
//                            sendMessage(sendArticleFromQAMsg.content.toString(), Args.MESSAGE_DATA_TYPE_DATA, doctorId);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }));
    }


    public boolean sendMessage(String info, String type, String destId) {
//        WebOperation.WebOperationCallback<CommonResult> callback = new WebOperation.WebOperationCallback<CommonResult>() {
//            @Override
//            public void operationExecutedSuccess(WebOperation operation, WebOperation.WebOperationRequestResult<CommonResult> result) {
//                if (result == null || result.getData() == null) {
//                    ToastUtil.getInstance().showToast("发送失败");
//
//                    return;
//                }
//
//                if (Constant.REQUEST_SUCCESS == result.getData().getErrorCode()) {
//                    ToastUtil.getInstance().showToast("发送成功");
//                    ThreadPool.postDelay(new Runnable() {
//                        @Override
//                        public void run() {
//                            RxBus.getDefault().post(new SendArticleResultMsg("发送成功"));
//                        }
//                    }, 1000);
//                    view.finish();
//                } else {
//                    ToastUtil.getInstance().showToast(result.getData().getErrorMessage());
//                }
//
//            }
//
//            @Override
//            public void operationExecutedFailed(WebOperation operation, Exception exception) {
//                ToastUtil.getInstance().showToast("发送失败");
//            }
//        };
//
//
//        WebOperation webOperation = new WebPostOperation(callback) {
//
//            @Override
//            public String buildUrlQuery() {
//                return "/paas/representative/data/send/";
//            }
//
//            @Override
//            protected String[] getPostData() {
//                return new String[]{
//                        "doctor_id", destId,
//                        "data_info", info,
//                        "data_type", type
//                };
//            }
//
//            @Override
//            protected WebOperationRequestResult parseResponseString(Context context, String string) {
//
//                CommonResult commonResult = new CommonResult();
//                commonResult.fromJSONString(string);
//
//                return new WebOperation.WebOperationRequestResult(commonResult);
//            }
//
//        };
//
//        new WebOperationScheduler(AppContextUtil.getAppContext()).sendOperation(webOperation);


        return true;
    }


    public void autoInjectNativeJs(WebView webView, String url) {
        if (!NetworkConfig.getInstance().isForbidHost(url)) {
            webView.addJavascriptInterface(mAndroidJs, JS_INTERFACE_NATIVE);
        } else {
            webView.removeJavascriptInterface(JS_INTERFACE_NATIVE);
        }
    }

    @Override
    public void onDestroy() {
        if (view.getWebView() != null) {
            try {
                JSONObject dict = new JSONObject();
                dict.put("type", "beforeClose");
                view.getWebView().loadUrl(
                        "javascript:_nativeInvoke(" + dict.toString() + ");"
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable.dispose();
            mCompositeDisposable = null;
        }
    }
}
