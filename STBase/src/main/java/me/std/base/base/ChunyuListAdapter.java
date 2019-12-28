package me.std.base.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import me.std.base.R;
import me.std.common.core.Reflect;
import me.std.common.exception.ReflectException;
import me.std.common.utils.LogUtil;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/29.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public abstract class ChunyuListAdapter<T> extends BaseAdapter {
    protected List<T> dataSet;
    private Context mContext;

    public ChunyuListAdapter() {
        this(null);
    }

    public ChunyuListAdapter(Context context) {
        dataSet = new ArrayList<>();
        mContext = context;
    }

    public void add(T data) {
        if (dataSet != null && data != null) {
            dataSet.add(data);
        }
        notifyDataSetChanged();
    }

    public void addAll(List<T> set) {
        if (dataSet != null && set != null) {
            dataSet.addAll(set);
        }
        notifyDataSetChanged();
    }

    public void setData(List<T> set) {
        dataSet = set;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (dataSet == null) {
            return 0;
        }
        return dataSet.size();
    }

    @Override
    public T getItem(int position) {
        if (position < 0 || position >= getCount()) {
            return null;
        }
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T bean = getItem(position);

        return createView(convertView,
                parent,
                bean,
                getLayoutId(position),
                getHolderClazz(position));
    }

    protected int getLayoutId(int position) {
        return -1;
    }

    protected Class getHolderClazz(int position) {
        return null;
    }

    protected View createView(View convertView, ViewGroup parent, T bean, int layoutId, Class holderClazz) {
        if (convertView == null) {
            try {
                convertView = Reflect.on(holderClazz)
                        .create()
                        .call("inflater", mContext, parent)
                        .get();
            } catch (ReflectException e) {
                LogUtil.e("reflect", e.getMessage());
            }
        }

        if (convertView != null) {
            BaseViewHolder holder = (BaseViewHolder) convertView.getTag(R.id.item_holder_cache);

            if (holder != null) {
                holder.onBind(bean, getContext());
            }
        }

        return convertView;
    }
}
