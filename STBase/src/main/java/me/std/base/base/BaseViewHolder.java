package me.std.base.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import me.std.base.R;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/12/17.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public abstract class BaseViewHolder<T> {
    public View itemView;

    public View inflater(Context context, ViewGroup parent) {
        itemView = LayoutInflater.from(context).inflate(getLayoutId(), null);
        ButterKnife.bind(this, itemView);
        itemView.setTag(R.id.item_holder_cache, this);
        return itemView;
    }

    public abstract int getLayoutId();

    public abstract void onBind(T data, Context context);
}
