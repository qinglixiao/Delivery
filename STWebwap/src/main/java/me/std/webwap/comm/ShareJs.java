package me.std.webwap.comm;

import android.graphics.Bitmap;
import androidx.fragment.app.FragmentActivity;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.std.common.core.ThreadPool;

/**
 * 分享相关的Js注入
 *
 * @author michael created on 15/9/22.
 */
public class ShareJs {
    private ShareContent mShareContent;
    private ShareBtnListener mListener;
    private ShareDone mShareDone;

    private FragmentActivity mActivity;
    private WebView mWebView;

//    private ShareUtil.SourcePage mSourcePage; // 分享来源


    public ShareJs(FragmentActivity activity, WebView webView) {
        mActivity = activity;
        mWebView = webView;
    }

    public void setListener(ShareBtnListener listener) {
        mListener = listener;
    }

    public void setShareDone(ShareDone shareDone) {
        mShareDone = shareDone;
    }

    /**
     * @param shareContent     要分享的内容
     * @param callbacks        回调函数的名称列表
     * @param ignoreBtnVisible 忽略H5中js 控制share按钮的显示
     * @param forceDoShare     强制显示分享页面
     */
    public void share(ShareContent shareContent, List<String> callbacks, boolean ignoreBtnVisible,
                      boolean forceDoShare) {
        if (shareContent == null) {
            return;
        }

        // 合并参数。非得缺省一些参数。。。
        merge(shareContent);

        if (TextUtils.isEmpty(shareContent.direct_share_to)) {
            if (mListener != null) {
                if (!ignoreBtnVisible) {
                    mListener.setShareBtn(mShareContent.btn_visible);
                }
                mListener.setShareContent(mShareContent, callbacks);
            }

            // 变态需求
            // 刚进入页面时传参{btn_visible,share_to,title,desc,link,img}进行配置
            // 分享时只传一个参数{do_share:true}
            if (!shareContent.do_share && !forceDoShare) {
                return;
            }
            showShareDialog(callbacks);
        } else {
            directShare(shareContent.direct_share_to, callbacks);
        }
    }

//    /**
//     * 设置分享来源
//     *
//     * @param sourcePage 分享来源
//     */
//    public void setSourcePage(ShareUtil.SourcePage sourcePage) {
//        mSourcePage = sourcePage;
//    }

    /**
     * 弹出分享弹窗
     */
    private void showShareDialog(List<String> callbacks) {
//        ChunyuShareDialog dialog = new ChunyuShareDialog();
//
//        // 设置分享来源
//        if (mSourcePage != null) {
//            dialog.setSourcePage(mSourcePage);
//        }
//
//        if (mShareContent.share_to == null || mShareContent.share_to.size() <= 0) {
//            mShareContent.share_to = new ArrayList<String>(Arrays.asList(ShareTo.SHARE_TO_QZONE,
//                    ShareTo.SHARE_TO_WEIBO, ShareTo.SHARE_TO_WX_SESSION,
//                    ShareTo.SHARE_TO_WX_FRIENDS));
//        }
//        for (String shareto : mShareContent.share_to) {
//            switch (shareto) {
//                case ShareTo.SHARE_TO_QZONE:
//                    dialog.addQZoneShare(mShareContent.title, mShareContent.desc,
//                            mShareContent.img, mShareContent.link,
//                            getShareCallback(ShareTo.SHARE_TO_QZONE, mShareContent, callbacks));
//                    break;
//                case ShareTo.SHARE_TO_QQ_FRIENDS:
//                    dialog.addQQFriendsShare(mShareContent.title, mShareContent.desc,
//                            mShareContent.img, mShareContent.link,
//                            getShareCallback(ShareTo.SHARE_TO_QQ_FRIENDS, mShareContent, callbacks));
//                    break;
//                case ShareTo.SHARE_TO_WEIBO:
//                    dialog.addWeiboShare(mShareContent.title, mShareContent.img,
//                            mShareContent.link,
//                            getShareCallback(ShareTo.SHARE_TO_WEIBO, mShareContent, callbacks));
//                    break;
//                case ShareTo.SHARE_TO_WX_SESSION:
//                    dialog.addWeixinSessionShare(mShareContent.title, mShareContent.desc,
//                            mShareContent.img, mShareContent.link,
//                            getShareCallback(ShareTo.SHARE_TO_WX_SESSION, mShareContent, callbacks));
//                    break;
//                case ShareTo.SHARE_TO_WX_FRIENDS:
//                    dialog.addWeixinFriendsShare(mShareContent.title, mShareContent.desc,
//                            mShareContent.img, mShareContent.link,
//                            getShareCallback(ShareTo.SHARE_TO_WX_FRIENDS, mShareContent, callbacks));
//                    break;
//                case ShareTo.SHARE_TO_SMS:
//                    dialog.addSMSshare(mShareContent.desc + " " + mShareContent.link);
//                default:
//                    break;
//            }
//        }
//
//        dialog.show(mActivity.getSupportFragmentManager(), "jsp_share_dialog");
    }

