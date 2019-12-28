package me.std.common.utils;

import android.view.View;

public class ViewUtil {
    public static void visible(View view) {
        if (view != null && view.getVisibility() != View.VISIBLE) {
            setVisible(view, View.VISIBLE);
        }
    }

    public static void gone(View view) {
        if (view != null && view.getVisibility() != View.GONE) {
            setVisible(view, View.GONE);
        }
    }

    public static void hide(View view) {
        if (view != null && view.getVisibility() != View.INVISIBLE) {
            setVisible(view, View.INVISIBLE);
        }
    }

    /***
     * 只处理{View.VISIBLE，View.GONE} 两种状态
     * @param view
     * @param isShow
     */
    public static void show(View view, boolean isShow) {
        if (isShow) {
            visible(view);
        } else {
            gone(view);
        }
    }

    private static void setVisible(View view, int visible) {
        if (view != null) {
            view.setVisibility(visible);
        }
    }
}
