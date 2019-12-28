package me.std.base.view.dialog;

import android.app.Dialog;
import android.content.Context;

import me.std.base.R;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/12/12.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class LoadingDialog extends Dialog {
    public LoadingDialog(Context context) {
        super(context, R.style.CYLoaddingDialog);
        initView();
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, R.style.CYLoaddingDialog);
        initView();
    }

    protected LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    private void initView() {
        setContentView(R.layout.loading_dialog_view);
    }
}
