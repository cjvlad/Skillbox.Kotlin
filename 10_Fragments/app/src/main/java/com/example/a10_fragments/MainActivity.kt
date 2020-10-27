package com.example.a10_fragments

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {
        const val TAG = "TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            showLoginFragment()
        }
    }

    private fun showLoginFragment() {
        val alreadyHasFragment = supportFragmentManager.findFragmentById(R.id.container) != null
        if (!alreadyHasFragment) {
            supportFragmentManager.commit {
                setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left)
                replace(R.id.container, LoginFragment())
            }
        } else {
            toast("alreadyHasFragment")
        }
    }

    fun showMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment(), TAG)
            .commit()
    }

    override fun onBackPressed() {
        val fragmentMain = supportFragmentManager.findFragmentByTag(TAG)

        if (fragmentMain != null && fragmentMain.childFragmentManager.backStackEntryCount > 0) {
            fragmentMain.childFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}

