package com.std.framework.util;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * 项目资源工具类
 * Created by gfy on 2016/4/7.
 */
public class ResourceUtil {

    /**
     * 获取应用资源
     *
     * @return
     */
    public static Resources getResource() {
        return AppUtil.getAppContext().getResources();
    }

    /**
     * 根据图像名称获取drawable
     *
     * @param drawableName
     * @return
     */
    public static Drawable getDrawable(String drawableName) {
        int id = getDrawableId(drawableName);
        if (id == -1 || id == 0)
            return null;
        else
            return AppUtil.getAppContext().getResources().getDrawable(id);
    }

    /**
     * 获取string
     *
     * @param stringName
     * @return
     */
    public static String getString(String stringName) {
        int id = getStringId(stringName);
        if (id == -1 || id == 0)
            return "";
        else
            return AppUtil.getAppContext().getResources().getString(id);
    }

    /**
     * 获取颜色
     *
     * @param colorName
     * @return
     */
    public static int getColor(String colorName) {
        int id = getColorId(colorName);
        if (id == -1 || id == 0)
            return -1;
        else
            return AppUtil.getAppContext().getResources().getColor(id);
    }

    /**
     * 获取给定颜色对应的id
     *
     * @param colorName
     * @return
     */
    private static int getColorId(String colorName) {
        try {
            return AppUtil.getAppContext().getResources().getIdentifier(colorName, "color", AppUtil.getAppContext().getPackageName());
        } catch (Exception ex) {
            return -1;
        }
    }

    /**
     * 获取给定字符串对应的id
     *
     * @param stringName
     * @return
     */
    private static int getStringId(String stringName) {
        try {
            return AppUtil.getAppContext().getResources().getIdentifier(stringName, "string", AppUtil.getAppContext().getPackageName());
        } catch (Exception ex) {
            return -1;
        }
    }

    /**
     * 获取给定图像对应的id
     *
     * @param drawableName
     * @return
     */
    private static int getDrawableId(String drawableName) {
        try {
            return AppUtil.getAppContext().getResources().getIdentifier(drawableName, "drawable", AppUtil.getAppContext().getPackageName());
        } catch (Exception ex) {
            return -1;
        }
    }

    /**
     * 描          述 ：通过给定的布局文件名获取对应的资源ID
     * 创建日期  : 2014-1-27
     * 作           者 ： lx
     * 修改日期  : ·
     * 修   改   者 ：
     *
     * @param layoutName
     * @return 查找成功返回：id
     * 失败返回：-1
     */
    public static int getLayoutId(String layoutName) {
        try {
            return AppUtil.getAppContext().getResources().getIdentifier(layoutName, "layout", AppUtil.getAppContext().getPackageName());
        }catch (Exception ex){
            return -1;
        }
    }

}
