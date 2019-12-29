package com.std.framework.core;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.std.framework.util.ToastUtil;

import java.io.File;
import java.io.IOException;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import me.std.common.utils.Logger;


/**
 * Description:
 * Created by 李晓 ON 2016/12/8.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader:
 */
public class VoiceRecordHelper {
    /* 语音录制帮助类标记 */
    private String TAG = "VoiceRecordHelper";

    /* 语音录制 */
    private MediaRecorder mMediaRecorder;

    /* 语音录制handler */
    private Handler handler = new Handler();

    /* 系统声音管理类 */
    private AudioManager mAudioManager;

    /* 系统声音改变监听 */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;

    public VoiceRecordHelper(Context context) {
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        initListener();
    }

    private void initListener() {
        // 系统声音改变监听
        mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                Logger.d(TAG, "Audio Focus Change=" + focusChange);
                // 暂时失去了音频焦点，但很快会重新得到焦点
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                    Logger.e(TAG, focusChange + "");
                    // 你已经得到焦点了
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    // 永久失去焦点
                    Logger.e(TAG, focusChange + "");
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    Logger.e(TAG, focusChange + "");
                }
            }
        };
    }

    /**
     * 开始录制语音
     *
     * @param voicePath 语音录制的路径
     */
    public void startVoiceRecord(final String voicePath) {
        if (TextUtils.isEmpty(voicePath)) {
            return;
        }
        boolean isFocus = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
        if (isFocus) {
            Logger.d(TAG, "file name = " + voicePath);
            try {
                if (mMediaRecorder != null) {
                    mMediaRecorder.stop();
                    mMediaRecorder.release();
                    mMediaRecorder = null;
                    Logger.e("mediaRecorder is not null", "");
                }
            } catch (Exception e) {
                Log.w(TAG, "stopRecord" + e.getMessage());
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        mMediaRecorder = new MediaRecorder();
                        // 第1步：设置音频来源（MIC表示麦克风）
                        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        Logger.d(TAG, "file setAudioSource = MediaRecorder.AudioSource.MIC");
                        // 第2步：设置音频输出格式（默认的输出格式）
                        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB); // for
                        // 第3步：设置音频编码方式（默认的编码方式）
                        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                        Logger.d(TAG, "file setAudioEncoder = ");
                        // 第4步：指定音频输出文件
                        mMediaRecorder.setOutputFile(voicePath);
                        // 第5步：调用prepare方法
                        try {
                            mMediaRecorder.prepare();
                            Logger.d(TAG, "file prepare");
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Logger.d(TAG, "message = " + e.getMessage());
                        }
                        // 第6步：调用start方法开始录音
                        mMediaRecorder.start();
                    } catch (Exception e) {
                        mMediaRecorder = null;
                        ToastUtil.show("录音失败");
                        e.printStackTrace();
                    }
                }
            });
        } else {
            Logger.e(TAG, "request Audio Focus failed.");
        }
    }

    /**
     * 开始录制语音  需要返回值判断录制是否成功用到
     *
     * @param voicePath 语音录制的路径
     */
    public Flowable<Boolean> startVoiceRecordAsObservable(final String voicePath) {
        if (TextUtils.isEmpty(voicePath)) {
            return Flowable.empty();
        }
        return Flowable.just(voicePath)
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        boolean isFocus = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                                AudioManager.STREAM_MUSIC,
                                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
                        if (!isFocus) {
                            Logger.e(TAG, "request Audio Focus failed.");
                        }
                        return isFocus;
                    }
                }).map(new Function<String, Boolean>() {
                           @Override
                           public Boolean apply(String s) throws Exception {
                               Logger.d(TAG, "file name = " + voicePath);
                               try {
                                   //重新初始化mMediaRecorder
                                   if (mMediaRecorder != null) {
                                       mMediaRecorder.stop();
                                       mMediaRecorder.release();
                                       mMediaRecorder = null;
                                       Logger.e("mediaRecorder is not null", "");
                                   }
                                   mMediaRecorder = new MediaRecorder();
                                   // 第1步：设置音频来源（MIC表示麦克风）
                                   mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                                   Logger.d(TAG, "file setAudioSource = MediaRecorder.AudioSource.MIC");
                                   // 第2步：设置音频输出格式（默认的输出格式）
                                   mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB); // for
                                   // 第3步：设置音频编码方式（默认的编码方式）
                                   mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                                   Logger.d(TAG, "file setAudioEncoder = ");
                                   // 第4步：指定音频输出文件
                                   mMediaRecorder.setOutputFile(voicePath);
                                   // 第5步：调用prepare方法
                                   mMediaRecorder.prepare();
                                   // 第6步：调用start方法开始录音
                                   mMediaRecorder.start();
                               } catch (Exception e) {
                                   mMediaRecorder = null;
                                   Logger.d(TAG, "message = " + e.getMessage());
                                   return false;
                               }
                               return true;
                           }
                       }
                ).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 停止录音，并是否删除文件
     *
     * @param delete   是否删除文件
     * @param fileName 语音路径
     */
    public void stopVoiceRecord(boolean delete, final String fileName) {
        try {
            if (mMediaRecorder != null) {
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
    private void resetMusic() {
        if (mAudioManager != null && mOnAudioFocusChangeListener != null) {
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}
