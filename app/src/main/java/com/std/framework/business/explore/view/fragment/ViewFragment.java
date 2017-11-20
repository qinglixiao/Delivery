package com.std.framework.business.explore.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.R;
import com.std.framework.basic.BaseFragment;

/**
 * Description :
 * Author:       lx
 * Create on:  2017/4/13.
 * Modify by：lx
 */
public class ViewFragment  extends BaseFragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_event_bus, null);
        return view;
    }
}
