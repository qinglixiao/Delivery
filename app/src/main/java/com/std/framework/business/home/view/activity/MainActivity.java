package com.std.framework.business.home.view.activity;

import android.os.Bundle;

import com.std.framework.R;

import me.std.base.base.STActivity;
import me.std.base.core.ActionBar;

public class MainActivity extends STActivity {
    private static final String TAG = "LX";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActionBar(ActionBar.Builder builder) {
        builder.setTitle(R.string.app_name);
    }

}
