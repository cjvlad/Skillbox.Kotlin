package com.skillbox.lists12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            createListFragment()
        }

    }

    private fun createListFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, PersonListFragment())
            .commit()
    }
}
