package com.std.framework.business.call.presenter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.library.util.LibUtil;
import com.std.framework.business.call.contract.VoiceRecordContract;
import com.std.framework.core.GlobalConfig;
import com.std.framework.core.SUFIX;
import com.std.framework.core.VoicePlayHelper;
import com.std.framework.core.VoiceRecordHelper;
import com.std.framework.util.ToastUtil;

import java.io.File;
import java.io.IOException;

/**
 * Description:
 * Created by 李晓 ON 2017/2/27.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader:李晓
 */
public class VoiceRecordPresenter implements VoiceRecordContract.Presenter {

    private String mVoiceName = "";

    private File mVoiceFile;

    private VoiceRecordHelper mVoiceHelper;

    private VoicePlayHelper mVoicePlayHelper;

    private VoiceRecordContract.View view;

    public VoiceRecordPresenter(VoiceRecordContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    public void onDestroyPresenter() {
        this.view = null;
        if (mVoiceHelper != null) {
            mVoiceHelper = null;
        }
        if (mVoicePlayHelper != null) {
            unRegisterSensor();
            mVoicePlayHelper.stopVoice();
            mVoicePlayHelper = null;
        }
    }

    @Override
    public void startRecord() {
        if (!LibUtil.isExternalStorageAvailable()) {
            ToastUtil.show("SD 不存在！");
        } else {
            try {
                recordVoice();
            } catch (IOException e) {
            }
        }

    }

    private void recordVoice() throws IOException {
        // TODO: 停止其他占用音频的地方
        if (mVoiceHelper == null) {
            mVoiceHelper = new VoiceRecordHelper((Activity) view.getContext());
        }
        long mStartVoiceTime = System.currentTimeMillis();
        mVoiceName = GlobalConfig.VOICE_DIR + "/" + mStartVoiceTime
                + SUFIX.AMR;
        mVoiceFile = new File(mVoiceName);
        if (!mVoiceFile.exists()) {
            mVoiceFile.getParentFile().mkdirs();
        }
        mVoiceFile.createNewFile();
        mVoiceHelper.startVoiceRecord(mVoiceName);
    }

    @Override
    public void stopRecord() {
        if (null == mVoiceHelper) {
            return;
        }
        mVoiceHelper.stopVoiceRecord(false, null);
        if (new File(mVoiceName).exists() && new File(mVoiceName).length() > 0) {
            //文件长度大于0时,才录制成功
            view.saveEnable(true);
        } else {
            //录制失败  停止录音并删除文件
            mVoiceHelper.stopVoiceRecord(true, mVoiceName);
            view.saveEnable(false);
        }
    }

    @Override
    public void play() {
        if (mVoicePlayHelper == null) {
            mVoicePlayHelper = new VoicePlayHelper((Activity) view.getContext());
            registerSensor();
        }
        playVoice(mVoiceName);
    }

    @Override
    public void pause() {
        if (mVoicePlayHelper != null) {
//            mVoicePlayHelper.pause();
        }
    }

    @Override
    public void resume() {
        if (mVoicePlayHelper != null) {
//            mVoicePlayHelper.resume();
        }
    }

    @Override
    public void reset() {
        //TODO: 删除录音文件,充重置view状态
        stopVoicePlay();
        pause();
        if (mVoiceFile.exists()) {
            mVoiceFile.delete();
        }
    }

    @Override
    public void upload() {

    }

    public void registerSensor() {
        if (mVoicePlayHelper != null) {
            mVoicePlayHelper.registerListener();
        }
    }

    public void unRegisterSensor() {
        if (mVoicePlayHelper != null) {
            mVoicePlayHelper.unRegisterListener();
        }
    }

    private void playVoice(String voicePath) {
        if (!TextUtils.isEmpty(voicePath)) {
            mVoicePlayHelper.setOnVoiceFinishListener(new VoicePlayHelper.OnVoiceFinishListener() {
                @Override
                public void onFinish() {
                    //TODO: update ui status
//                    view.updateViewStatus(2);
                }
            });
            mVoicePlayHelper.startVoice(voicePath);
        }
    }

    private void stopVoicePlay() {
        if (mVoicePlayHelper != null) {
            mVoicePlayHelper.stopVoice();
        }
    }


    @Override
    public void init(Intent data) {

    }

    @Override
    public void destroy() {

    }
}
