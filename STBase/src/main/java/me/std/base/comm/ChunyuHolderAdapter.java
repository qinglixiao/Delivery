package me.std.base.comm;

import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import me.std.base.R;
import me.std.base.base.BaseViewHolder;
import me.std.base.base.ChunyuListAdapter;
import me.std.common.core.Reflect;
import me.std.common.exception.ReflectException;
import me.std.common.utils.Logger;

/**
 * Description:
 * Author: lixiao
 * Create on: 2019/1/15.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class ChunyuHolderAdapter<T> extends ChunyuListAdapter<T> {
    //单个Holder Type
    private static final String SINGLEHOLDERDATATYPE = "singleholderdatatype";

    private AtomicInteger mAomicInteger = new AtomicInteger(0);

    /**
     * view类型ViewHolder对应关系
     * Map<DataType,Pair<ViewType,Holder>
     * <p>
     * DataType:一般与dataset集合某个字段值关联
     * ViewType:自动生成值
     * <p>
     * 当有多个Holder时通过接口{@link HolderTypeKeyGenerater} 赋值
     */
    private Map<Object, Pair<Integer, Class<? extends BaseViewHolder>>> mTypesHolderMapping = new HashMap<>();

    private HolderTypeKeyGenerater mHolderTypeKeyGenerater;


    public ChunyuHolderAdapter(Context context) {
        super(context);
    }

    /**
     * 设置当前Holder，仅支持一种类型
     * （会清空Holder集合）
     *
     * @param holder
     */
    public void setHolder(Class<? extends BaseViewHolder> holder) {
        mTypesHolderMapping.clear();
        addHolder(SINGLEHOLDERDATATYPE, holder);
    }

    public void addHolder(Object dataType, Class<? extends BaseViewHolder> holder) {
        if (dataType == null || holder == null) {
            return;
        }
        Pair pair = new Pair(mAomicInteger.getAndIncrement(), holder);
        mTypesHolderMapping.put(dataType, pair);
    }

    @Override
    public int getViewTypeCount() {
        return mTypesHolderMapping.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object typeKey = getTypeOfKey(position);
        if (typeKey != null) {
            Pair pair = mTypesHolderMapping.get(typeKey);
            if (pair != null) {
                return (int) pair.first;
            }
        }
        if (mTypesHolderMapping.size() > 0 && mTypesHolderMapping.containsKey(SINGLEHOLDERDATATYPE)) {
            Pair pair = mTypesHolderMapping.get(SINGLEHOLDERDATATYPE);
            return (int) pair.first;
        }
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Object dataType = getTypeOfKey(position);
        Pair<Integer, Class<? extends BaseViewHolder>> mapping = mTypesHolderMapping.get(dataType);
        if (mapping != null && mapping.second != null) {
            BaseViewHolder holder = null;
            if (convertView != null) {
                holder = (BaseViewHolder) convertView.getTag(R.id.item_holder_cache);
            }

            if (convertView == null || holder == null) {
                try {
                    convertView = Reflect.on(mTypesHolderMapping.get(dataType).second)
                            .create()
                            .call("inflater", getContext(), parent)
                            .get();
                } catch (ReflectException e) {
                    Logger.e("reflect", e.getMessage());
                }
            }

            if (convertView != null) {
                holder = (BaseViewHolder) convertView.getTag(R.id.item_holder_cache);
                holder.onBind(getItem(position), getContext());
            }
        }
        return convertView;
    }

    public void setHolderTypeKeyGenerater(HolderTypeKeyGenerater holderTypeKeyGenerater) {
        this.mHolderTypeKeyGenerater = holderTypeKeyGenerater;
    }

    protected Object getTypeOfKey(int position) {
        if (mHolderTypeKeyGenerater == null) {
            return SINGLEHOLDERDATATYPE;
        } else {
            return mHolderTypeKeyGenerater.getTypeOfKey(position);
        }
    }

    public interface HolderTypeKeyGenerater {
        Object getTypeOfKey(int position);
    }
}
