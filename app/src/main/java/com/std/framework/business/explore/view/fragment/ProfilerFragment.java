package com.std.framework.business.explore.view.fragment;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.R;
import com.std.framework.databinding.FragmentProfilerBinding;

import me.std.base.base.STFragment;
import me.std.base.core.ActionBar;


public class ProfilerFragment extends STFragment implements View.OnClickListener{
    private FragmentProfilerBinding profilerBinding;

    @Override
    protected void onActionBar(ActionBar.Builder builder) {
        builder.setTitle("profiler analysis");
    }

    @Override
    protected View onCreateLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profiler, null);
        profilerBinding = DataBindingUtil.bind(view);
        viewAction();
        return view;
    }

    private void viewAction(){
        profilerBinding.btnCall.setOnClickListener(this);
    }

    private Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                Math.cos(30);
            }
        }
    };

    public void methodA() {
        for (int i = 0; i < 1000; i++) {
            int j = 0;
            j++;
        }
        methodB("methodA->methodB");
        methodC("methodA->methodC");
    }

    private void methodB(String name) {
        Thread th = new Thread(runnable1, name);
        th.start();
        methodC("methodB->methodC");
    }

    private void methodC(String name) {
        Thread th = new Thread(runnable2, name);
        th.start();
    }

    @Override
    public void onClick(View v) {
        methodA();
    }


}
