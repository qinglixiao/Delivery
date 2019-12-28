package me.std.base.view;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import me.std.base.R;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/23.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class EmptyView extends LinearLayout implements View.OnClickListener {
    private ViewGroup view;
    private ImageView mImgEmpty;
    private TextView mHintView;
    private OnEmptyViewClick mViewClick;

    public EmptyView(Context context) {
        super(context);
        init();
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setOnViewClick(OnEmptyViewClick onViewClick) {
        this.mViewClick = onViewClick;
    }

    private void init() {
        view = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.empty_view, this);
        mImgEmpty = view.findViewById(R.id.img_empty);
        mHintView = view.findViewById(R.id.tv_tip);
        mImgEmpty.setOnClickListener(this);
    }

    public void showHint(String hint) {
        mHintView.setText(hint);
    }

    @Override
    public void onClick(View v) {
        if (mViewClick != null) {
            mViewClick.onClick(v);
        }
    }

    public interface OnEmptyViewClick {
        void onClick(View view);
    }
}
