package com.std.framework.business.explore.view.fragment;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.R;

import me.std.base.base.BaseFragment;

/**
 * Description :
 * Created by lx on 2016/9/29.
 * Job number：137289
 * Phone ：18611867932
 * Email：lixiao3@syswin.com
 * Person in charge : lx
 * Leader：lx
 */
public class EffectFragment extends BaseFragment {
//    private EffectFragmentBinding effectFragmentBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_effect, container, false);
//        effectFragmentBinding = DataBindingUtil.bind(view);
        return view;
    }
}
