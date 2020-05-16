package com.std.framework.business.explore.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.std.framework.R;
import com.std.framework.business.explore.adapter.RecyclerViewAdapter;
import com.std.framework.comm.view.RecyclerItemDivider;
import com.std.framework.databinding.FragmentRecyclerViewBinding;

import me.std.base.base.STFragment;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/9/27.
 * Modify by：lx
 */
public class RecyclerViewFragment extends STFragment {
    private FragmentRecyclerViewBinding recyclerViewBinding;
    private RecyclerView recyclerView;
    private static final String[] data = new String[]{
            "北京", "黑龙江", "吉林", "山东", "江苏", "河北", "山西", "内蒙古", "新疆", "西藏", "云南", "香港", "澳门"
    };

    @Override
    protected View onCreateLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, null);
        recyclerViewBinding = DataBindingUtil.bind(view);
        initView();
        initData();
        return view;
    }

    private void initView() {
        recyclerView = recyclerViewBinding.rcvResult;
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new RecyclerItemDivider(getActivity().getResources().getDrawable(R.color.black),RecyclerItemDivider.VERTICAL_LIST));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initData() {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(data, getContext());
        recyclerView.setAdapter(adapter);
    }

}
