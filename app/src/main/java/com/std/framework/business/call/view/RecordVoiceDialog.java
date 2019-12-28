package com.std.framework.business.call.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.std.framework.R;
import com.std.framework.comm.view.BottomPopContainer;
import com.std.framework.core.GlobalConfig;
import com.std.framework.core.SUFIX;
import com.std.framework.core.VoiceRecordHelper;
import com.std.framework.databinding.RecordVoiceBinding;

import java.io.File;

import me.std.common.utils.FileUtil;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/11/28.
 * Modify by：lx
 */
public class RecordVoiceDialog implements View.OnClickListener {
    private static final int RECORDTIME = 10 * 1000; //录音时长

    private RecordVoiceBinding recordVoiceBinding;
    private BottomPopContainer popWindow;
    private Context context;
    private VoiceRecordHelper recordHelper;
    private File recordFile;

    public RecordVoiceDialog(Context context) {
        this.context = context;
        initView();
        initRecord();
    }

    private void initView() {
        popWindow = new BottomPopContainer(context, R.layout.view_record_voice);
        recordVoiceBinding = DataBindingUtil.bind(popWindow.getView());
        recordVoiceBinding.btnStart.setOnClickListener(this);
        recordVoiceBinding.btnStop.setOnClickListener(this);
    }

    private void initRecord() {
        recordHelper = new VoiceRecordHelper(context);
    }

    public void show() {
        popWindow.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                beginRecording();
                break;
            case R.id.btn_stop:
                stopRecording();
                break;
        }
    }

    private void beginRecording() {
        //创建本地文件
        String voiceName = generateName();
        recordFile = FileUtil.newFile(voiceName);
        if (recordFile != null) {
            recordHelper.startVoiceRecord(voiceName);
        }
    }

    private void stopRecording() {
        recordHelper.stopVoiceRecord(false, null);
    }

    private String generateName() {
        return GlobalConfig.VOICE_DIR + System.currentTimeMillis() + SUFIX.AMR;
    }

    private void tick(){

    }

}
