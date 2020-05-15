//
//  VersionInfo.java
//  SpringRainDoctor
//
//  Created by Eden He on 2011-11-30
//  Copyright (c) 2011 Chunyu.mobi 
//  All rights reserved
//

package me.std.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VersionInfo {
    public static int MAJOR_VERSION;
    public static int MINOR_VERSION;
    public static int MINI_VERSION;
    public static int BUILD_NUMBER;

    private static String version_name;
    private static int version_code;

    public static void initVersionInfo(String versionName, int versionCode) {
        version_name = versionName;
        version_code = versionCode;

        String[] version = versionName.split("\\.");
        MAJOR_VERSION = Integer.valueOf(version[0]);
        MINOR_VERSION = Integer.valueOf(version[1]);
        MINI_VERSION = Integer.valueOf(version[2]);
        BUILD_NUMBER = Integer.valueOf(new SimpleDateFormat("yyMMdd").format(new Date(System.currentTimeMillis())));
    }

    public static String getAppVersion() {
        return String.format("%d.%d.%d", MAJOR_VERSION, MINOR_VERSION, MINI_VERSION);
    }

    public static String getVersionName() {
        return version_name;
    }

    /**
     * 版本号比较
     *
     * @param version1
     * @param version2
     * @return version1 > version2 -> 1
     * versoin1 < verison2 -> -1
     * version1 = verion2 -> 0
     */
    public static int compare(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int m = v1.length;
        int n = v2.length;

        for (int i = 0; i < Math.max(m, n); i++) {
            int num1 = i < m ? Integer.valueOf(v1[i]) : 0;
            int num2 = i < n ? Integer.valueOf(v2[i]) : 0;

            if (num1 != num2) {
                return num1 > num2 ? 1 : -1;
            }
        }

        return 0;
    }

}