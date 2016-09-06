package com.std.framework.comm.clazz;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by gfy on 2016/4/27.
 */
public class ViewHolder {
    /**
     *
     * @param view view
     * @param id   id
     * @return T 返回类型
     */
    @SuppressWarnings("unchecked")
    public static <T extends View> T getView(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
