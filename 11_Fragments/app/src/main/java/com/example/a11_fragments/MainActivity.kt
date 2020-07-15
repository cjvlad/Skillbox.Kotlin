package com.example.a11_fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showViewPagerFragment()
    }

    private fun showViewPagerFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.container, TabsActivity())
            .commit()
    }
}
