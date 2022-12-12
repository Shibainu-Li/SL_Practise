package com.shibainu.li.cpplibs

class NeedCallBackClasz {
    init {
        System.loadLibrary("cppTest-native-lib")
    }

    /**
     * 动态注册
     */
    external fun getDoLog():String
}