package com.std.framework.business.call.contract;

import com.std.framework.basic.IBasePresenter;
import com.std.framework.basic.IBaseView;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/9/6
 * Modify by：lx
 */
public interface VoiceSelContract {
    interface View extends IBaseView<Presenter> {
    }

    interface Presenter extends IBasePresenter {
    }

}
