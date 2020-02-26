package com.std.framework.core;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.library.core.God;

import me.std.base.base.BaseFragment;

/**
 * Created by gfy on 2016/3/31.
 */
public class FragmentManufacture extends Fragment{

    /**
     * 创建一个fragment
     * @param bundle
     * @param clazz
     * @return
     */
    public static BaseFragment make(Bundle bundle, Class<? extends BaseFragment> clazz){
        BaseFragment fragment = God.love(clazz);
        fragment.setArguments(bundle);
        return fragment;
    }
}
