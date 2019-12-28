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

}
