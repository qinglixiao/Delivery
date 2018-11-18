#include <jni.h>
#include<android/log.h>

extern "C" {
#include "decode_audio.h"
#include "encode_audio.h"
}

#define LOG_TAG "LX"
#define d(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define i(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

extern "C"
JNIEXPORT jint JNICALL
Java_com_std_framework_ffmpeg_FFmpegTool_run(JNIEnv *env, jclass type, jobjectArray cmds) {
    int argc = env->GetArrayLength(cmds);
    char *argv[argc];
    for (int i = 0; i < argc; i++) {
        jstring js = (jstring) env->GetObjectArrayElement(cmds, i);
        argv[i] = (char *) env->GetStringUTFChars(js, 0);
    }
    return 0;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_std_framework_ffmpeg_FFMediaConvert_audioToMp3(
        JNIEnv *env, jclass type, jstring srcFile_, jstring targetFile_) {
    d("进入andioToMp3");
    const char *srcFile = env->GetStringUTFChars(srcFile_, 0);
    const char *targetFile = env->GetStringUTFChars(targetFile_, 0);
    char *srcFile1 = NULL;
    char *targetFile1 = NULL;
    srcFile1 = const_cast<char *>(srcFile);
    targetFile1 = const_cast<char *>(targetFile);
    char *inputArgv[3];
    char *outputArgv[2];
    inputArgv[0] = srcFile1;
    inputArgv[1] = srcFile1;
    inputArgv[2] = targetFile1;

    outputArgv[0] = targetFile1;
    outputArgv[1] = targetFile1;
    d("开始执行input");
    input(3, inputArgv);
    d("开始执行output");
    output(2, outputArgv);
    d("执行完毕！");
    return 0;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_std_framework_ffmpeg_FFMediaConvert_add(JNIEnv *env, jclass type, jint a, jint b) {
    return a + b;
}