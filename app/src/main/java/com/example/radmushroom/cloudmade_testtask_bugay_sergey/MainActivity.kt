package com.example.radmushroom.cloudmade_testtask_bugay_sergey

import android.os.Bundle
import android.support.v4.app.Fragment


class MainActivity : BaseActivity(){

    override fun getContentView(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.mainLayout)
        if (mainFragment == null){
            supportFragmentManager.beginTransaction().replace(R.id.mainLayout, MainFragment()).commit()
        }
    }


}

