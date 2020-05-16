package com.std.framework.util;

import android.app.KeyguardManager;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.core.os.CancellationSignal;

/**
 *
 * 设备条件判断

 1.设备是否支持指纹识别
 2.设备是否处于安全保护中（有指纹识别的手机，在使用指纹识别的时候，还需要强制设置密码或者图案解锁，如果未设置的话是不许使用指纹识别的）
 3.设备是否已经注册过指纹（如果用户未使用过这个指纹技术，那么只能提示用户到系统设置里面去设置）
 *
 *
 */
public class FingerUtil {
    public static CancellationSignal cancellationSignal;

    public static void callFingerPrint(final OnCallBackListenr listener) {
        FingerprintManagerCompat managerCompat = FingerprintManagerCompat.from(AppUtil.getAppContext());
//        if (!managerCompat.isHardwareDetected()) { //判断设备是否支持
//            if (listener != null)
//                listener.onSupportFailed();
//            return;
//        }
        KeyguardManager keyguardManager = (KeyguardManager) AppUtil.getAppContext().getSystemService(AppUtil.getAppContext().KEYGUARD_SERVICE);
        if (!keyguardManager.isKeyguardSecure()) {//判断设备是否处于安全保护中
            if (listener != null)
                listener.onInsecurity();
            return;
        }
        if (!managerCompat.hasEnrolledFingerprints()) { //判断设备是否已经注册过指纹
            if (listener != null)
                listener.onEnrollFailed(); //未注册
            return;
        }
        if (listener != null)
            listener.onAuthenticationStart(); //开始指纹识别
        cancellationSignal = new CancellationSignal(); //必须重新实例化，否则cancel 过一次就不能再使用了
        managerCompat.authenticate(null, 0, cancellationSignal, new FingerprintManagerCompat.AuthenticationCallback() {
            // 当出现错误的时候回调此函数，比如多次尝试都失败了的时候，errString是错误信息，比如华为的提示就是：尝试次数过多，请稍后再试。
            @Override
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                if (listener != null)
                    listener.onAuthenticationError(errMsgId, errString);
            }

            // 当指纹验证失败的时候会回调此函数，失败之后允许多次尝试，失败次数过多会停止响应一段时间然后再停止sensor的工作
            @Override
            public void onAuthenticationFailed() {
                if (listener != null)
                    listener.onAuthenticationFailed();
            }

            @Override
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                if (listener != null)
                    listener.onAuthenticationHelp(helpMsgId, helpString);
            }

            // 当验证的指纹成功时会回调此函数，然后不再监听指纹sensor
            @Override
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                if (listener != null)
                    listener.onAuthenticationSucceeded(result);
            }

            ;
        }, null);

    }

    public interface OnCallBackListenr {
        void onSupportFailed();

        void onInsecurity();

        void onEnrollFailed();

        void onAuthenticationStart();

        void onAuthenticationError(int errMsgId, CharSequence errString);

        void onAuthenticationFailed();

        void onAuthenticationHelp(int helpMsgId, CharSequence helpString);

        void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result);
    }

    public static void cancel() {
        if (cancellationSignal != null)
            cancellationSignal.cancel();
    }
}