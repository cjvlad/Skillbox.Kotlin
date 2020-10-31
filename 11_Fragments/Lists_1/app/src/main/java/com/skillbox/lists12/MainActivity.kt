package com.skillbox.lists12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(R.layout.activity_app) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            createListFragment()
        }

    }

    private fun createListFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.appContainer, PersonListFragment())
            .commit()
    }
}
