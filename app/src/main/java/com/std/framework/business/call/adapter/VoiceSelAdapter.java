package com.std.framework.business.call.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.R;
import com.std.framework.comm.clazz.RecyclerHolder;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/9/6
 * Modify by：lx
 */
public class VoiceSelAdapter extends RecyclerView.Adapter<RecyclerHolder> {
    private Context context;

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerHolder(View.inflate(context, R.layout.item_view_record, null));
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
