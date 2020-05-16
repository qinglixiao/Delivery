package com.std.framework.comm.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;

import com.std.framework.R;

/**
 * Description : 底部弹出dialog, 内容由用户传入
 * Author:       lx
 * Create on:  2016/12/10.
 * Modify by：lx
 */
public class BottomPopContainer extends Dialog {
    private Context context;
    private View view;

    public BottomPopContainer(Context context, @LayoutRes int layoutId) {
        super(context, R.style.BottomPopDialogStyle);
        this.context = context;
        setWindowAttribute();
        setView(layoutId);
    }

    private void setWindowAttribute() {
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.LEFT | Gravity.BOTTOM;
        window.setAttributes(params);
        setCanceledOnTouchOutside(true);
    }

    private void setView(@LayoutRes int layoutId){
        view = LayoutInflater.from(context).inflate(layoutId, null, false);
        setContentView(view);
    }

    public View getView() {
        return view;
    }
}
