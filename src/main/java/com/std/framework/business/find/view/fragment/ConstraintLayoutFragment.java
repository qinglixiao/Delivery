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
import com.std.framework.databinding.ConstraintLayoutBinding;

/**
 * Description :
 * Created by lx on 2016/9/23.
 * Job number：137289
 * Phone ：18611867932
 * Email：lixiao3@syswin.com
 * Person in charge : lx
 * Leader：lx
 */
public class ConstraintLayoutFragment extends BaseFragment {
    private ConstraintLayoutBinding constraintLayoutBinding;

    @Override
    public void onNavigationBar(NavigationBar navigationBar) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_constraint, null);
        constraintLayoutBinding = DataBindingUtil.bind(view);
        return view;
    }
}
