package me.std.base.base;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.std.base.comm.STDActivityManager;
import me.std.common.config.RuntimePermissions;
import me.std.common.utils.RuntimePermissionUtil;

/**
 * Description:提供运行时权限获取功能
 * Author: lixiao
 * Create on: 2018/11/19.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 0x1000;

    /**
     * 用于注册并释放RxJava事件流，子类凡是用到RxJava事件流的都应该将其添加到该容器里，以便统一释放资源
     */
    private CompositeDisposable mCompositeDisposable;

    public BaseActivity() {
        /**添加activity到栈*/
        STDActivityManager.getInstance().add(this);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissionRequest();
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        /**销毁栈信息*/
        STDActivityManager.getInstance().remove(this);
        super.onDestroy();
        mCompositeDisposable.dispose();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
        }
        return true;
    }

    public void permissionRequest() {
        String[] permissions = requestPermissions();
        if (permissions == null) {
            permissions = new String[]{
                    RuntimePermissions.WRITE_EXTERNAL_STORAGE,
                    RuntimePermissions.READ_EXTERNAL_STORAGE,
                    RuntimePermissions.CALL_PHONE,
                    RuntimePermissions.READ_PHONE_STATE,
                    RuntimePermissions.RECORD_AUDIO,
                    RuntimePermissions.CAMERA,
            };
        }
        RuntimePermissionUtil.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
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
     * 申请运行时权限
     * 如果没有复写该方法，将请求系统默认权限列表
     * 列表取值：
     * {@link RuntimePermissions#READ_EXTERNAL_STORAGE,...}
     *
     * @return
     */
    public String[] requestPermissions() {
        return null;
    }

    /**
     * 权限被允许执行方法
     */
    protected void onPermissionGranted(int requestCode) {

    }

    /**
     * 权限被拒绝执行方法
     */
    protected void onPermissionDenied(List<String> permissions, int requestCode) {

    }

    /**
     * 添加Rxjava事件流
     *
     * @param disposable
     */
    protected void addDisposable(Disposable disposable) {
        if (disposable != null) {
            mCompositeDisposable.add(disposable);
        }
    }

}
