package com.std.framework.interfaces;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by gfy on 2016/4/7.
 */
public interface IDbException {
    @Subscribe(threadMode = ThreadMode.MAIN)
    void onErrorShow(String message);
}
