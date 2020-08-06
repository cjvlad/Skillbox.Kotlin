package com.example.a11_fragments

import ViewPagerFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AppActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_app)
//
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.appContainer, ViewPagerFragment())
//            .commit()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.appContainer, ViewPagerFragment())
                .commit()
        }
    }

}