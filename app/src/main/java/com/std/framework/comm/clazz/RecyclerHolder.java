package com.std.framework.comm.clazz;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/9/6
 * Modify by：lx
 */
public class RecyclerHolder extends RecyclerView.ViewHolder {
    public RecyclerHolder(View itemView) {
        super(itemView);
    }

    public <T extends View> T getView(int id) {
        return ViewHolder.getView(itemView, id);
    }
}