    /**
     * 直接调起分享
     */
    private void directShare(String directShareTo, final List<String> callbacks) {
//        ShareEntry shareEntry = null;
//        final String imgUrl_cover = "https://resource.chunyu.mobi/@/media/images/2018/09/06/6c34/4d3cd8bf22ba_w420_h336_.png";
//        final String imgUrl_circle = "https://media2.chunyuyisheng.com/@/media/images/2018/07/26/caab/5f38601ad6ac_w670_h738_.png";
//
//        switch (directShareTo) {
//            case ShareTo.SHARE_TO_QZONE:
//                shareEntry = new ShareEntry(mShareContent.title, mShareContent.desc,
//                        mShareContent.img, mShareContent.link,
//                        getShareCallback(ShareTo.SHARE_TO_QZONE, mShareContent, callbacks),
//                        ShareType.SHARE_QZONE);
//                break;
//            case ShareTo.SHARE_TO_QQ_FRIENDS:
//                shareEntry = new ShareEntry(mShareContent.title, mShareContent.desc,
//                        mShareContent.img, mShareContent.link,
//                        getShareCallback(ShareTo.SHARE_TO_QQ_FRIENDS, mShareContent, callbacks),
//                        ShareType.SHARE_QQ_FRIENDS);
//                break;
//            case ShareTo.SHARE_TO_WEIBO:
//                shareEntry = new ShareEntry(null, mShareContent.title,
//                        mShareContent.img, mShareContent.link,
//                        getShareCallback(ShareTo.SHARE_TO_WEIBO, mShareContent, callbacks),
//                        ShareType.SHARE_WEIBO);
//                break;
//            case ShareTo.SHARE_TO_WX_SESSION:
//                if (null == mShareContent.userName) {
//                    shareEntry = new ShareEntry(mShareContent.title, mShareContent.desc,
//                            mShareContent.img, mShareContent.link,
//                            getShareCallback(ShareTo.SHARE_TO_WX_SESSION, mShareContent, callbacks),
//                            ShareType.SHARE_WEIXIN_SESSION);
//                } else {
//                    shareEntry = new ShareEntry(mShareContent.title, imgUrl_cover, mShareContent.link,
//                            ShareType.SHARE_WEIXIN_SESSION, mShareContent.path, mShareContent.userName,
//                            getShareCallback(ShareTo.SHARE_TO_WX_SESSION, mShareContent, callbacks), null);
//                }
//
//                break;
//            case ShareTo.SHARE_TO_WX_FRIENDS:
//                //link有默认值,所以判断小程序码
//                if (mShareContent.thumImage == null) {
//                    shareEntry = new ShareEntry(mShareContent.title, mShareContent.desc,
//                            mShareContent.img, mShareContent.link,
//                            getShareCallback(ShareTo.SHARE_TO_WX_FRIENDS, mShareContent, callbacks),
//                            ShareType.SHARE_WEIXIN_FRIENDS);
//                } else {
//                    final ShareImageCreator shareImageCreator = new ShareImageCreator(mActivity);
//                    mActivity.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            shareImageCreator.setListener(new ShareImageCreator.ShareImageCreatorListener() {
//                                @Override
//                                public void onSuccess(Bitmap bitmap) {
//                                    if (bitmap == null) {
//                                        return;
//                                    }
//
//                                    ShareEntry shareEntry = new ShareEntry(mShareContent.title, null, null, ShareType.SHARE_WEIXIN_FRIENDS, null, null, getShareCallback(ShareTo.SHARE_TO_WX_FRIENDS, mShareContent, callbacks), bitmap);
//
//                                    new ShareUtil().share(mActivity, shareEntry);
//                                }
//
//                                @Override
//                                public void onError(Error error) {
//
//                                }
//                            });
//                            shareImageCreator.getBitmp(mShareContent.thumImage, imgUrl_circle, null, null, null, null, null);
//                        }
//                    });
//                }
//                break;
//            case ShareTo.SHARE_TO_SMS:
//                shareEntry = new ShareEntry(null, mShareContent.desc + " " + mShareContent.link,
//                        null, null, null, ShareType.SHARE_SMS);
//                break;
//            default:
//                break;
//        }
//
//        if (shareEntry != null) {
//            new ShareUtil().share(mActivity, shareEntry);
//        }
    }

//    private ShareCallback getShareCallback(String shareTo, ShareContent shareContent, final List<String> callbacks) {
//        if (callbacks == null || callbacks.isEmpty()) {
//            return null;
//        }
//        final ShareResult result = new ShareResult();
//        result.share_to = shareTo;
//        result.share_info = shareContent;
//        return new ShareCallback() {
//            @Override
//            public void onShareReturn() {
//                result.share_success = true;
//                doJS(callbacks, result.toString());
//            }
//
//            @Override
//            public void onShareFailed(String s) {
//                result.share_success = false;
//                doJS(callbacks, result.toString());
//            }
//        };
//    }

