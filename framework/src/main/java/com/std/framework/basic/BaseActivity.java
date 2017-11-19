package com.std.framework.basic;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.std.framework.comm.clazz.STDActivityManager;
import com.std.framework.comm.clazz.STDFragmentManager;

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 视图管理器
     */
    public final STDFragmentManager FragmentManager;

    public BaseActivity() {
        FragmentManager = STDFragmentManager.getInstance(this);
        /**添加activity到栈*/
        STDActivityManager.getInstance().add(this);
    }

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        /**销毁栈信息*/
        STDActivityManager.getInstance().remove(this);
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBack();
                return true;
        }
        return true;
    }

    public void onBack() {
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //权限已被允许
            onGranted(requestCode);
        } else {
            //权限被拒绝
            onDenied(requestCode);
        }
    }

    /**
     * 请求权限被允许执行方法
     */
    protected void onGranted(int requestCode) {

    }

    /**
     * 请求权限被拒绝执行方法
     */
    protected void onDenied(int requestCode) {

    }
}
