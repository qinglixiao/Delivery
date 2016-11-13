package com.std.framework.business.rich.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/10/27.
 * Modify by：lx
 */
public class ListViewAdapter extends BaseAdapter {
    private List<View> views;
    private Context context;

    public ListViewAdapter(Context context){
        this.context = context;
        views = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public Object getItem(int position) {
        return views.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return views.get(position);
    }

    public void addEdit(EditText editText){
        views.add(editText);
        notifyDataSetChanged();
    }

    public void addImage(ImageView imageView){
        views.add(imageView);
        notifyDataSetChanged();
    }

    public void addImage(List<ImageView> imageViews){
        if(imageViews != null && imageViews.size() != 0){
            views.addAll(imageViews);
            notifyDataSetChanged();
        }
    }
}