    /**
     * 执行js回调代码
     */
    private void doJS(final List<String> callbacks, String jsonStr) {
        final String template = "javascript:%s(" + jsonStr + ")";
        ThreadPool.post(new Runnable() {
            @Override
            public void run() {
                for (String callback : callbacks) {
                    Log.e("js", "sharedone js: " + String.format(template, callback));
                    mWebView.loadUrl(String.format(template, callback));
                }
                if (mShareDone != null) {
                    mShareDone.onShareDone();
                }
            }
        });
    }

    private void merge(ShareContent shareContent) {
        if (mShareContent == null) {
            mShareContent = new ShareContent();
        }
        mShareContent.btn_visible = shareContent.btn_visible;
        mShareContent.from = shareContent.from;
        if (!TextUtils.isEmpty(shareContent.title)) {
            mShareContent.title = shareContent.title;
        }
        if (!TextUtils.isEmpty(shareContent.desc)) {
            mShareContent.desc = shareContent.desc;
        }
        if (!TextUtils.isEmpty(shareContent.link)) {
            mShareContent.link = shareContent.link;
        }
        if (!TextUtils.isEmpty(shareContent.img)) {
            mShareContent.img = shareContent.img;
        }
        if (!TextUtils.isEmpty(shareContent.userName)) {
            mShareContent.userName = shareContent.userName;
        }
        if (!TextUtils.isEmpty(shareContent.path)) {
            mShareContent.path = shareContent.path;
        }
        if (!TextUtils.isEmpty(shareContent.thumImage)) {
            mShareContent.thumImage = shareContent.thumImage;
        }
        if (shareContent.getmBitmap() != null) {
            mShareContent.setmBitmap(shareContent.getmBitmap());
        }
        mShareContent.share_to = shareContent.share_to;
    }

    public static class ShareContent implements Serializable {
        public static final String FROM_SHARE_KEY = "share_key";
        public static final String FROM_INTENT = "intent";
        public static final String FROM_JS = "JS";

