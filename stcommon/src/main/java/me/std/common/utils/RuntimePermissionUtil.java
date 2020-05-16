package me.std.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 对运行时权限做相关操作
 * Author: lixiao
 * Create on: 2018/12/13.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class RuntimePermissionUtil {

    /**
     * 运行时权限筛选，返回还未获取的权限
     * 过滤掉已经获得的权限
     *
     * @param context
     * @param permissions
     * @return
     */
    public static String[] filterPermissions(Context context, String[] permissions) {
        String[] filter = null;
        if (permissions != null && permissions.length > 0) {
            List<String> denied = new ArrayList<>();
            for (String permission : permissions) {
                if (!checkSelfPermission(context, permission)) {
                    denied.add(permission);
                }
            }
            if (denied.size() != 0) {
                int count = denied.size();
                filter = new String[count];
                for (int i = 0; i < count; i++) {
                    filter[i] = denied.get(i);
                }
            }
        }
        return filter;
    }

    /**
     * 获取运行时权限
     *
     * @param context
     * @param permissions
     * @param requestCode
     */
    public static String[] requestPermissions(Activity context, String[] permissions, int requestCode) {
        String[] denied = null;
        if (permissions != null && permissions.length > 0) {
            denied = filterPermissions(context, permissions);
            if (denied != null && denied.length > 0) {
                ActivityCompat.requestPermissions(context, denied, requestCode);
            }
        }
        return denied;

    }

    /**
     * 是否具有某运行时权限
     *
     * @param context
     * @param permission
     * @return
     */
    public static boolean checkSelfPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 摄像头权限（适应于5.1系统）
     *
     * @return
     */
    public static boolean isHasCameraPermission() {
        boolean canUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
            // setParameters 是针对魅族MX5 做的。MX5 通过Camera.open() 拿到的Camera
            Camera.Parameters mParameters = mCamera.getParameters();
            mCamera.setParameters(mParameters);
        } catch (Exception e) {
            canUse = false;
        }
        if (mCamera != null) {
            mCamera.release();
        }
        return canUse;
    }


    /**
     * 判断是是否有录音权限 (适应于5.1系统)
     */
    public static boolean isHasAudioPermission() {
        // 音频获取源
        int audioSource = MediaRecorder.AudioSource.MIC;
        // 设置音频采样率，44100是目前的标准，但是某些设备仍然支持22050，16000，11025
        int sampleRateInHz = 44100;
        // 设置音频的录制的声道CHANNEL_IN_STEREO为双声道，CHANNEL_CONFIGURATION_MONO为单声道
        int channelConfig = AudioFormat.CHANNEL_IN_STEREO;
        // 音频数据格式:PCM 16位每个样本。保证设备支持。PCM 8位每个样本。不一定能得到设备支持。
        int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
        // 缓冲区字节大小
        int bufferSizeInBytes = 0;
        bufferSizeInBytes = 0;
        bufferSizeInBytes = AudioRecord.getMinBufferSize(sampleRateInHz,
                channelConfig, audioFormat);
        AudioRecord audioRecord = new AudioRecord(audioSource, sampleRateInHz,
                channelConfig, audioFormat, bufferSizeInBytes);
        //开始录制音频
        try {
            // 防止某些手机崩溃，例如联想
            audioRecord.startRecording();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        if (audioRecord.getRecordingState() != AudioRecord.RECORDSTATE_RECORDING
                && audioRecord.getRecordingState() != AudioRecord.RECORDSTATE_STOPPED) {
            return false;
        }

        if (audioRecord.getRecordingState() == AudioRecord.RECORDSTATE_STOPPED) {
            //如果短时间内频繁检测，会造成audioRecord还未销毁完成，此时检测会返回RECORDSTATE_STOPPED状态，再去read，会读到0的size，可以更具自己的需求返回true或者false
            return false;
        }

        byte[] bytes = new byte[1024];
        int readSize = audioRecord.read(bytes, 0, 1024);
        if (readSize == AudioRecord.ERROR_INVALID_OPERATION || readSize <= 0) {
            return false;
        }
        audioRecord.stop();
        audioRecord.release();
        audioRecord = null;
        return true;
    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    private void method() {
//        CameraManager manager = (CameraManager) AppContextUtil.getAppContext().getSystemService(Context.CAMERA_SERVICE);
//        try {
//            //获取可用摄像头列表
//            for (String cameraId : manager.getCameraIdList()) {
//                //获取相机的相关参数
//                CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
//                // 不使用前置摄像头。
//                Integer facing = characteristics.get(CameraCharacteristics.LENS_FACING);
//                if (facing != null && facing == CameraCharacteristics.LENS_FACING_FRONT) {
//                    continue;
//                }
//                StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
//                if (map == null) {
//                    continue;
//                }
//                try {
//                    //打开相机预览
//                    manager.openCamera(cameraId,null, new StateCallback() {
//                        @Override
//                        public void onOpened(CameraDevice camera) {
//
//                        }
//
//                        @Override
//                        public void onDisconnected(CameraDevice camera) {
//
//                        }
//
//                        @Override
//                        public void onError(CameraDevice camera, int error) {
//
//                        }
//                    });
//                } catch (CameraAccessException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException("Interrupted while trying to lock camera opening.", e);
//                }
//                return;
//            }
//        } catch (CameraAccessException e) {
//            e.printStackTrace();
//        } catch (NullPointerException e) {
//            //不支持Camera2API
//        }
//    }
//}

}
