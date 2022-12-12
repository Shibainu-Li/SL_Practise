#include <jni.h>
#include <pthread.h>
#include<android/log.h>

#define TAG    "豪哥" // 这个是自定义的LOG的标识
#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__) // 定义LOGD类型
#define LOGI(...)  __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__) // 定义LOGI类型
#define LOGW(...)  __android_log_print(ANDROID_LOG_WARN,TAG,__VA_ARGS__) // 定义LOGW类型
#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__) // 定义LOGE类型

//
// Created by Trust on 2022/12/8.
//

/*
 * 反射java class
 */



const char *callBackPath = "com/shibainu/li/cpplibs/TestNativeCallBack";

jobject javaObject = nullptr;


void testClassLoadJava(JNIEnv *env,jobject thiz){

    jclass jc = env->GetObjectClass(thiz);

    jmethodID nativeCallId = env->GetMethodID(jc,"nativeCall","()V");
    env->CallVoidMethod(thiz,nativeCallId);
}




extern "C"
JNIEXPORT jstring JNICALL
Java_com_shibainu_li_cpplibs_CppTestManagaer_test1(JNIEnv *env, jobject thiz) {
    testClassLoadJava(env,thiz);
    return env->NewStringUTF("hello 我是 native code");
}



extern "C"
JNIEXPORT void JNICALL
Java_com_shibainu_li_cpplibs_CppTestManagaer_testChangeFiles(JNIEnv *env, jobject thiz) {
    jclass  jc = env->GetObjectClass(thiz);
    jmethodID nativeCallChangeFilesId = env->GetMethodID(jc,"nativeCallChangeFiles","()V");


    jfieldID yearId = env->GetFieldID(jc,"year","I");
    jfieldID nameId = env->GetFieldID(jc,"name","Ljava/lang/String;");
    env->SetIntField(thiz,yearId,29);
    env->SetObjectField(thiz, nameId, env->NewStringUTF("果子哥牛批！！！"));

    env->CallVoidMethod(thiz,nativeCallChangeFilesId);
}


extern "C"
JNIEXPORT void JNICALL
Java_com_shibainu_li_cpplibs_CppTestManagaer_testCallBackFiles(JNIEnv *env, jobject thiz) {
    //获取jclass
    jclass jc = env->GetObjectClass(thiz);
    //获取methodId
    jmethodID testCallBackFilesID = env->GetMethodID(jc,"nativeCall","(Ljava/lang/String;I)V");
    //调用java层
    env->CallVoidMethod(thiz,testCallBackFilesID,env->NewStringUTF("豪总"),28);
}

/**
 * native函数
 * @param env
 * @param thiz
 * @return
 */
jstring getDoLog(JNIEnv * env,jobject thiz){
    return env->NewStringUTF("我是动态注册native函数");
}

/**
 * java与native函数关系表
 */
JNINativeMethod methods[] = {
        {"getDoLog","()Ljava/lang/String;",(void *)getDoLog},
};

JNIEnv * mEnv = nullptr;
JavaVM* mVm = nullptr;
jint JNI_OnLoad(JavaVM* vm, void* reserved){
    mVm = vm;

    if(vm->GetEnv((void **)&mEnv,JNI_VERSION_1_6) != JNI_OK){ return JNI_ERR; }

    //反射JavaClass
    jclass clazz = mEnv->FindClass("com/shibainu/li/cpplibs/NeedCallBackClasz");

    if(clazz == nullptr){ return JNI_ERR; }

    //注册 class和函数关系表对应关系
    jint result = mEnv->RegisterNatives(clazz,methods,1);

    if(result < 0){ return JNI_ERR; }

    return JNI_VERSION_1_6;
}

extern "C"
JNIEXPORT jlong JNICALL
Java_com_shibainu_li_cpplibs_CppTestManagaer_testThread(JNIEnv *env, jobject thiz, jobject t) {
    jclass jtClasz = env->GetObjectClass(t);
    jfieldID poorId = env->GetStaticFieldID(jtClasz, reinterpret_cast<const char *>('nativePeer'), "J");
    jlong nativePeers =  env->GetLongField(t,poorId);
    return nativePeers;
}


void testNewJavaClass(JNIEnv * je){
    jclass  jc = je->FindClass("com/shibainu/li/cpplibs/TestNativeCallBack");
    jobject  TestNativeCallbackOb = je->AllocObject(jc);
    jmethodID tID = je->GetMethodID(jc,"showLog", "(Ljava/lang/String;)V");
    je->CallVoidMethod(TestNativeCallbackOb,tID,je->NewStringUTF("我是native传送过来的"));
}


/**
 * 该线程执行的是 在子线程反射调用java层函数
 * @param arg
 * @return
 */
void *testPthread(void * arg){
    LOGD("执行线程");
    JNIEnv * je = nullptr;
    jint result = mVm->AttachCurrentThread(&je, nullptr);
    if(result == JNI_OK){
        jclass jc = je->GetObjectClass(javaObject);
        jmethodID ncjID = je->GetMethodID(jc,"nativeCallJni","()V");
        je->CallVoidMethod(javaObject,ncjID);
        mVm->DetachCurrentThread();
    }

    return 0;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_shibainu_li_cpplibs_CppTestManagaer_startPthread(JNIEnv *env, jobject thiz) {
    testNewJavaClass(env);
    pthread_t p;
    //把jobject 提升全局变量
    javaObject = env->NewGlobalRef(thiz);
    pthread_create(&p, nullptr, testPthread, (void *) "awdad");
}

