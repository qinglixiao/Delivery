package com.std.framework.comm.clazz;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

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
