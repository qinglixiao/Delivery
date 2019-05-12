package com.std.framework.business.explore.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.std.framework.R;
import com.std.framework.basic.BaseTitleFragment;
import com.std.framework.core.NavigationBar;
import com.std.framework.databinding.BindingCustomerView;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/8/31
 * Modify by：lx
 */
public class CustomerViewFragment extends BaseTitleFragment implements View.OnClickListener {
    private BindingCustomerView bindingCustomerView;
    @Override
    public void onNavigationBar(NavigationBar.Builder navBuilder) {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_view, null);
        bindingCustomerView = DataBindingUtil.bind(view);
        bindingCustomerView.btnPop.setOnClickListener(this);
        return view;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_pop_demo, null);
        PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.showAsDropDown((View)v.getParent(), 0, -((View) v.getParent()).getHeight());
//        popupWindow.showAtLocation(v,Gravity.CENTER_VERTICAL,0,0);

        view.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }


}
