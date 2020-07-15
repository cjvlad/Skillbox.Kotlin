import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.a11_fragments.ChoiseDialogFragment
import com.example.a11_fragments.R
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_tabs.*

class TabsActivity: Fragment(R.layout.activity_tabs) {

    private fun applyFilter(viewpager: ViewPager2, screens : List<OnboardingScreen>) {

        val adapter = OnboardingAdapter(screens, this) // количество экранов увеличен в два раза скринс
        viewPager.adapter = adapter

        TabLayoutMediator(Tablayout, viewPager) // связать лайаут и вьюпейджер
        { tab, position ->
            tab.text = "Вкладка ${position + 1}"
            tab.setIcon(R.drawable.ic_flight)
        }.attach()
        dotsIndicator.setViewPager2(viewPager)
    }

    private val screens: List<OnboardingScreen> = listOf(
        OnboardingScreen(
            tag = listOf(ArticleTag.HELICOPTER),
            textRes = R.string.onboarding_text_1,
            bgColorRes = R.color.onboarding_color_1,
            drawableRes = R.drawable.as350
        ),

        OnboardingScreen(
            tag = listOf(ArticleTag.HELICOPTER),
            textRes = R.string.onboarding_text_2,
            bgColorRes = R.color.onboarding_color_2,
            drawableRes = R.drawable.bell412
        ),

        OnboardingScreen(
            tag = listOf(ArticleTag.HELICOPTER),
            textRes = R.string.onboarding_text_3,
            bgColorRes = R.color.onboarding_color_3,
            drawableRes = R.drawable.mi8
        ),

        OnboardingScreen(
            tag = listOf(ArticleTag.HELICOPTER),
            textRes = R.string.onboarding_text_4,
            bgColorRes = R.color.onboarding_color_4,
            drawableRes = R.drawable.milmi26
        ),

        OnboardingScreen(
            tag = listOf(ArticleTag.JET),
            textRes = R.string.onboarding_text_5,
            bgColorRes = R.color.onboarding_color_5,
            drawableRes = R.drawable.a320
        ),

        OnboardingScreen(
            tag = listOf(ArticleTag.JET),
            textRes = R.string.onboarding_text_6,
            bgColorRes = R.color.onboarding_color_5,
            drawableRes = R.drawable.b737
        ),

        OnboardingScreen(
            tag = listOf(ArticleTag.JET),
            textRes = R.string.onboarding_text_7,
            bgColorRes = R.color.onboarding_color_1,
            drawableRes = R.drawable.b777
        ),

        OnboardingScreen(
            tag = listOf(ArticleTag.JET),
            textRes = R.string.onboarding_text_8,
            bgColorRes = R.color.onboarding_color_2,
            drawableRes = R.drawable.eclipse550
        ),

        OnboardingScreen(
            tag = listOf(ArticleTag.PROPELLER),
            textRes = R.string.onboarding_text_9,
            bgColorRes = R.color.onboarding_color_3,
            drawableRes = R.drawable.an2
        ),

        OnboardingScreen(
            tag = listOf(ArticleTag.PROPELLER),
            textRes = R.string.onboarding_text_10,
            bgColorRes = R.color.onboarding_color_4,
            drawableRes = R.drawable.cessna177
        ),

        OnboardingScreen(
            tag = listOf(ArticleTag.PROPELLER),
            textRes = R.string.onboarding_text_11,
            bgColorRes = R.color.onboarding_color_5,
            drawableRes = R.drawable.fokker50
        ),

        OnboardingScreen(
            tag = listOf(ArticleTag.PROPELLER),
            textRes = R.string.onboarding_text_12,
            bgColorRes = R.color.onboarding_color_5,
            drawableRes = R.drawable.piperpa31
        )
    )

    private var filtredArticles = screens



    fun onConfirm(filteredData : Array<Boolean>) {
        val tmpModelList = ArrayList<OnboardingScreen>()
        for (e in screens)
        {
            if ((filteredData[0] && e.tag.contains(ArticleTag.HELICOPTER)) ||
                (filteredData[1] && e.tag.contains(ArticleTag.JET)) ||
                (filteredData[2] && e.tag.contains(ArticleTag.PROPELLER))) {
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
            Tablayout.getTabAt((0 until 7).random())?.orCreateBadge?.apply { // повесить бейджик (уведомление)
                number = getNumber()+1 // сколько уведомление
                badgeGravity = BadgeDrawable.BOTTOM_END // расположение бейджика
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
            ChoiseDialogFragment.newInstance(filterData).show(childFragmentManager, "choisedialogfragment ")
        }

    }

}