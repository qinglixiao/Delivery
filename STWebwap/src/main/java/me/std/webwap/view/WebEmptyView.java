package me.std.webwap.view;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import me.std.webwap.R;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/23.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class WebEmptyView extends LinearLayout {
    private ViewGroup view;
    private ProgressBar circleProgress;
    private ImageView imgFaile;
    private TextView tipView;

    public WebEmptyView(Context context) {
        super(context);
    }

    public WebEmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WebEmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        view = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.web_empty_view, this);
//        circleProgress = view.findViewById(R.id.loading_progress_circle);
//        imgFaile = view.findViewById(R.id.loading_fail);
//        tipView = view.findViewById(R.id.loading_tip);
    }

    public void showError(String error,@DrawableRes int drawableId){
        tipView.setText(error);
        imgFaile.setImageResource(drawableId);
    }
}
