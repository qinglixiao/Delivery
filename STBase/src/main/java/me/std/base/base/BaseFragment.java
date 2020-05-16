package me.std.base.base;

import androidx.fragment.app.Fragment;

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
     * 注：只要当前Fragment可见状态时才会调用
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

    public void onBackPressed() {

    }

}
