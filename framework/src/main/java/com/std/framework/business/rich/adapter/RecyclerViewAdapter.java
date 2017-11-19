package com.std.framework.business.rich.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.std.framework.R;
import com.std.framework.comm.clazz.RecyclerHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/10/27.
 * Modify by：lx
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerHolder> {
    private List<View> views;
    private Context context;

    public RecyclerViewAdapter(Context context){
        this.context = context;
        views = new ArrayList<>();
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.item_edit_text,parent,false));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return views.size();
    }

    public void addEdit(EditText editText){
        views.add(editText);
        notifyDataSetChanged();
    }

    public void addImage(ImageView imageView){
        views.add(imageView);
        notifyDataSetChanged();
    }
}
