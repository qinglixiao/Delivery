package com.std.framework.util;

import android.view.View;

public class ViewUtil {
    public static void visible(View view) {
        setVisible(view, View.VISIBLE);
    }

    public static void gone(View view) {
        setVisible(view, View.GONE);
    }

    public static void hide(View view) {
        setVisible(view, View.INVISIBLE);
    }

    private static void setVisible(View view, int visible) {
        if (view != null) {
            view.setVisibility(visible);
        }
    }
}
