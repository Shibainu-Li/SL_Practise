package com.shibainu.li.cpplibs

class CppTestManagaer {
    init {
        System.loadLibrary("cppTest-native-lib")
    }

    external fun test1():String

}