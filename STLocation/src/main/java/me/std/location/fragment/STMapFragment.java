package me.std.location.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.std.base.base.STFragment;
import me.std.base.core.ActionBar;
import me.std.location.R;

/**
 * Description:
 * Author: lixiao
 * Create on: 2020-02-26.
 */
public class STMapFragment extends STFragment {

    @Override
    protected void onActionBar(ActionBar.Builder builder) {
        builder.setTitle("行踪")
                .build();
    }

    @Override
    protected View onCreateLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, null);
    }
}
