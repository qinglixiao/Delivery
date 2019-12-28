//
//  NetworkControl.java
//  SpringRainDoctor
//
//  Created by Eden He on 2012-2-14
//  Copyright (c) 2012 Chunyu.mobi 
//  All rights reserved
//

package me.std.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class NetStateUtil {

    /**
     * 网络是否连接
     *
     * @return
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) AppContextUtil.getAppContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }


    public static boolean isWifiConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) AppContextUtil.getAppContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mWiFiNetworkInfo != null) {
            return mWiFiNetworkInfo.isAvailable();
        }
        return false;
    }

    /**
     * 是否蜂窝网络
     *
     * @return
     */
    public static boolean isMobileConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) AppContextUtil.getAppContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mMobileNetworkInfo = mConnectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mMobileNetworkInfo != null) {
            return mMobileNetworkInfo.isAvailable();
        }
        return false;
    }

    public static int getNetworkType() {
        int netType = 0;
        ConnectivityManager connMgr = (ConnectivityManager) AppContextUtil.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = 1;// wifi
        } else if (nType == ConnectivityManager.TYPE_MOBILE) {
            int nSubType = networkInfo.getSubtype();
            TelephonyManager mTelephony = (TelephonyManager) AppContextUtil.getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
            if (nSubType == TelephonyManager.NETWORK_TYPE_UMTS
                    && !mTelephony.isNetworkRoaming()) {
                netType = 3;// 3G
            } else {
                netType = 2;// 2G
            }
        }
        return netType;
    }
}
