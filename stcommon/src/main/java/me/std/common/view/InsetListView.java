package me.std.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Description:重载了onMeasure函数，可以嵌套到ScrollView里
 * Author: zhangjian
 * Create on: 2018/8/17
 * Job number: 1800802
 * Phone: 18310617891
 * Email: zhangjian@chunyu.me
 */
public class InsetListView extends ListView {
    public InsetListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InsetListView(Context context) {
        super(context);
    }

    public InsetListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
