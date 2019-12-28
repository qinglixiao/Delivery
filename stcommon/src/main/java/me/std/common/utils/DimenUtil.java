package me.std.common.utils;

import android.util.TypedValue;

/**
 * Description:尺寸转换工具类
 * Author: lixiao
 * Create on: 2018/7/19.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class DimenUtil {

    /**
     * dp->px
     *
     * @param dp
     * @return
     */
    public static float dpToPx(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, AppContextUtil.getAppContext().getResources().getDisplayMetrics());
    }

    /**
     * px->dp
     *
     * @param px
     * @return
     */
    public static float pxTodp(float px) {
        final float scale = AppContextUtil.getAppContext().getResources().getDisplayMetrics().density;
        return px / scale;
    }

    /***
     * 从sp换算到px
     * @param  sp 要转换的sp大小
     * @return 转换后的像素大小
     */
    public static int spToPx(float sp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, sp, AppContextUtil.getAppContext().getResources().getDisplayMetrics());
    }

    /**
     * @return 获取屏幕宽度，单位是像素
     */

    public static int getDisplayWidth() {
        return AppContextUtil.getAppContext().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高
     *
     * @return
     */
    public static int getDisplayHeight() {
        return AppContextUtil.getAppContext().getResources().getDisplayMetrics().heightPixels;
    }

}
