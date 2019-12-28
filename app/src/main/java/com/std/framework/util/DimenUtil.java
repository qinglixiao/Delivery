package com.std.framework.util;

import android.util.TypedValue;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/10/8.
 * Modify by：lx
 */
public class DimenUtil {
    /**
     * 描          述 ：dp转化为px
     * 创建日期  : 2014-11-30
     * 作           者 ： lx
     * 修改日期  :
     * 修   改   者 ：
     *
     * @param dp
     * @return
     * @version : 1.0
     */
    public static float dpToPx(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, AppUtil.getAppContext().getResources().getDisplayMetrics());
    }

    /**
     * 描          述 ：px转化为dp
     * 创建日期  : 2014-11-30
     * 作           者 ： lx
     * 修改日期  :
     * 修   改   者 ：
     *
     * @param px
     * @return
     * @version : 1.0
     */
    public static float pxTodp(float px) {
        final float scale = AppUtil.getAppContext().getResources().getDisplayMetrics().density;
        return px / scale;
    }

    /**
     * 描          述 ：sp转化为px
     * 创建日期  : 2014-11-30
     * 作           者 ： lx
     * 修改日期  :
     * 修   改   者 ：
     *
     * @return
     * @version : 1.0
     */
    public static float spToPx(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, AppUtil.getAppContext().getResources().getDisplayMetrics());
    }

}
