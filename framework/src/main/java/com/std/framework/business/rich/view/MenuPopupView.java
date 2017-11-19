package com.std.framework.business.rich.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.std.framework.R;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/10/27.
 * Modify by：lx
 */
public class MenuPopupView extends PopupWindow implements View.OnClickListener {
    private Context context;
    private OperateClickListener operateClickListener;

    public MenuPopupView(Context context) {
        this.context = context;
        initView();
    }

    private void initView() {
        View view = View.inflate(context, R.layout.view_more_operate, null);
        View v_text = view.findViewById(R.id.btn_text);
        View v_img = view.findViewById(R.id.btn_img);

        v_text.setOnClickListener(this);
        v_img.setOnClickListener(this);

        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new ColorDrawable(0x000000));
        setOutsideTouchable(true);
        setFocusable(true);
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(view);
    }

    public void setOperateClickListener(OperateClickListener operateClickListener) {
        this.operateClickListener = operateClickListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_text:
                if (operateClickListener != null) {
                    operateClickListener.onClickText();
                }
                break;
            case R.id.btn_img:
                if (operateClickListener != null) {
                    operateClickListener.onClickImage();
                }
                break;
        }
    }

    interface OperateClickListener {
        void onClickText();

        void onClickImage();
    }

}
