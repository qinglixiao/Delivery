package com.std.framework.business.call.contract;

import android.content.Intent;

import com.std.framework.basic.IBasePresenter;
import com.std.framework.basic.IBaseView;

/**
 * Description:
 * Created by 李晓 ON 2017/2/27.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader:李晓
 */
public interface VoiceRecordContract {
    interface View extends IBaseView {
        void saveEnable(boolean enable);
        int getDuration();
    }

    interface Presenter extends IBasePresenter<Intent> {
        void startRecord();

        void stopRecord();

        void play();

        void pause();

        void resume();

        void reset();

        void upload();
    }
}
