package com.shibainu.li.sl_practiseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.shibainu.li.cpplibs.CppTestMainActivity
import com.shibainu.li.cpplibs.CppTestManagaer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.cpp_test).setOnClickListener {
            startActivity(Intent(this,CppTestMainActivity::class.java))
        }

    }
}