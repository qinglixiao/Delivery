package me.std.base.widget.listview.view;

import android.content.Context;

/**
 * Created by Roger Huang on 2019/1/15.
 */

public abstract class CYViewHolder<T> {
    public abstract void update(T bean, Context context);
}
