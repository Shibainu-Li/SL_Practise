#include <jni.h>

//
// Created by Trust on 2022/12/8.
//


extern "C"
JNIEXPORT jstring JNICALL
Java_com_shibainu_li_cpplibs_CppTestManagaer_test1(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("hello 我是 native code");
}


