package com.example.radmushroom.cloudmade_testtask_bugay_sergey

import android.os.Bundle
import android.support.v4.app.Fragment

class UserDetailActivity : BaseActivity() {

    override fun getContentView(): Int = R.layout.activity_user_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userDetailFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.userDetailLayout)
        if (userDetailFragment == null){
            supportFragmentManager.beginTransaction().replace(R.id.userDetailLayout, UserDetailsFragment()).commit()
        }
    }
}
