package com.shibainu.li.cpplibs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class CppTestMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cpp_test_main)

        findViewById<TextView>(R.id.cp_tv).text = CppTestManagaer().test1()
    }
}