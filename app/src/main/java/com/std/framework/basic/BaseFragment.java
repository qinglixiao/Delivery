package com.std.framework.basic;

import android.support.v4.app.Fragment;

import java.util.List;

public abstract class BaseFragment extends Fragment {

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            loadData();
        }
    }

    /**
     * 加载数据
     */
    protected void loadData() {
    }

    /**
     * 请求权限被允许执行方法
     */
    public void onPermissionGranted(int requestCode) {

    }

    /**
     * 请求权限被拒绝执行方法
     */
    public void onPermissionDenied(List<String> permissions, int requestCode) {

    }

}
