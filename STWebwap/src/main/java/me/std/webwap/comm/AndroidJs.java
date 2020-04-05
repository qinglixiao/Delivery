package me.std.webwap.comm;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import me.std.common.core.DataConvert;
import me.std.common.core.RxBus;
import me.std.common.core.ThreadPool;
import me.std.common.utils.AppContextUtil;
import me.std.webwap.contract.CommonWebActivityContract;
import me.std.webwap.mutual.CommonWebAssistant;


/**
 * H5调用本地代码, Js注入
 * <a href="https://git.chunyu.me/frontend/ourwork/blob/master/docs/medweb_api/native_bridge.md">详见说明文档</a>
 *
 * @author michael created on 15/8/28.
 */
public class AndroidJs {

    private static final String VERSION_JS = "1.0";
    private Activity mActivity;
    private WebView mWebView;
    private ShareJs mShareJs;

    private onAndroidJs mOnAndroidJs;
    // H5的事件存储列表
    private static HashMap<String, List<String>> mEventMap = new HashMap<>();

    private static final HashMap<String, String> mMethodStartVersion =
            new HashMap<String, String>();

    private static final String METHOD_SHARE = "share";//分享
    private static final String METHOD_SET_TITLE = "setTitle";//设置网页的标题
    private static final String METHOD_RECORD_EVENT = "recordEvent";//打点，countly
    private static final String METHOD_QR_SCAN = "qrScan";//二维码扫描，debug包，用于H5 debugtool
    /**
     * 设置返回键的功能
     * 【注意！！】关闭网页的功能也在这里，需要把do_close参数设为true
     */
    private static final String METHOD_SET_BACK_BTN = "setBackBtn";
    private static final String METHOD_CLEAR_CACHE = "clearCache";//清楚webview的缓存
    private static final String METHOD_UPLOAD_FILE = "upload";//H5调用上传文件的接口上传文件
    private static final String METHOD_SPEECH_TO_TEXT = "speechToText";//调用百度语音识别，将语音信息转换成文本
    private static final String METHOD_SET_COIN = "setCoin";// js加金币
    private static final String METHOD_CALL = "call";// 此为通用JS调用原生的方法，通常应用于特定的业务场景。
    private static final String METHOD_OPEN_VIEW = "openView"; //通过intent协议调用指定页面
    public static final String METHOD_OPEN_NATIVE = "openNative"; //通过传递参数形式打开指定页面
    public static final String METHOD_OPEN_WEBVIEW = "openWebView"; // 打开一个新的web容器
    //8512直接分享微信好友或者朋友圈
    public static final String METHOD_WECHAT_SHARE = "weixin_share";

    private static final String EVENT_SHARE_DONE = "shareDone";//分享完成时的事件回调
    private static final String EVENT_SPEECH_TO_TEXT_DONE = "speechToTextDone";//语音识别完成时的事件回调
    private static final String EVENT_UPLOAD_FILE_DONE = "uploadDone";//文件上传完成时的事件回调

    public static final String JS_CALLBACK_GET_STEPS = "get_steps";
    public static final String JS_CALLBACK_IS_LOGIN = "is_login"; //判断是否登录

    public static final String METHOD_SET_ACTIONBAR = "setActionBarType"; //判断是否自定义修改actionbar
    public static final String METHOD_SEND_MATERIAL = "send_material";//资料库发送资料
    public static final String METHOD_SELECT_ACTION = "select_action";//选择h5列表中的某一项，并且返回到原来界面


    private Map sendMaterialMap;


