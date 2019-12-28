package me.std.base.comm;

import android.util.SparseArray;
import android.view.View;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/29
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class ViewHolder {
    /**
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
