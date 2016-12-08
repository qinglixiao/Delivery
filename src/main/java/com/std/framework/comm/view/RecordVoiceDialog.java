package com.std.framework.comm.view;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.std.framework.R;
import com.std.framework.databinding.RecordVoiceBinding;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/11/28.
 * Modify by：lx
 */
public class RecordVoiceDialog extends Dialog {
    private RecordVoiceBinding recordVoiceBinding;

    public RecordVoiceDialog(Context context) {
        super(context);
        initView();
        getWindow().setWindowAnimations(R.style.AnimationAlpha);
    }

    public RecordVoiceDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RecordVoiceDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void initView() {
        View view = View.inflate(getContext(), R.layout.view_record_voice, null);
        recordVoiceBinding = DataBindingUtil.bind(view);
        setContentView(view);
    }
}
