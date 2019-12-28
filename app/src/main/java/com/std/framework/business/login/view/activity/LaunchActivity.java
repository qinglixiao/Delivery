package com.std.framework.business.login.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.std.framework.R;
import com.std.framework.basic.BaseActivity;
import com.std.framework.business.login.view.fragment.GuideFragment;
import com.std.framework.business.home.view.activity.MainTabActivity;
import com.std.framework.comm.clazz.STDActivityManager;
import com.std.framework.comm.clazz.STDFragmentManager;
import com.std.framework.core.Logger;
import com.std.framework.util.SharedPreferencesUtil;

import me.std.common.core.ThreadPool;

public class LaunchActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        setContentView(R.layout.launch);
        if (SharedPreferencesUtil.isStartFirst()) {
            STDFragmentManager.getInstance(this).add(R.id.guide_content, new GuideFragment());
            SharedPreferencesUtil.putStartSign();
        } else
            ThreadPool.postDelay(waiting, 1000);
        Logger.d("LX","LaunchActivity-onCreate");
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
//        overridePendingTransition(R.anim.flat_in,R.anim.zoom_out);
    }

}
