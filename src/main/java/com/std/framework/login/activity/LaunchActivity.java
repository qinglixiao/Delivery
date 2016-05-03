package com.std.framework.login.activity;

import android.content.Intent;
import android.os.Bundle;

import com.library.util.ThreadPool;
import com.std.framework.R;
import com.std.framework.activity.BaseActivity;
import com.std.framework.main.activity.MainActivity;
import com.std.framework.comm.STDActivityManager;
import com.std.framework.comm.STDFragmentManager;
import com.std.framework.login.fragment.GuideFragment;
import com.std.framework.util.SharedPreferencesUtil;

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
            ThreadPool.postOnUiThreadDelayed(waiting, 1000);
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
        Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
