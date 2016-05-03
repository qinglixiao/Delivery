package com.std.framework.view;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

/**
 * Created by gfy on 2016/5/2.
 */
public class MToast extends android.widget.Toast {
    /**
     * Construct an empty MToast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public MToast(Context context) {
        super(context);
    }
}