        /**
         * 是否隐藏、显示右上角分享按钮
         */
        public boolean btn_visible;
        /**
         * 是否立即弹出分享菜单， 默认为false不弹出菜单
         */
        public boolean do_share = false;
        /**
         * 可分享的渠道，根据分享渠道，显示不同的可分享按钮
         */
        public ArrayList<String> share_to;
        /**
         * 分享的标题，如果不传，默认为当前H5的页面标题
         */
        public String title;
        /**
         * 分享的描述内容，如果不传，默认为当前H5的URL，与微信分享机制类同
         */
        public String desc;
        /**
         * 分享链接，如果不传，默认为当前H5页面的URL
         */
        public String link;
        /**
         * 分享小图，如果不传，客户端给个默认值
         */
        public String img;

        public String from;

        /**
         * 直接调起分享的分享方式
         */
        public String direct_share_to;

        public Params need_param;

        //小程序分享
        public String type;
        public String userName;
        public String path;

        //朋友圈
        public String thumImage;

        public Bitmap mBitmap;


        public Bitmap getmBitmap() {
            return mBitmap;
        }

        public void setmBitmap(Bitmap mBitmap) {
            this.mBitmap = mBitmap;
        }

        /**
         * 设置默认值
         */
        public void init(String title, String currentUrl, String defaultImageUrl) {
            this.title = title;
            this.desc = currentUrl;
            this.link = currentUrl;
            this.img = defaultImageUrl;
            this.share_to = new ArrayList<>();
            share_to.add(ShareTo.SHARE_TO_QZONE);
            share_to.add(ShareTo.SHARE_TO_WEIBO);
            share_to.add(ShareTo.SHARE_TO_WX_SESSION);
            share_to.add(ShareTo.SHARE_TO_WX_FRIENDS);
        }

        public ShareContent() {
        }

        public ShareContent(ArrayList<String> shareToList, String title, String desc, String imgUrl,
                            String pageUrl) {
            this.share_to = shareToList;
            this.title = title;
            this.desc = desc;
            this.link = pageUrl;
            this.img = imgUrl;
        }

        /**
         * 默认分享到：QQ、微博、微信
         */
        public ShareContent(String title, String desc, String imgUrl, String pageUrl) {
            this.title = title;
            this.desc = desc;
            this.link = pageUrl;
            this.img = imgUrl;
        }

        /**
         * 分享小程序给和朋友圈
         */
        public ShareContent(String appName, String title, String imgUrl, String oldPageUrl, String path,
                            Bitmap bitmap) {
            this.userName = appName;
            this.path = path;
            mBitmap = bitmap;
            this.title = title;
            this.link = oldPageUrl;
            this.img = imgUrl;
        }
    }

    public static void formatParams(ShareContent shareContent) {
        if (shareContent == null || shareContent.need_param == null) {
            return;
        }
        if (shareContent.need_param.version == Boolean.TRUE
                && !TextUtils.isEmpty(shareContent.link)) {
//            shareContent.link = NetworkConfig.getInstance().addParams(
//                    shareContent.link, "version", VersionInfo.getShortAppVersion());
        }
    }

    public static class ShareResult implements Serializable {
        /**
         * 用户分享完成为true，如果分享失败或者取消分享，返回false
         */
        public boolean share_success;
        /**
         * 用户分享的渠道是啥，参考上面的列表，如果用户取消分享，不返回此字段
         */
        public String share_to;

        public ShareContent share_info;
    }

    public static class Params implements Serializable {

        public boolean version;
    }

    public static class ShareTo {
        public static final String SHARE_TO_WX_SESSION = "weixin_haoyou";
        public static final String SHARE_TO_WX_FRIENDS = "weixin_pengyouquan";
        public static final String SHARE_TO_QZONE = "qq";
        public static final String SHARE_TO_QQ_FRIENDS = "qq_haoyou";
        public static final String SHARE_TO_WEIBO = "weibo";

        public static final String SHARE_TO_SMS = "sms"; //客户端增加
    }

    public interface ShareBtnListener {
        void setShareBtn(boolean isShowShareBtn);

        void setShareContent(ShareContent shareContent, List<String> callback);
    }

    public interface ShareDone {
        void onShareDone();
    }
}
