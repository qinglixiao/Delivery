package me.std.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Description:重载onMeasure函数，可以嵌套到ScrollView里的GridView
 * Author: zhangjian
 * Create on: 2018/8/16
 * Job number: 1800802
 * Phone: 18310617891
 * Email: zhangjian@chunyu.me
 */
public class InsetGridView extends GridView {
    public InsetGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InsetGridView(Context context) {
        super(context);
    }

    public InsetGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
