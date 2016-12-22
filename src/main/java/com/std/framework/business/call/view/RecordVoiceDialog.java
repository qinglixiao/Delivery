package com.std.framework.business.call.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.CountDownTimer;
import android.view.View;

import com.std.framework.R;
import com.std.framework.comm.view.BottomPopContainer;
import com.std.framework.core.GlobalConfig;
import com.std.framework.core.SUFFIX;
import com.std.framework.core.VoiceRecordHelper;
import com.std.framework.util.TimeUtil;

import java.text.SimpleDateFormat;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/11/28.
 * Modify by：lx
 */
public class RecordVoiceDialog implements View.OnClickListener {
    private static final int RECORDTIME = 10 * 1000; //录音时长

    private com.std.framework.databinding.RecordVoiceBinding recordVoiceBinding;
    private BottomPopContainer popWindow;
    private Context context;
    private VoiceRecordHelper recordHelper;

    public RecordVoiceDialog(Context context) {
        this.context = context;
        initView();
        initRecord();
    }

    private void initView() {
        popWindow = new BottomPopContainer(context, R.layout.view_record_voice);
        recordVoiceBinding = DataBindingUtil.bind(popWindow.getView());
        recordVoiceBinding.btnStart.setOnClickListener(this);
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
                recordHelper.startVoiceRecord(generateName());
                beginTimer();
                break;
        }
    }

    private String generateName() {
        return GlobalConfig.VOICE_DIR + TimeUtil.milliseconds2String(System.currentTimeMillis()) + SUFFIX.AMR;
    }

    private void beginTimer() {
        new CountDownTimer(RECORDTIME, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                recordVoiceBinding.vRecordTime.setText(TimeUtil.milliseconds2String(millisUntilFinished, new SimpleDateFormat("mm:ss")) + "");
            }

            @Override
            public void onFinish() {
                recordVoiceBinding.vRecordTime.setText("录音结束！");
            }
        }.start();
    }
}