    static {
        mMethodStartVersion.put(METHOD_SHARE, VERSION_JS);
        mMethodStartVersion.put(METHOD_SET_TITLE, VERSION_JS);
        mMethodStartVersion.put(METHOD_RECORD_EVENT, VERSION_JS);
        mMethodStartVersion.put(METHOD_QR_SCAN, VERSION_JS);
        mMethodStartVersion.put(METHOD_SET_BACK_BTN, VERSION_JS);
        mMethodStartVersion.put(METHOD_CLEAR_CACHE, VERSION_JS);
        mMethodStartVersion.put(METHOD_UPLOAD_FILE, VERSION_JS);
        mMethodStartVersion.put(METHOD_SPEECH_TO_TEXT, VERSION_JS);
        mMethodStartVersion.put(METHOD_WECHAT_SHARE, VERSION_JS);

        mMethodStartVersion.put(EVENT_SHARE_DONE, VERSION_JS);
        mMethodStartVersion.put(EVENT_SPEECH_TO_TEXT_DONE, VERSION_JS);
        mMethodStartVersion.put(EVENT_UPLOAD_FILE_DONE, VERSION_JS);

        mMethodStartVersion.put(METHOD_SET_COIN, VERSION_JS);
        mMethodStartVersion.put(METHOD_CALL, VERSION_JS);
        mMethodStartVersion.put(METHOD_OPEN_VIEW, VERSION_JS);
        mMethodStartVersion.put(METHOD_OPEN_NATIVE, VERSION_JS);
        mMethodStartVersion.put(METHOD_SET_ACTIONBAR, VERSION_JS);
        mMethodStartVersion.put(METHOD_OPEN_WEBVIEW, VERSION_JS);
        mMethodStartVersion.put(METHOD_SEND_MATERIAL, VERSION_JS);
        mMethodStartVersion.put(METHOD_SELECT_ACTION, VERSION_JS);
    }

    public AndroidJs(Activity activity, WebView webView) {
        mActivity = activity;
        mWebView = webView;
    }

    public ShareJs getShareJs() {
        if (mShareJs == null) {
            mShareJs = new ShareJs((FragmentActivity) mActivity, mWebView);
        }
        return mShareJs;
    }

    public void setShareJs(ShareJs shareJs) {
        mShareJs = shareJs;
    }

    public void setOnAndroidJs(onAndroidJs onAndroidJs) {
        mOnAndroidJs = onAndroidJs;
    }

