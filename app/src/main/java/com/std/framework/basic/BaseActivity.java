package com.std.framework.basic;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.std.framework.comm.clazz.STDActivityManager;
import com.std.framework.comm.clazz.STDFragmentManager;

import java.util.ArrayList;
import java.util.List;

import me.std.common.config.RuntimePermissions;

public abstract class BaseActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 0x1000;

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
        permissionRequest();
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
                return true;
        }
        return true;
    }

    private void permissionRequest() {
        String[] permissions = {
                RuntimePermissions.WRITE_EXTERNAL_STORAGE,
                RuntimePermissions.READ_EXTERNAL_STORAGE,
                RuntimePermissions.CALL_PHONE
        };
        ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        List<String> denied = new ArrayList<>();
        int count = grantResults.length;
        for (int i = 0; i < count; i++) {
            //权限被拒
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                denied.add(permissions[i]);
            }
        }

        if (denied.size() > 0) {
            this.onPermissionDenied(denied, requestCode);
        } else {
            this.onPermissionGranted(requestCode);
        }

    }

    /**
     * 请求权限被允许执行方法
     */
    protected void onPermissionGranted(int requestCode) {

    }

    /**
     * 请求权限被拒绝执行方法
     */
    protected void onPermissionDenied(List<String> permissions, int requestCode) {

    }
}
