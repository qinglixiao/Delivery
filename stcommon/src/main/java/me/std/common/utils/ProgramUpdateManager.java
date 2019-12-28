//
//  ProgramUpdateManager.java
//  SpringRainDoctor
//
//  Created by Eden He on 2012-6-21
//  Copyright (c) 2012 Chunyu.mobi
//  All rights reserved
//

package me.std.common.utils;

import android.text.TextUtils;

/**
  * Description:
  * Author: huangyuan
  * Create on: 2018/10/12
  * Job number: 1800829
  * Phone: 13120112517
  * Email: huangyuan@chunyu.me
  */
public class ProgramUpdateManager {

    private static final String CHUNYU_DOWNLOAD_URL =
            "http://www.chunyuyisheng.com/download/chunyu/latest/";

    private String newVersion = "1.0.0";

    private String newUpdates = "";

    private String newUrl = "";

    private String minVersion = "1.0.0";

    private String versionUpdateMsg = "";

    private int lastDelayTime = 0;
    private SharedPreferencesUtil mSharedPreferencesUtil;
    private ProgramUpdateManager() {
        if (mSharedPreferencesUtil == null) {
            mSharedPreferencesUtil = SharedPreferencesUtil.getPersonal("version");
            newVersion = mSharedPreferencesUtil.getString("newVersion", "1.0.0");
            newUpdates = mSharedPreferencesUtil.getString("newUpdates", "");
            lastDelayTime = mSharedPreferencesUtil.getInt("lastDelayTime", 0);
            minVersion = mSharedPreferencesUtil.getString("minVersion", "1.0.0");
            versionUpdateMsg = mSharedPreferencesUtil.getString("versionUpdateMsg", "");

            newUrl = mSharedPreferencesUtil.getString("newUrl", CHUNYU_DOWNLOAD_URL);
            if (TextUtils.isEmpty(newUrl)) {
                newUrl = CHUNYU_DOWNLOAD_URL;
            }
        }
    }

    private static ProgramUpdateManager manager;

    public static ProgramUpdateManager getInstance() {
        if (manager == null) {
            manager = new ProgramUpdateManager();
        }
        return manager;
    }

    public String getNewUrl() {
        return newUrl;
    }

    public boolean hasNewVersion() {
        return hasNewVersion(false);
    }

    public boolean hasNewVersion(boolean force) {
        // 如果选择下次提醒，则两天后提醒一次
        int delay = (int) (System.currentTimeMillis() / 1000) - lastDelayTime;
        boolean hasNewVersion = checkIfHasNewVersion(getNewVersion(), VersionInfo
                .getAppVersion());
        if (!hasNewVersion) {
            this.cancelPendingDownload();
        }
        return hasNewVersion && (delay > 60 * 60 * 48 || force);
    }

    public boolean canGetNewVersion() {
        boolean hasNewVersion =
                checkIfHasNewVersion(getNewVersion(), VersionInfo.getAppVersion());
        if (!hasNewVersion) {
            this.cancelPendingDownload();
        }
        return hasNewVersion;
    }

    public void setNewVersion(String ver, String updates, String url) {
        /**
         *
         *
         * boolean hasNewVersion = checkIfHasNewVersion(ver, getNewVersion());
         * if (hasNewVersion) {
         *     pref.edit().putString("newVersion", ver).commit();
         *     newVersion = ver;
         *     newUpdates = updates;
         * }
         *
         *  修改为：直接修改本地保存的新版本信息，
         *  原因是在测试服务和线上服务进行切换时，测试服务上较高的版本号会对切换到线上服务后的升级提醒造成影响
         *  by: Eden
         *  2015-10-18
         */
        newVersion = ver;
        newUpdates = updates;
        newUrl = TextUtils.isEmpty(url) ? CHUNYU_DOWNLOAD_URL : url;
        mSharedPreferencesUtil.saveString("newVersion", ver);
        mSharedPreferencesUtil.saveString("newUrl", newUrl);
    }


    public String getNewVersion() {
        return newVersion;
    }

    public String getNewUpdates() {
        return newUpdates;
    }

    public String getMinVersion() {
        return minVersion;
    }

    public String getForceUpdateMsg() {
        return versionUpdateMsg;
    }

    public void setMinVersion(String minVer, String updateMsg) {
        minVersion = minVer;
        versionUpdateMsg = updateMsg;
        mSharedPreferencesUtil.saveString("minVersion", minVersion);
        mSharedPreferencesUtil.saveString("versionUpdateMsg", updateMsg);
    }

    public boolean checkForceUpdate() {
        return checkIfHasNewVersion(getMinVersion(), VersionInfo.getAppVersion());
    }

    public void updateLater() {
        lastDelayTime = (int) (System.currentTimeMillis() / 1000);
        mSharedPreferencesUtil.saveInt("lastDelayTime", lastDelayTime);
    }

    private void cancelPendingDownload() {
        mSharedPreferencesUtil.saveInt("pendingDownload", 0);
    }

    public boolean hasPendingDownload() {
        int hasPending = mSharedPreferencesUtil.getInt("pendingDownload", 0);
        return hasPending == 1;
    }

    /**
     * @param remoteVersion
     * @param currentVersion
     * @return
     */
    public boolean checkIfHasNewVersion(String remoteVersion, String currentVersion) {
        try {
            String remoteComponents[] = remoteVersion.split("\\.");
            String currentComponents[] = currentVersion.split("\\.");

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
            return remoteComponents.length > currentComponents.length;
        } catch (Exception e) {
            return false;
        }

    }
}


