//
//  ProgressDialogFragment.java
//  DoctorCommon
//
//  Created by Eden He on 2013-8-24
//  Copyright (c) 2013 Chunyu.mobi 
//  All rights reserved
//

package me.std.common.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ProgressDialogFragment extends DialogFragment {

    private String mText;
    private OnCancelListener mOnCancelListener = null;
    private boolean mIsDissmissOnPause = false;

    public ProgressDialogFragment() {
        super();
    }

    public ProgressDialogFragment setTitle(String text) {
        mText = text;
        return this;
    }

    public ProgressDialogFragment setOnCancelListener(OnCancelListener listener) {
        mOnCancelListener = listener;
        return this;
    }

    public ProgressDialogFragment setDissmissOnPause(boolean dissmissOnPause) {
        mIsDissmissOnPause = dissmissOnPause;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage(mText);
        dialog.setIndeterminate(true);

        if (mOnCancelListener != null) {
            this.setCancelable(true);
            dialog.setOnCancelListener(mOnCancelListener);
        } else {
            this.setCancelable(isCancelable());
        }
        return dialog;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mIsDissmissOnPause) {
            dismissAllowingStateLoss();
        }
    }
}
