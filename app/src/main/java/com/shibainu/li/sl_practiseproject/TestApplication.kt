package com.shibainu.li.sl_practiseproject

import android.app.Application
import android.util.Log
import com.shibainu.li.dblibs.Testmanager
import com.shibainu.li.uilibs.NewAppWidget

class TestApplication:Application() {
    companion object{
        lateinit var mTestmanager:Testmanager
    }

    override fun onCreate() {
        super.onCreate()
        mTestmanager = Testmanager().also {
            it.initConfig(this)
        }

        NewAppWidget.Companion.mTest = {
            mTestmanager.addTest(it)
        }
        Log.d("sl_p","application init")
        test("",2)
    }


    fun test(msg:String,num:Int):String = "this is test"
}