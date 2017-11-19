
package com.std.framework.business.call.util;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.os.Handler;
import android.text.TextUtils;

import com.std.framework.core.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/9/6
 * Modify by：lx
 */
public class VoiceRecordUtil {
    /* 语音录制 */
    private MediaRecorder mMediaRecorder;

    /* 语音录制handler */
    private Handler handler = new Handler();

    /* 系统声音管理类 */
    private AudioManager mAudioManager;

    /* 系统声音改变监听 */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;

    public VoiceRecordUtil(Activity context){
        mAudioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        initListener();
    }

    private void initListener(){
        // 系统声音改变监听
        mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                Logger.m("Audio Focus Change=" + focusChange);
                // 暂时失去了音频焦点，但很快会重新得到焦点
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                    // 你已经得到焦点了
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    // 永久失去焦点
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                }
            }
        };
    }

    /**
     * 开始录制语音
     * @param voicePath 语音录制的路径
     */
    public void startVoiceRecord(final String voicePath) {
        if(TextUtils.isEmpty(voicePath)){
            return;
        }
        boolean isFocus = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
        if(isFocus){
            try {
                if(mMediaRecorder != null){
                    mMediaRecorder.stop();
                    mMediaRecorder.release();
                    mMediaRecorder = null;
                }
            } catch (Exception e) {
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        mMediaRecorder = new MediaRecorder();
                        // 第1步：设置音频来源（MIC表示麦克风）
                        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        // 第2步：设置音频输出格式（默认的输出格式）
                        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB); // for
                        // 第3步：设置音频编码方式（默认的编码方式）
                        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                        // 第4步：指定音频输出文件
                        mMediaRecorder.setOutputFile(voicePath);
                        // 第5步：调用prepare方法
                        try {
                            mMediaRecorder.prepare();
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // 第6步：调用start方法开始录音
                        mMediaRecorder.start();
                    } catch (Exception e) {
                        mMediaRecorder = null;
                        e.printStackTrace();
                    }
                }
            });
        }else{
        }
    }

    /**
     * 停止录音，并是否删除文件
     * @param delete 是否删除文件
     * @param fileName 语音路径
     */
    public void stopVoiceRecord(boolean delete, final String fileName) {
        try {
            if(mMediaRecorder != null){
                mMediaRecorder.stop();
                mMediaRecorder.release();
                mMediaRecorder = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            mMediaRecorder = null;
        }
        if (delete) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!TextUtils.isEmpty(fileName)) {
                        File file = new File(fileName);
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                }
            }, 1000);
        }
        resetMusic();
    }

    /**
     * 录制语音完成后放弃焦点获取
     */
    private void resetMusic(){
        if(mAudioManager != null && mOnAudioFocusChangeListener != null){
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}
