package com.shibainu.li.baselib

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity(@LayoutRes layoutId:Int):AppCompatActivity(layoutId) ,IUiView{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        initConfig()
    }

    open fun initViews(){  }

    open fun initConfig(){  }

    override fun showLoading() { Log.d("base","showLoading") }

    override fun dismissLoading() { Log.d("base","dismissLoading") }

}