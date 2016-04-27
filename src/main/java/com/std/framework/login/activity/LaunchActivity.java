package com.std.framework.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.library.util.ThreadPool;
import com.std.framework.R;
import com.std.framework.activity.BaseActivity;
import com.std.framework.activity.MainTabActivity;
import com.std.framework.comm.STDActivityManager;
import com.std.framework.login.fragment.GuideFragment;
import com.std.framework.util.SharedPreferencesUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LaunchActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        Log.d("LX", "进入启动页");
        setContentView(R.layout.launch);
        Log.d("LX", "检查是否首次启动");
        if (SharedPreferencesUtil.isStartFirst()) {
            Log.d("LX", "进入导航页");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.guide_content, new GuideFragment());
            transaction.commit();
            SharedPreferencesUtil.putStartSign();
        } else
            ThreadPool.postOnUiThreadDelayed(waiting, 1000);
        STDActivityManager.getInstance().add(this);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        STDActivityManager.getInstance().remove(this);
    }

    private Runnable waiting = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
           redirectToMain();
        }

    };

    public void redirectToMain() {
        Intent intent = new Intent(LaunchActivity.this, MainTabActivity.class);
        startActivity(intent);
        finish();
    }
}
