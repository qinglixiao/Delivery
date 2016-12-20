package com.std.framework.comm.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.std.framework.R;

/**
 * Description : 底部弹出dialog, 内容可以由用户传入
 * Author:       lx
 * Create on:  2016/12/10.
 * Modify by：lx
 */
public class BottomPopView extends Dialog {
    private View view;

    public BottomPopView(Context context, @LayoutRes int layoutId) {
        super(context, R.style.BottomPopViewStyle);
        initWindow();
        view = LayoutInflater.from(context).inflate(layoutId, null, false);
        setContentView(view);
    }

    private void initWindow() {
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.LEFT | Gravity.BOTTOM;
        window.setAttributes(params);
        setCanceledOnTouchOutside(true);
    }

    public View getView() {
        return view;
    }
}
