package com.std.framework.business.explore.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.R;
import com.std.framework.basic.BaseFragment;
import com.std.framework.business.explore.adapter.RecyclerViewAdapter;
import com.std.framework.databinding.RecyclerViewBinding;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/9/27.
 * Modify by：lx
 */
public class RecyclerViewFragment extends BaseFragment {
    private RecyclerViewBinding recyclerViewBinding;
    private RecyclerView recyclerView;
    private static final String[] data = new String[]{
            "北京","黑龙江","吉林","山东","江苏","河北","山西","内蒙古","新疆","西藏","云南","香港","澳门"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view,null);
        recyclerViewBinding = DataBindingUtil.bind(view);
        initView();
        initData();
        return view;
    }

    private void initView(){
        recyclerView = recyclerViewBinding.rcvResult;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData(){
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(data,getContext());
        recyclerView.setAdapter(adapter);
    }

}
