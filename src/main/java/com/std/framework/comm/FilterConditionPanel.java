package com.std.framework.comm;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;

import com.std.framework.R;

/**
 * Created by gfy on 2015/12/30.
 */
public class FilterConditionPanel extends PopupWindow {
    private static final int WIDTH = 290;
    private static final int HEIGHT = 529;

    private static float width;
    private static float height;

    private static int screen_width;
    private static int screen_height;

    private float offset_x = 0;

    private View arrow;
    private ExpandableListView listView;
    private View clearView;
    private ViewGroup container;
    private Context context;

    public FilterConditionPanel(Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init() {
        View view = View.inflate(context, R.layout.pop_filter_panel, null);
        arrow = view.findViewById(R.id.top_arrow);
        listView = (ExpandableListView) view.findViewById(R.id.lst_filter);
        container = (ViewGroup) view.findViewById(R.id.filter_container);
        clearView = view.findViewById(R.id.tv_filter_clear);
        screenSampling();
        measureView(arrow);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new ColorDrawable(0x000000));
        setOutsideTouchable(true);
        setFocusable(true);
        //防止虚拟按键遮挡内容
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        setAnimationStyle(R.style.condition_filter_pop);
        setContentView(view);
    }

    public ExpandableListView getListView() {
        return listView;
    }

    public ViewGroup getContainer() {
        return container;
    }

    public View getCleanButton() {
        return clearView;
    }

    @Override
    public void showAsDropDown(View anchor) {
        int window_x = getWindow_X(anchor);
        if (window_x >= screen_width / 2) {
            offset_x = screen_width - window_x - width;
        } else {
            offset_x = -window_x;
        }
        arrow.setX(anchor.getWidth() / 2f - offset_x - arrow.getMeasuredWidth() / 2f);
        super.showAsDropDown(anchor);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        xoff = (int) (offset_x != 0 ? offset_x : xoff);
        setWindowGray(0.6f);
        super.showAsDropDown(anchor, xoff, yoff);
    }

    private int getWindow_X(View view) {
        int[] viewLocation = new int[2];
        view.getLocationInWindow(viewLocation);
        return viewLocation[0];
    }

    private void screenSampling() {
        width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, WIDTH, context.getResources().getDisplayMetrics());
        height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, HEIGHT, context.getResources().getDisplayMetrics());
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        screen_width = dm.widthPixels;
        screen_height = dm.heightPixels;
    }

    private void measureView(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
    }

    private void setWindowGray(float alpha) {
        WindowManager.LayoutParams params =((Activity) context).getWindow().getAttributes();
        params.alpha = alpha;
        ((Activity) context).getWindow().setAttributes(params);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        setWindowGray(1);
    }
}
