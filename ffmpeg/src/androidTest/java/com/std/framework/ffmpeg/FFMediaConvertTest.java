package com.std.framework.ffmpeg;

import android.os.Environment;

import junit.framework.TestCase;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/9.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class FFMediaConvertTest extends TestCase {

    public void testAudioToMp3() {
        String src = Environment.getExternalStorageDirectory() + "/audio.amr";
        String target = Environment.getExternalStorageDirectory() + "/audio.mp3";
        FFMediaConvert.audioToMp3(src, target);
    }

    public void testAdd() {
        FFMediaConvert.add(3, 4);
    }
}