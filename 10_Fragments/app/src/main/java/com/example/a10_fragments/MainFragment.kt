package com.example.a10_fragments

import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit

class MainFragment: Fragment(R.layout.fragment_main), ItemSelectListener {

    private var isTablet = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val tabletSize = resources.getBoolean(R.bool.isTablet)
        if (!tabletSize) {
            childFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragment_main_container, ListFragment())
                .commit()
        }
    }

    override fun loadListFragment() {
        childFragmentManager.commit {
            replace(R.id.fragment_main_container, ListFragment())
        }
    }

    override fun loadDetailFragment(text: String) {
        childFragmentManager.commit {
            setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            replace(R.id.fragment_main_container, DetailFragment.newInstance(text))
            if (!isTablet) addToBackStack("loadDetailFragment")
        }
    }

    override fun onItemSelected(text: String) {
        childFragmentManager.beginTransaction()
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .replace(R.id.fragment_main_container, DetailFragment.newInstance(text))
            .commit()
    }
}



