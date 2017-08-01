package com.std.framework.business.explore.view.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.std.framework.R;
import com.std.framework.basic.BaseFragment;
import com.std.framework.core.NavigationBar;
import com.std.framework.util.FingerUtil;
import com.std.framework.util.ToastUtil;

/**
 * Description:
 * Created by 李晓 ON 2017/8/1.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
public class FingerIdentifyFragment extends BaseFragment {
    private View view;
    private TextView tv_device;

    @Override
    protected void onNavigationBar(NavigationBar navigationBar) {
        navigationBar.setTitle("指纹识别");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_finger, null);
        tv_device = (TextView) view.findViewById(R.id.tv_device_info);
        onFingerprintClick(tv_device);
        return view;
    }

    public void onFingerprintClick(final TextView v) {

        FingerUtil.callFingerPrint(new FingerUtil.OnCallBackListenr() {
            @Override
            public void onSupportFailed() {
                v.setText("当前设备不支持指纹");
//                showToast("当前设备不支持指纹");
            }

            @Override
            public void onInsecurity() {
                v.setText("当前设备未处于安全保护中");
//                showToast("当前设备未处于安全保护中");
            }

            @Override
            public void onEnrollFailed() {
                v.setText("请到设置中设置指纹");
//                showToast("请到设置中设置指纹");
            }

            @Override
            public void onAuthenticationStart() {
                ToastUtil.show("指纹识别中...");
            }

            @Override
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
//                showToast(errString.toString());
                ToastUtil.show("出错");
            }

            @Override
            public void onAuthenticationFailed() {
                ToastUtil.show("解锁失败");
            }

            @Override
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
//                showToast(helpString.toString());
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                ToastUtil.show("解锁成功");

            }
        });
    }
}
