package com.std.framework.ffmpeg;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/7.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class FFMediaConvert {
    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("avutil-56");
        System.loadLibrary("avcodec-58");
    }

    public static native int audioToMp3(String srcFile, String targetFile);
    public static native int add(int a,int b);
}
