package me.std.common.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import me.std.common.R;

/**
 * 继承自android.app.AlertDialog的自定义弹窗
 * <p/>
 * 在使用ViewPager时（例如首页），使用DialogFragment做弹窗会和ViewPager中的Fragment创建冲突，导致崩溃
 *
 * @author michael created on 15/11/25.
 */
public class AlertDialog extends android.app.AlertDialog implements View.OnClickListener {

    private String mTitle;

    private String mMessage;
    private int mMsgGravity = Gravity.LEFT;

    private String mLeftButtonText;

    private String mRightButtonText;

    private OnClickListener onButtonClickListener;

    public static final int LEFT_BUTTON = 0;

    public static final int RIGHT_BUTTON = 1;

    public AlertDialog(Activity context) {
        super(context, R.style.Widget_Dialog_Theme_Floating);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        setContentView(R.layout.dialog_alert_new);
        TextView titleView = (TextView) this.findViewById(R.id.dialog_title);
        if (!TextUtils.isEmpty(mTitle)) {
            titleView.setText(mTitle);
            titleView.setVisibility(View.VISIBLE);
        } else {
            titleView.setVisibility(View.GONE);
        }

        //设置提示内容
        TextView msgView = (TextView) this.findViewById(R.id.dialog_message);
        if (mMsgGravity > 0) {
            msgView.setGravity(mMsgGravity);
        }
        if (!TextUtils.isEmpty(mMessage)) {
            msgView.setText(mMessage);
            msgView.setVisibility(View.VISIBLE);
        } else {
            msgView.setVisibility(View.GONE);
        }

        //设置取消按钮
        TextView leftButton = (TextView) this.findViewById(R.id.negative_button);
        if (!TextUtils.isEmpty(mLeftButtonText)) {
            leftButton.setText(mLeftButtonText);
            leftButton.setVisibility(View.VISIBLE);
            leftButton.setOnClickListener(this);
        } else {
            leftButton.setVisibility(View.GONE);
        }

        TextView rightButton = (TextView) this.findViewById(R.id.positive_button);
        if (!TextUtils.isEmpty(mRightButtonText)) {
            rightButton.setText(mRightButtonText);
            rightButton.setVisibility(View.VISIBLE);
            rightButton.setOnClickListener(this);
        } else {
            rightButton.setVisibility(View.GONE);
        }
    }

    public AlertDialog setTitle(String title) {
        mTitle = title;
        return this;
    }

    public AlertDialog setMessage(String message) {
        mMessage = message;
        return this;
    }

    public AlertDialog setMsgGravity(int gravity) {
        mMsgGravity = gravity;
        return this;
    }

    public AlertDialog setButtons(String right) {
        return setButtons(right, null);
    }

    public AlertDialog setButtons(String right, String left) {
        mLeftButtonText = left;
        mRightButtonText = right;
        return this;
    }

    /**
     * which = 0 when click right button
     */
    public AlertDialog setOnButtonClickListener(OnClickListener listener) {
        onButtonClickListener = listener;
        return this;
    }

    public AlertDialog setCanCancel(boolean canCancel) {
        super.setCancelable(canCancel);
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.negative_button) {
            dismiss();
            if (onButtonClickListener != null) {
                onButtonClickListener.onClick(this, LEFT_BUTTON);
            }
        } else {
            dismiss();
            if (onButtonClickListener != null) {
                onButtonClickListener.onClick(this, RIGHT_BUTTON);
            }
        }
    }

}
