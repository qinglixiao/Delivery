package com.std.framework.comm.interfaces;

import android.view.View;
import android.widget.TextView;

public interface INavigation {
    View getContainer();

    void setTitle(String title);

    TextView getTitle();
}
