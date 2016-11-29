package com.std.framework.basic;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.std.framework.core.NavigationBar;

public abstract class BaseFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置具有操作主activity标题栏权限
        setHasOptionsMenu(true);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (getActivity() instanceof BaseTitleActivity) {
                NavigationBar navigationBar = ((BaseTitleActivity) getActivity()).getNavigationBar();
                navigationBar.resetMenu();
                onNavigationBar(navigationBar);
            }
            loadData();
        }
    }

    /**
     * 自定义title
     *
     * @param navigationBar
     */
    protected void onNavigationBar(NavigationBar navigationBar) {
    }

    /**
     * 加载数据
     */
    protected void loadData() {
    }

}
