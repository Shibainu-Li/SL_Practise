package com.shibainu.li.sl_practiseproject

import android.content.Intent
import android.util.Log
import android.view.View
import com.shibainu.li.baselib.BaseActivity
import com.shibainu.li.cpplibs.CppTestMainActivity

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun initViews() {
        findViewById<View>(R.id.cpp_test).setOnClickListener {
            startActivity(Intent(this,CppTestMainActivity::class.java))
        }
    }

    override fun initConfig() {
        Log.d("lhh","initConfig")
        showLoading()
        dismissLoading()
    }

}