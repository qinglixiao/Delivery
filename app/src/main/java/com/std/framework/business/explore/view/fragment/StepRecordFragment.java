package com.std.framework.business.explore.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.std.framework.R;
import com.std.framework.core.NavigationBar;

import me.std.base.base.STFragment;
import me.std.base.core.ActionBar;

/**
 * Description :
 * Author:       lx
 * Create on:  2017/5/8.
 * Modify by：lx
 */
public class StepRecordFragment extends STFragment {
    private TextView tv_device;

    @Override
    protected void onActionBar(ActionBar.Builder builder) {
        builder.setTitle("android 计步");
    }

    @Override
    protected View onCreateLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_record, null);
        tv_device = (TextView) view.findViewById(R.id.tv_device_info);
        return view;
    }

}
