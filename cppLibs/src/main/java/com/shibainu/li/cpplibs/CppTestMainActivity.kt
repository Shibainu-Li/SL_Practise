package com.shibainu.li.cpplibs

import android.app.AlarmManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlin.concurrent.thread

class CppTestMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cpp_test_main)
//        TestClassLoad().test()
        CppTestManagaer().let {
            it.startPthread()
        }






        val currentThread = Thread.currentThread()
        val javaClass = Thread::class.java

        val declaredField = javaClass.getDeclaredField("nativePeer")
        declaredField.isAccessible = true
        val nativePeer = declaredField.get(currentThread)
        Log.d("lhh","nativePeer CTM:$nativePeer")

//            findViewById<TextView>(R.id.cp_tv).text = it.test1()
//            it.testChangeFiles()
//            it.testCallBackFiles()
//
//            val currentThread = Thread.currentThread()
//
//            val nativePeerFiled = currentThread::class.java.getDeclaredField("priority")
//            nativePeerFiled.isAccessible = true
//            val name = nativePeerFiled.name
////
//            val long = nativePeerFiled.get(null)
////
////            Log.d("CppTestMainActivity"," thread: ")
//
//
//            TestFiles().let {
//                val filed = it::class.java.getDeclaredField("testLong")
//                filed.isAccessible = true
//
//                val get = filed.get(null)
//                get
//            }
//        }

        NeedCallBackClasz().let {
            Log.d("CppTestMainActivity",it.getDoLog())
        }

    }



}