    /**
     * 检验目前版本是否有要调用的方法
     *
     * @param version 当前版本号
     * @param method  要检查的方法
     */
    @JavascriptInterface
    public boolean has(String version, String method) {
        try {
            String remoteComponents[] = version.split("\\.");
            String currentComponents[] = mMethodStartVersion.get(method).split("\\.");

            int size = Math.min(remoteComponents.length, currentComponents.length);

            for (int i = 0; i < size; i++) {
                int remote = Integer.parseInt(remoteComponents[i]);
                int current = Integer.parseInt(currentComponents[i]);
                if (remote > current) {
                    return true;
                } else if (remote < current) {
                    return false;
                }
            }
            return remoteComponents.length >= currentComponents.length;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 调用本地方法的入口 <br />
     * JS调用时，可能会缺省callBack，缺省时调用该方法
     *
     * @param version 接口版本
     * @param method  接口名
     */
    @JavascriptInterface
    public boolean invoke(String version, String method) {
        return invoke(version, method, null);
    }

    /**
     * 调用本地方法的入口 <br />
     * JS调用时，可能会缺省callBack，缺省时调用该方法
     *
     * @param version 接口版本
     * @param method  接口名
     * @param jsonStr 接口参数-JSON格式
     */
    @JavascriptInterface
    public boolean invoke(String version, String method, String jsonStr) {
        try {
            if (!has(version, method)) {
                return false;
            }
            if (TextUtils.equals(method, METHOD_SHARE)) {
                ShareJs.ShareContent shareContent = DataConvert.fromJson(jsonStr, ShareJs.ShareContent.class);
                shareContent.from = ShareJs.ShareContent.FROM_JS;
                ShareJs.formatParams(shareContent);
                mShareJs.setShareDone(new ShareJs.ShareDone() {
                    @Override
                    public void onShareDone() {
//                        removeEvent(EVENT_SHARE_DONE);
                    }
                });
                mShareJs.share(shareContent, mEventMap.get(EVENT_SHARE_DONE), false, false);
            } else if (TextUtils.equals(method, METHOD_SET_TITLE)) {
                if (mOnAndroidJs != null) {
                    mOnAndroidJs.onSetTitle(jsonStr);
                }
            } else if (TextUtils.equals(method, METHOD_SET_ACTIONBAR)) {
                if (mOnAndroidJs != null) {
                    mOnAndroidJs.onSetActionBar(jsonStr);
                }
            } else if (TextUtils.equals(method, METHOD_RECORD_EVENT)) {
//                CoutlyEvent event = (CoutlyEvent) new CoutlyEvent().fromJSONString(jsonStr);
//                CountlyUtil.getInstance(mActivity).addEvent(event.mEvent, event.mSegment, event.mCount, event.mSum);
            } else if (TextUtils.equals(method, METHOD_QR_SCAN)) {
//                H5ToolUtil.getInstance(mActivity).doQrScan(mActivity, jsonStr);
            } else if (TextUtils.equals(method, METHOD_SET_BACK_BTN)) {
//                if (mOnAndroidJs != null) {
//                    try {
//                        BackJsData backJsData = new BackJsData();
//                        JSONObject jsonObject = new JSONObject(jsonStr);
//                        backJsData.mDoClose = jsonObject.optBoolean("do_close");
//                        backJsData.mExecJs = jsonObject.optString("exec_js");
//                        backJsData.mType = jsonObject.optString("type");
//                        backJsData.data = jsonObject.optJSONObject("data").toString();
//                        mOnAndroidJs.onSetBackBtn(backJsData);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
            } else if (METHOD_CLEAR_CACHE.equals(method)) {
                if (mOnAndroidJs != null) {
                    mOnAndroidJs.onClearCache();
                }
            } else if (METHOD_UPLOAD_FILE.equals(method)) {
                //todo 下一版本再上

            } else if (METHOD_SPEECH_TO_TEXT.equals(method)) {
                final String jsonStrFinal = jsonStr;
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        speechToText(jsonStrFinal);
                    }
                });
            } /*else if (METHOD_SET_COIN.equals(method)) {
                //首次订阅健康计划加金币
                String url = "/api/gold/task/local/finish?name=FIRST_ADD_HEALTH_PROGRAM";
                SimpleOperation operation = new SimpleOperation(url, CoinTask.class);
                new WebOperationScheduler(mActivity).sendOperation(operation);
            } */ else if (METHOD_CALL.equals(method)) {
                return call(version, new JSONObject(jsonStr));
            } else if (METHOD_OPEN_VIEW.equals(method)) {
//                IntentData data = DataConvert.parseObjectFromJson(jsonStr, IntentData.class);
//                if (AppContextUtil.compareVersion(data.minversion, AppContextUtil.getAppVersionName(mActivity)) <= 0) {
//                    AppContextUtil.sendIntent(mActivity, data.param);
//                }
            } else if (METHOD_OPEN_WEBVIEW.equals(method)) {
//                WebData data = DataConvert.parseObjectFromJson(jsonStr, WebData.class);
//                if (data != null && !TextUtils.isEmpty(data.url)) {
//                    Bundle bundle = new Bundle();
//                    bundle.putString(Args.ARG_WEB_URL, data.url);
//                    bundle.putBoolean(Args.ARG_AUTO_SET_TITLE, true);
//                    CommonWebAssistant.openWebActivity(mActivity, bundle);
//                }
            } else if (METHOD_WECHAT_SHARE.equals(method)) {
                //8512分享小程序
                shareMiniProgram(jsonStr);
            } else if (METHOD_SEND_MATERIAL.equals(method)) {
                sendMaterial(jsonStr);
            } else if (METHOD_SELECT_ACTION.equals(method)) {
//                BackJsData backJsData = new BackJsData();
//                JSONObject jsonObject = new JSONObject(jsonStr);
//                backJsData.mDoClose = jsonObject.optBoolean("do_close");
//                backJsData.mExecJs = jsonObject.optString("exec_js");
//                backJsData.mType = jsonObject.optString("type");
//                backJsData.data = jsonObject.optJSONObject("data").toString();
//                mOnAndroidJs.onSetBackBtn(backJsData);
            } else {
                return WebInjectManager.getInstance(mActivity).invoke(mActivity, method, jsonStr);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void sendMaterial(final String jsonStr) {
        try {
            final JSONObject jsonObject = new JSONObject(jsonStr);

            boolean execute = jsonObject.optBoolean("execute");
            final boolean btnVisible = jsonObject.optBoolean("btn_visible");

            final JSONObject dataJson = jsonObject.optJSONObject("data");
            final String fromType = jsonObject.optString("from_type");
            final String title = dataJson.optString("title");


            switch (fromType) {
                case "qa":
                    sendMaterialFromQA(dataJson, title, btnVisible, execute);
                    break;
                case "workbench":
                    sendMaterialFromWorkbench(dataJson, title, btnVisible, execute);
                    break;
                case "ppt":
                    sendMaterialPPTFromWorkbeanch(dataJson, title, btnVisible, execute);
                    break;
                default:
                    break;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMaterialFromQA(final JSONObject dataJson, final String title, final boolean btnVisible, boolean execute) {
        if (mActivity != null && mActivity instanceof CommonWebActivityContract.View) {
            ThreadPool.post(new Runnable() {
                @Override
                public void run() {
                    ((CommonWebActivityContract.View) mActivity).setRightButtonState("发送", null, btnVisible, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            RxBus.getDefault().post(new SendArticleFromQAMsg(dataJson));
                        }
                    });
                }
            });
        }
        if (execute) {
//            RxBus.getDefault().post(new SendArticleFromQAMsg(dataJson));
        }


    }

    private void sendMaterialFromWorkbench(final JSONObject dataJson, final String title, final boolean btnVisible, boolean execute) {
        if (mActivity != null && mActivity instanceof CommonWebActivityContract.View) {
            ThreadPool.post(new Runnable() {
                @Override
                public void run() {
                    ((CommonWebActivityContract.View) mActivity).setRightButtonState("发送", null, btnVisible, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            CommonWebAssistant.openSelFriendActivity(mActivity, dataJson.toString(), title, Args.MESSAGE_DATA_TYPE_DATA);
                        }
                    });
                }
            });
        }
        if (execute) {
//            CommonWebAssistant.openSelFriendActivity(mActivity, dataJson.toString(), title, Args.MESSAGE_DATA_TYPE_DATA);
        }
    }

    private void sendMaterialPPTFromWorkbeanch(final JSONObject dataJson, final String title, final boolean btnVisible, boolean execute) {
        if (mActivity != null && mActivity instanceof CommonWebActivityContract.View) {
            ThreadPool.post(new Runnable() {
                @Override
                public void run() {
                    ((CommonWebActivityContract.View) mActivity).setRightButtonState("发送", null, btnVisible, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            CommonWebAssistant.openSelFriendActivity(mActivity, dataJson.toString(), title, Args.MESSAGE_DATA_TYPE_PPT);
                        }
                    });
                }
            });
        }
        if (execute) {
//            CommonWebAssistant.openSelFriendActivity(mActivity, dataJson.toString(), title, Args.MESSAGE_DATA_TYPE_PPT);
        }
    }


    private void shareMiniProgram(String jsonStr) {
        ShareJs.ShareContent shareContent = DataConvert.fromJson(jsonStr, ShareJs.ShareContent.class);
        if (ShareJs.ShareTo.SHARE_TO_WX_SESSION.equals(shareContent.type)) {
            shareContent.direct_share_to = ShareJs.ShareTo.SHARE_TO_WX_SESSION;

        } else if (ShareJs.ShareTo.SHARE_TO_WX_FRIENDS.equals(shareContent.type)) {
            shareContent.direct_share_to = ShareJs.ShareTo.SHARE_TO_WX_FRIENDS;

        }
        shareContent.from = ShareJs.ShareContent.FROM_JS;
        ShareJs.formatParams(shareContent);
        mShareJs.share(shareContent, mEventMap.get(EVENT_SHARE_DONE), false, false);

    }

    @JavascriptInterface
    @Deprecated
    public boolean invoke(String version, String method, String jsonStr, String callBacks) {
        return invoke(version, method, jsonStr);
    }

    /**
     * 百度语音识别，并把结果按照事件的方式上传
     * <p/>
     * <a href="https://git.chunyu.me/gitlab_extend/views/md_file/md_view.html?file_name=%2Ffrontend%2Fourwork%2Fraw%2Fmaster%2Fdocs%2Fh5_native%2Fnative_bridge.md#chunyunativebridge-native方法列表（版本10）-语音识别-speechtotext">speechToText</a>
     * <a href="https://git.chunyu.me/gitlab_extend/views/md_file/md_view.html?file_name=%2Ffrontend%2Fourwork%2Fraw%2Fmaster%2Fdocs%2Fh5_native%2Fnative_bridge.md#chunyunativebridge-native监听方式的url">speechToTextDone</a>
     */
    private void speechToText(String jsonStr) {
        //TODO lx 百度语音包无法下载
//        try {
//            final SpeechToTextData data = (SpeechToTextData) new SpeechToTextData().fromJSONString(jsonStr);
//            if (data == null) {
//                return;
//            }
//
//            BaiduVoiceUtils.startRecognize(mActivity, new BaiduVoiceUtils.BaiduVoiceCallback() {
//                @Override
//                public void onRecognizeVoice(String result) {
//                    if (TextUtils.isEmpty(result)) {
//                        return;
//                    }
//                    List<String> callbacks = mEventMap.get(EVENT_SPEECH_TO_TEXT_DONE);
//                    if (callbacks == null || callbacks.isEmpty()) {
//                        return;
//                    }
//                    SpeechToTextData.DoneData doneData = new SpeechToTextData.DoneData();
//                    doneData.mUserInfo = data.mUserInfo;
//                    doneData.mText = result;
//                    String doneJs = "%s(" + doneData.toString() + ")";
//                    if (mOnAndroidJs != null) {
//                        for (String callback : callbacks) {
//                            Log.e("js", "speechToText js: " + String.format(doneJs, callback));
//                            mOnAndroidJs.execJSStr(String.format(doneJs, callback));
//                        }
//                    }
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 此为通用JS调用原生的方法，通常应用于特定的业务场景。
     * 例如原生定义了一个health_plan_changed的方法（健康助手订阅变化），JS可以调用这个接口告知客户端。
     * 通知名称和参数不受限制，以实际业务场景为准，调用时前端开发和客户端开发自行协商。
     */
    @JavascriptInterface
    public boolean call(String version, JSONObject jsonObject) {
        String method = jsonObject.optString("method");
        if (TextUtils.isEmpty(method)) {
            return false;
        }
        JSONObject params = jsonObject.optJSONObject("params");
        String callback = jsonObject.optString("callback");

        JSONObject result = WebInjectManager.getInstance(mActivity).call(method, params);
        if (result == null) {
            return false;
        }
        execJS(callback, result);
        return true;
    }

    /**
     * 使web view执行一段js回调函数
     *
     * @param webView
     * @param callbackMethod js callback 函数名
     * @param dict           回调函数所需的参数
     */
    @JavascriptInterface
    public void execJS(final WebView webView, final String callbackMethod, final JSONObject dict) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(
                        "javascript:" + callbackMethod + "(" + dict.toString() + ");"
                );
            }
        });
    }

    @JavascriptInterface
    public void execJS(String callbackMethod, JSONObject dict) {
        execJS(mWebView, callbackMethod, dict);
    }

    /***
     * ChunyuNativeBridge.on('接口版本','事件名','处理事件的JS函数名')
     * native返回值恒定为 true
     * 接口版本和接口名参数必填，其他参数可选
     */
    @JavascriptInterface
    public boolean on(String version, String event, String method) {
        try {
            if (!has(version, event)) {
                return false;
            }

            List<String> methodList;
            if (mEventMap.containsKey(event)) {
                methodList = mEventMap.get(event);
            } else {
                methodList = new LinkedList<>();
            }
            methodList.add(method);
            mEventMap.put(event, methodList);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 事件执行完就把注册的事件清除掉
     */
    public void removeEvent(String event) {
        mEventMap.remove(event);
    }

    /**
     * 封装一些回调
     */
    public interface onAndroidJs {
        /**
         * 设置网页后退按钮时的回调，ps：关闭web页面的控制也用这个！
         */
//        void onSetBackBtn(BackJsData backJsData);

        void onSetTitle(String title);

        void onClearCache();

        void execJSStr(String jsStr);

        void onSetActionBar(String jsonStr);
    }
}
