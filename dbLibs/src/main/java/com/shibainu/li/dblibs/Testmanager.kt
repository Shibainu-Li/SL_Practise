package com.shibainu.li.dblibs

import android.app.Application
import android.content.Context
import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.android.Admin
import java.text.SimpleDateFormat

class Testmanager {
    private lateinit var mBoxStore: BoxStore

    fun initConfig(application: Context){
        mBoxStore = MyObjectBox.builder().androidContext(application).build()
        Admin(mBoxStore).start(application)
    }

    val mTest: Box<TestDb> by lazy { mBoxStore.boxFor(TestDb::class.java) }

    fun addTest(num:Int){
        val timeStamp = System.currentTimeMillis()
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        mTest.put(TestDb(num = num, time = sdf.format(timeStamp)))
    }
}