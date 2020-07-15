package com.example.a11_fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_tabs.*

class TabsActivity: AppCompatActivity(R.layout.activity_tabs) {

    private fun applyFilter(viewpager: ViewPager2, screens : List<OnboardingScreen>) {
        val adapter = OnboardingAdapter(screens, this)
        viewpager.adapter = adapter
        TabLayoutMediator(Tablayout, viewPager) { tab, position ->
            tab.text = "Вкладка ${position + 1}"
        }.attach()
        dotsIndicator.setViewPager2(viewPager)
    }

    private val screens: List<OnboardingScreen> = listOf(
        OnboardingScreen(
            tag = listOf(TypeOfPlanes.HELICOPTER),
            textRes = R.string.onboarding_text_1,
            bgColorRes = R.color.onboarding_color_1,
            drawableRes = R.drawable.AS350
        ),

        OnboardingScreen(
            tag = listOf(TypeOfPlanes.HELICOPTER),
            textRes = R.string.onboarding_text_2,
            bgColorRes = R.color.onboarding_color_2,
            drawableRes = R.drawable.Bell412
        ),

        OnboardingScreen(
            tag = listOf(TypeOfPlanes.HELICOPTER),
            textRes = R.string.onboarding_text_3,
            bgColorRes = R.color.onboarding_color_3,
            drawableRes = R.drawable.Mi_8
        ),

        OnboardingScreen(
            tag = listOf(TypeOfPlanes.HELICOPTER),
            textRes = R.string.onboarding_text_4,
            bgColorRes = R.color.onboarding_color_4,
            drawableRes = R.drawable.MilMi_26
        ),

        OnboardingScreen(
            tag = listOf(TypeOfPlanes.JET),
            textRes = R.string.onboarding_text_5,
            bgColorRes = R.color.onboarding_color_5,
            drawableRes = R.drawable.A320
        ),

        OnboardingScreen(
            tag = listOf(TypeOfPlanes.JET),
            textRes = R.string.onboarding_text_6,
            bgColorRes = R.color.onboarding_color_5,
            drawableRes = R.drawable.B737_500
        ),

        OnboardingScreen(
            tag = listOf(TypeOfPlanes.JET),
            textRes = R.string.onboarding_text_7,
            bgColorRes = R.color.onboarding_color_1,
            drawableRes = R.drawable.B777
        ),

        OnboardingScreen(
            tag = listOf(TypeOfPlanes.JET),
            textRes = R.string.onboarding_text_8,
            bgColorRes = R.color.onboarding_color_2,
            drawableRes = R.drawable.Eclipse550
        ),

        OnboardingScreen(
            tag = listOf(TypeOfPlanes.PROPELLER),
            textRes = R.string.onboarding_text_9,
            bgColorRes = R.color.onboarding_color_3,
            drawableRes = R.drawable.An_2
        ),

        OnboardingScreen(
            tag = listOf(TypeOfPlanes.PROPELLER),
            textRes = R.string.onboarding_text_10,
            bgColorRes = R.color.onboarding_color_4,
            drawableRes = R.drawable.Cessna177
        ),

        OnboardingScreen(
            tag = listOf(TypeOfPlanes.PROPELLER),
            textRes = R.string.onboarding_text_11,
            bgColorRes = R.color.onboarding_color_5,
            drawableRes = R.drawable.Fokker50
        ),

        OnboardingScreen(
            tag = listOf(TypeOfPlanes.PROPELLER),
            textRes = R.string.onboarding_text_12,
            bgColorRes = R.color.onboarding_color_5,
            drawableRes = R.drawable.PiperPA_31
        )
    )

    private var filtredArticles = screens

    fun onConfirm(filteredData : Array<Boolean>) {
        val tmpModelList = ArrayList<OnboardingScreen>()
        for (e in screens)
        {
            if ((filteredData[0] && e.tag.contains(TypeOfPlanes.HELICOPTER)) ||
                (filteredData[1] && e.tag.contains(TypeOfPlanes.JET)) ||
                (filteredData[2] && e.tag.contains(TypeOfPlanes.PROPELLER))) {
                tmpModelList.add(e)
            }
        }
        filterData = filteredData
        applyFilter(viewPager, tmpModelList)
    }

    var filterData = arrayOf(true,true,true,true,true,true)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        button.setOnClickListener {
            Tablayout.getTabAt((0 until 7).random())?.orCreateBadge?.apply {
                number = getNumber()+1
                badgeGravity = BadgeDrawable.BOTTOM_END
            }
        }

        filterData = arrayOf(true,true,true,true,true,true)
        setHasOptionsMenu(true)
        applyFilter(viewPager, screens)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Tablayout.getTabAt(position)?.removeBadge()
            }
        })

        button_filter.setOnClickListener {
            ChoiseFragment.newInstance(filterData).show(childFragmentManager, "choisedialogfragment ")
        }

    }

}
