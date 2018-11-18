package com.std.framework.basic;

import com.std.framework.comm.interfaces.INavRebuild;
import com.std.framework.core.NavigationBar;

public class BaseTitleFragment extends BaseFragment {
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (getActivity() != null && getActivity() instanceof INavRebuild) {
                NavigationBar.Builder navBuilder = ((INavRebuild) getActivity()).getNavBuilder();
                navBuilder.build().reset();
                onNavigationBar(navBuilder);
            }
        }
    }

    /**
     * 定义标题栏
     *
     * @param navBuilder
     */
    protected void onNavigationBar(NavigationBar.Builder navBuilder) {
    }
}
