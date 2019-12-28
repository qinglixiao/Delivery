package me.std.common.utils;

import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Description: 封装Android日志输出
 * Author: huangyuan
 * Create on: 2018/10/16
 * Job number: 1800829
 * Phone: 13120112517
 * Email: huangyuan@chunyu.me
 */
public class LogUtil {
    private static final boolean FILE_OUTPUT = false;

    private static final String TAG = "LogUtil";
    private static FileWriter fw = null;

//    public static void fileLog(String content) {
//        if (FILE_OUTPUT) {
//            File file = FileUtil.getTempLogPath();
//            file.mkdirs();
//
//            try {
//                if (fw == null) {
//                    String logFile = FileUtil.getTempLogPath() + "log.txt";
//                    fw = new FileWriter(logFile, true);
//                }
//                fw.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
//                        .format(new Date()));
//                fw.write("\t");
//                fw.write(content);
//                fw.write("\n");
//                fw.flush();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static void debug(String msg) {
        if (AppContextUtil.DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (AppContextUtil.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (AppContextUtil.DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (AppContextUtil.DEBUG) {
            Log.i(tag, msg);

        }
    }

    public static void d(String tag, String msg) {
        if (AppContextUtil.DEBUG) {
            Log.d(tag, msg);

        }
    }

    public static void v(String tag, String msg) {
        if (AppContextUtil.DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static int v(String tag, String msg, Throwable tr) {
        return Log.v(tag, msg, tr);
    }


}