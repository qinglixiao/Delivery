package me.std.common.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import me.std.common.R;

/**
 * Description:
 * Author: huangyuan
 * Create on: 2018/08/17
 * Job number: 1800829
 * Phone: 13120112517
 * Email: huangyuan@chunyu.me
 */
public class ToastUtil {

    private Toast mToast;

    private Context mContext;

    private static WeakReference<ToastUtil> mInstance;

    private ToastUtil() {
        mContext = AppContextUtil.getAppContext();
    }

    public static ToastUtil getInstance() {
        if (mInstance == null || mInstance.get() == null) {
            mInstance = new WeakReference(new ToastUtil());
        }
        return mInstance.get();
    }

    public synchronized static void destroyInstance() {
        mInstance.clear();
        mInstance = null;
    }

    public void showToast(String text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    public void showToast(int resId) {
        showToast(mContext.getString(resId), Toast.LENGTH_SHORT);
    }

    public void showToast(int resId, int duration) {
        showToast(mContext.getString(resId), duration);
    }

    public void showToast(String text, int duration) {
        showToast(text, duration, Gravity.BOTTOM, 0, 0, Gravity.NO_GRAVITY, 0);
    }

    public void showToast(String text, int duration, int toastGravity) {
        showToast(text, duration, toastGravity, 0, 0, Gravity.NO_GRAVITY, 0);
    }

    public void showToast(String text, int duration, int toastGravity, int toastXOffset, int toastYOffset) {
        showToast(text, duration, toastGravity, toastXOffset, toastYOffset, Gravity.NO_GRAVITY, 0);
    }

    public void showToast(String text, int duration, int toastGravity, int icon) {
        showToast(text, duration, toastGravity, 0, 0, Gravity.NO_GRAVITY, icon);
    }

    /**
     * 展示toast,832统一了toast的展示
     * 如果当前toast正在展示，又点击了toast，则只更改展示内容，不重新生成toast
     *
     * @param text           toast内容
     * @param duration       展示时长
     * @param toastGravity   toast的展示位置, refrence {@link Toast#setGravity(int, int, int)}
     * @param toastXOffset   toast show x offset, refrence {@link Toast#setGravity(int, int, int)}
     * @param toastYOffset   toast show y offset, refrence {@link Toast#setGravity(int, int, int)}
     * @param contentGravity toast文字内容的位置
     */
    public void showToast(final String text, final int duration, final int toastGravity,
                          int toastXOffset, int toastYOffset, final int contentGravity, final int icon) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(mContext, text, duration);
        setToastView(text, toastGravity, toastXOffset, toastYOffset, contentGravity, icon);
        mToast.show();
    }

    private void setToastView(String text, int toastGravity, int toastXOffset, int toastYOffset,
                              int contentGravity, int icon) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //toast自定义布局
        View mToastView = inflater.inflate(R.layout.toast_layout, null);
        //toast要展示的文字内容
        TextView mToastContent = mToastView.findViewById(R.id.toast_tip);
        //toast提示的icon，默认不展示
        ImageView mToastIcon = mToastView.findViewById(R.id.toast_status_icon);

        mToast.setGravity(toastGravity, toastXOffset, toastYOffset);
        mToastContent.setGravity(contentGravity);
        mToastContent.setText(text);
        if (icon > 0) {
            //默认不展示状态图标
            mToastIcon.setImageResource(icon);
            mToastIcon.setVisibility(View.VISIBLE);
        } else {
            mToastIcon.setVisibility(View.GONE);
        }
        mToast.setView(mToastView);
    }

    /**
     * 取消toast展示
     * 通常在当前页面销毁时调用
     */
    public void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

}
