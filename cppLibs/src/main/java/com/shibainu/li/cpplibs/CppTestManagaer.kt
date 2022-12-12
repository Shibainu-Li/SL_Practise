package com.shibainu.li.cpplibs

import android.util.Log

class CppTestManagaer {
    init {
        System.loadLibrary("cppTest-native-lib")
    }

    external fun test1():String


    external fun testChangeFiles()


    external fun testCallBackFiles()

    external fun testThread(t:Thread):Long


    private val TAG = "NeedNativeCall"
    private var name:String = "果子哥"
    private var year :Int =  18

    fun nativeCall(){
        Log.d(TAG,"nativeCall:$name:|$year")
    }

    fun nativeCallChangeFiles(){
        Log.d(TAG,"nativeCallChangeFiles:$name:|$year")
    }

    fun nativeCall(cName:String,cYear:Int){
        Log.d(TAG,"nativeCallC:$cName:|$cYear")
    }

    fun nativeCallJni(){
        Log.d(TAG,"nativeCallJni")
    }


    external fun startPthread();

}