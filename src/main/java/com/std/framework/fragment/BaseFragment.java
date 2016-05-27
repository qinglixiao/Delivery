package com.std.framework.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.activity.BaseTitleActivity;
import com.std.framework.core.NavigationBar;

public class BaseFragment extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置具有操作主activity标题栏权限
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        if (getActivity() instanceof BaseTitleActivity) {
            onNavigationBar(((BaseTitleActivity) getActivity()).getNavigationBar());
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void onNavigationBar(NavigationBar navigationBar) {

    }

}
