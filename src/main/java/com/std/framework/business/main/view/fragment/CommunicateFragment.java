package com.std.framework.business.main.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.std.framework.R;
import com.std.framework.basic.BaseFragment;
import com.std.framework.core.NavigationBar;
import com.std.framework.databinding.FragmentCommunicateBinding;

public class CommunicateFragment extends BaseFragment {
    private FragmentCommunicateBinding fragmentCommunicateBinding;

    @Override
    public void onNavigationBar(NavigationBar navigationBar) {
        navigationBar.setTitle(R.string.main_tab_communicate);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_communicate, null);
        fragmentCommunicateBinding = DataBindingUtil.bind(view);
        return view;
    }


}
