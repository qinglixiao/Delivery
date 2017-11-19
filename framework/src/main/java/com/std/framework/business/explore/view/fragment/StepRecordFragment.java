package com.std.framework.business.explore.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.std.framework.R;
import com.std.framework.basic.BaseFragment;
import com.std.framework.core.NavigationBar;

/**
 * Description :
 * Author:       lx
 * Create on:  2017/5/8.
 * Modify by：lx
 */
public class StepRecordFragment extends BaseFragment {
    private TextView tv_device;


    @Override
    protected void onNavigationBar(NavigationBar navigationBar) {
        navigationBar.setTitle("android 计步");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_record, null);
        tv_device = (TextView) view.findViewById(R.id.tv_device_info);
        return view;
    }

}
