package me.std.common.utils;

import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.webkit.MimeTypeMap;

/**
 * 下载类, 默认下载春雨医生APK, 也可以下载其他文件.
 * 存储位置: /sdcard[/download/][filename]
 * <p/>
 * Created by C.L.Wang on 15/7/27.
 */
@TargetApi(11)
public class DownloadUtil {

    public static final String DEFAULT_FILE_NAME = "chunyuhospital.apk";

    public static final String DEFAULT_TITLE = "门头沟中医医院";

    final private Context mContext; // 下载进行
    final private String mUrl; // URL地址
    final private String mFileName; // 文件名
    final private String mTitle; // 通知栏标题

    private DownloadManager mDownloadManager; // 下载管理器
    private long mDownloadId; // 下载ID

    // 下载完成的接收器
    final private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (downloadId == mDownloadId) {
                Uri downloadFileUri = mDownloadManager.getUriForDownloadedFile(downloadId);
                MashupAppUtils.installApp(mContext, downloadFileUri);
                destroyArgs();
            }
        }
    };

    /**
     * 默认构造器, 下载春雨医生APK
     *
     * @param context 上下文
     */
    public DownloadUtil(Context context) {
        this(context, ProgramUpdateManager.getInstance().getNewUrl(), DEFAULT_FILE_NAME, DEFAULT_TITLE);
    }

    public DownloadUtil(Context context, String url) {
        this(context, url, DEFAULT_FILE_NAME, DEFAULT_TITLE);
    }

    /**
     * 参数构造器, 下载其他文件
     *
     * @param context  活动
     * @param url      URL
     * @param fileName 存储文件名
     * @param title    通知栏标题
     */
    @SuppressWarnings("WeakerAccess")
    public DownloadUtil(Context context, String url, String fileName, String title) {
        mContext = context;
        mUrl = url;
        mFileName = fileName;
        mTitle = title;
        initArgs();
    }

    /**
     * 下载文件
     */
    public void download() {

        // 设置下载Url
        // Vivo X6A下载https://media2.chunyuyisheng.com/cloud-hospital-1.0.apk失败，改成http即可
        Uri resource = Uri.parse(mUrl.replace("https://", "http://"));

        DownloadManager.Request request;
        try {
            request = new DownloadManager.Request(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // 设置文件类型
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(mUrl));
        request.setMimeType(mimeString);

        // 下载完成时在进度条提示
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        // 存储sdcard目录的download文件夹
//        request.setDestinationInExternalPublicDir("/download/", mFileName);
        request.setDestinationInExternalFilesDir(AppContextUtil.getAppContext(), Environment.DIRECTORY_DOWNLOADS, mFileName);

        if (Build.MODEL.equals("HUAWEI NXT-AL10")) {
            //华为Mate 8 只能这样设置才能下载，而且只在下载完成后才会在通知栏里显示，其它设置无效，而且不会下载
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);
            ToastUtil.getInstance().showToast("正在下载...");
        }

        request.setTitle(mTitle);

        // 开始下载
        try {
            mDownloadId = mDownloadManager.enqueue(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 初始化
    private void initArgs() {
        mDownloadManager = (DownloadManager) mContext.getSystemService((Context.DOWNLOAD_SERVICE));
        mContext.registerReceiver(mReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    // 析构
    private void destroyArgs() {
        mContext.unregisterReceiver(mReceiver);
    }

}
