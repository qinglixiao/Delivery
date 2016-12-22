package com.std.framework.core;

import com.std.framework.util.AppUtil;

import java.io.File;

/**
 * Description : 全局常量
 * Author:       lx
 * Create on:  2016/11/28.
 * Modify by：lx
 */
public class GlobalConfig {
    /**
     * 路径分隔符
     */
    private static final String SEPARATOR = File.separator;

    /**
     * 应用安装目录
     */
    public static final String APP_INSTALL_DIR = AppUtil.getAppDirectory();
    private static final String PREFIX = APP_INSTALL_DIR + SEPARATOR;

    /**
     * 语音文件存放目录
     */
    public static final String VOICE_DIR = PREFIX + "voice" + SEPARATOR;

}
