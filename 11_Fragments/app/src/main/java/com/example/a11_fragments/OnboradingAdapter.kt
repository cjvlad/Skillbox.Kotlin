package com.example.a11_fragments

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter(
    private val screens: List<OnboardingScreen>,
    fragment: TabsActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return screens.size
    }

    override fun createFragment(position: Int): Fragment {
        val screen: OnboardingScreen = screens[position]
        return OnboardingFragment.newInstance(
            textRes = screen.textRes,
            drawableRes = screen.drawableRes,
            bgColorRes = screen.bgColorRes
        )
    }
}