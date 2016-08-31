package com.std.framework.business.find.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.R;
import com.std.framework.basic.BaseFragment;
import com.std.framework.core.NavigationBar;
import com.std.framework.databinding.BindingCustomerView;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/8/31
 * Modify by：lx
 */
public class CustomerViewFragment extends BaseFragment {
    private BindingCustomerView bindingCustomerView;

    @Override
    public void onNavigationBar(NavigationBar navigationBar) {
        navigationBar.setTitle("自定义控件");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_view, null);
        bindingCustomerView = DataBindingUtil.bind(view);
        return view;
    }
}
