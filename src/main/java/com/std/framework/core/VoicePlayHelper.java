package com.std.framework.core;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;

import com.std.framework.util.ToastUtil;

import java.io.File;

/**
 * Description:
 * Created by 李晓 ON 2016/12/8.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader:
 */
public class VoicePlayHelper {
    /* 语音播放类标记 */
    private String TAG = "VoicePlayHelper";

    /* 持有的context */
    private Activity mContext;

    /* 语音文件路径 */
    private String mVoicePath;

    /* 语音播放 MediaPlayer */
    private MediaPlayer mMediaPlayer;

    /* 语音播放完成监听 */
    private OnVoiceFinishListener onVoiceFinishListener;

    private MediaPlayer.OnCompletionListener mOnCompletionListener;

    /* 系统声音管理类 */
    private AudioManager mAudioManager;

    /* 系统声音改变监听 */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;

    /* 距离传感器管理类 */
    private SensorManager mSensorManager;

    /* 距离传感器 */
    private Sensor mSensor;

    /* 距离传感器监听 */
    private SensorEventListener mSensorEventListener;// 距离传感器监听

    /* 记录播放模式 是否是听筒模式 */
    private boolean mIsPhoneMode = false;

    private Handler handler;

    public VoicePlayHelper(Activity context) {
        mContext = context;
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        handler = new Handler();
        initListener();
    }

    /**
     * 初始化语音播放时的监听
     */
    private void initListener() {
        // 系统声音改变监听
        mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                Logger.d(TAG, "Audio Focus Change=" + focusChange);
                // 暂时失去了音频焦点，但很快会重新得到焦点
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                    if (mMediaPlayer.isPlaying()) {
                        mMediaPlayer.pause();
                    }
                    // 你已经得到焦点了
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
                        mMediaPlayer.start();
                    }
                    // 永久失去焦点
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    stopVoice();
                }
            }
        };

        // 距离监听
        mSensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float range = event.values[0];
                mIsPhoneMode = range < 1.0 && event.sensor.getType() == Sensor.TYPE_PROXIMITY;
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    stopVoiceNotResetFocus();
                    changePlayMode(mIsPhoneMode);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //切换听筒时候  系统切换速度过慢导致语音开头不完整  所以延时1.5s开始播放
                            startVoice(mVoicePath);
                        }
                    }, 1500);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopVoice();
                if (onVoiceFinishListener != null) {
                    onVoiceFinishListener.onFinish();
                }
            }
        };
    }

    /**
     * 语音播放完毕还原其余声音
     */
    private void startOthersMusic() {
        if (mAudioManager != null) {
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    /**
     * 开始播放语音
     *
     * @param voicePath 语音文件路径
     */
    public void startVoice(String voicePath) {
        // 切换播放模式
        changePlayMode(mIsPhoneMode);
        mVoicePath = voicePath;
        boolean isMusic = mAudioManager.isMusicActive();
        int requestAudioFocusResult = 1;
        if (isMusic) {
            // 以闹钟的流类型获取焦点，这样占用结束之后，可以继续原有播放
            requestAudioFocusResult = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                    AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        }
        // 获取焦点成功，准备播放
        if (requestAudioFocusResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            Logger.d(TAG, "request Audio Focus successfully.");
            try {
                File file = new File(voicePath);
                if (file.exists()) {
                    if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                        stopVoice();
                    }
                    if (mMediaPlayer == null) {
                        mMediaPlayer = new MediaPlayer();
                    } else {
                        mMediaPlayer.release();
                    }
                    // 播放错误监听
                    mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mp, int what, int extra) {
                            stopVoice();
                            if (onVoiceFinishListener != null) {
                                onVoiceFinishListener.onFinish();
                            }
                            return false;
                        }
                    });
                    mMediaPlayer.setDataSource(voicePath);
                    if (mOnCompletionListener != null) {
                        mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                    }
                    mMediaPlayer.prepareAsync();
                    // 准备完成之后进行播放
                    mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mMediaPlayer.start();
                        }
                    });
                } else {
                    ToastUtil.show("语音不存在!");
                }
            } catch (Exception e) {
                Logger.e(TAG, "startVoice failed:" + e.getMessage());
                stopVoice();
                if (onVoiceFinishListener != null) {
                    onVoiceFinishListener.onFinish();
                }
            }
        } else {
            Logger.e(TAG, "request Audio Focus failed.");
            stopVoice();
            if (onVoiceFinishListener != null) {
                onVoiceFinishListener.onFinish();
            }
        }

    }

    /**
     * 停止播放语音
     */
    public void stopVoice() {
        try {
            if (mMediaPlayer != null) {
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                }
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
        } catch (Exception e) {
            // 系统在回收release时候release生效但mediaPlay并不为空
            // 所以在判断isPlaying时会抛IllegalStateException 此时需要至为空
            mMediaPlayer = null;
            Logger.e(TAG, "stopVoice failed:" + e.getMessage());
        } finally {
            startOthersMusic();
        }
    }

    /**
     * 停止播放语音 不还原焦点
     */
    private void stopVoiceNotResetFocus() {
        try {
            if (mMediaPlayer != null) {
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                }
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
        } catch (Exception e) {
            // 系统在回收release时候release生效但mediaPlay并不为空
            // 所以在判断isPlaying时会抛IllegalStateException 此时需要至为空
            mMediaPlayer = null;
            Logger.e(TAG, "stopVoice failed:" + e.getMessage());
        }
    }

    // 注册距离传感器
    public void registerListener() {
        mSensorManager.registerListener(mSensorEventListener, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    // 取消注册距离传感器
    public void unRegisterListener() {
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(mSensorEventListener);
        }
    }

    public void setOnVoiceFinishListener(OnVoiceFinishListener onVoiceFinishListener) {
        this.onVoiceFinishListener = onVoiceFinishListener;
    }

    /**
     * 切换播放模式
     *
     * @param isPhoneMode 是否是听筒模式
     */
    private void changePlayMode(boolean isPhoneMode) {
        if (isPhoneMode) {
            mIsPhoneMode = true;
            mAudioManager.setSpeakerphoneOn(false);
            mContext.setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                mAudioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
            } else {
                mAudioManager.setMode(AudioManager.MODE_IN_CALL);
            }
        } else {
            mIsPhoneMode = false;
            mAudioManager.setSpeakerphoneOn(true);
            mContext.setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mAudioManager.setMode(AudioManager.MODE_NORMAL);
        }
    }

    public interface OnVoiceFinishListener {
        // 播放结束
        void onFinish();
    }
}
