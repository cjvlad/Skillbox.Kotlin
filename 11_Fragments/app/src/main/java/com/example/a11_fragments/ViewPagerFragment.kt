import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.a11_fragments.HingeTransformation
import com.example.a11_fragments.R
import com.example.a11_fragments.TagsFilterDialog
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_view_pager.*
import kotlin.random.Random

class ViewPagerFragment : Fragment(R.layout.fragment_view_pager), TagsFilterDialog.TagsFilterDialogListener, ArticleFragment.OnGenerateEventListener {

    companion object {
        private const val FORM_STATE = "Form state"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        for (i in 0..filteredArticles.size) {
            outState.putInt("badge$i", tabLayout.getTabAt(i)?.badge?.number ?: 0)
        }
        outState.putBooleanArray(FORM_STATE, checkedTags)
    }

    private val articles = listOf(
        Article(
            tags = listOf(ArticleTag.HELICOPTER),
            textRes = R.string.onboarding_text_1,
            bgColorRes = R.color.onboarding_color_1,
            titleRes = R.string.article_1_title,
            imageRes = R.drawable.as350
        ),

        Article(
            tags = listOf(ArticleTag.HELICOPTER),
            textRes = R.string.onboarding_text_2,
            bgColorRes = R.color.onboarding_color_2,
            titleRes = R.string.article_2_title,
            imageRes = R.drawable.bell412
        ),

        Article(
            tags = listOf(ArticleTag.HELICOPTER),
            textRes = R.string.onboarding_text_3,
            bgColorRes = R.color.onboarding_color_3,
            titleRes = R.string.article_3_title,
            imageRes = R.drawable.mi8
        ),

        Article(
            tags = listOf(ArticleTag.HELICOPTER),
            textRes = R.string.onboarding_text_4,
            bgColorRes = R.color.onboarding_color_4,
            titleRes = R.string.article_4_title,
            imageRes = R.drawable.milmi26
        ),

        Article(
            tags = listOf(ArticleTag.JET),
            textRes = R.string.onboarding_text_5,
            bgColorRes = R.color.onboarding_color_5,
            titleRes = R.string.article_1_title,
            imageRes = R.drawable.a320
        ),

        Article(
            tags = listOf(ArticleTag.JET),
            textRes = R.string.onboarding_text_6,
            bgColorRes = R.color.onboarding_color_3,
            titleRes = R.string.article_2_title,
            imageRes = R.drawable.b737
        ),

        Article(
            tags = listOf(ArticleTag.JET),
            textRes = R.string.onboarding_text_7,
            bgColorRes = R.color.onboarding_color_1,
            titleRes = R.string.article_3_title,
            imageRes = R.drawable.b777
        ),

        Article(
            tags = listOf(ArticleTag.JET),
            textRes = R.string.onboarding_text_8,
            bgColorRes = R.color.onboarding_color_2,
            titleRes = R.string.article_4_title,
            imageRes = R.drawable.eclipse550
        ),

        Article(
            tags = listOf(ArticleTag.PROPELLER),
            titleRes = R.string.article_1_title,
            textRes = R.string.onboarding_text_9,
            bgColorRes = R.color.onboarding_color_3,
            imageRes = R.drawable.an2
        ),

        Article(
            tags = listOf(ArticleTag.PROPELLER),
            titleRes = R.string.article_2_title,
            textRes = R.string.onboarding_text_10,
            bgColorRes = R.color.onboarding_color_4,
            imageRes = R.drawable.cessna177
        ),

        Article(
            tags = listOf(ArticleTag.PROPELLER),
            titleRes = R.string.article_3_title,
            textRes = R.string.onboarding_text_11,
            bgColorRes = R.color.onboarding_color_5,
            imageRes = R.drawable.fokker50
        ),
        Article(
            tags = listOf(ArticleTag.PROPELLER),
            titleRes = R.string.article_4_title,
            textRes = R.string.onboarding_text_12,
            bgColorRes = R.color.onboarding_color_5,
            imageRes = R.drawable.piperpa31
        )
    )

    private var filteredArticles = articles

    override fun onGenerateEventClick() {
        val tab = tabLayout.getTabAt(Random.nextInt(0, filteredArticles.size))
        tab?.badge
            ?.let {
                it.number++
            } ?: tab?.orCreateBadge?.apply {
            number = 1
            badgeGravity = BadgeDrawable.TOP_END
        }
    }

    private var checkedTags: BooleanArray? = ArticleTag.values().map { true }.toBooleanArray()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            checkedTags = savedInstanceState.getBooleanArray(FORM_STATE)
            onApplyDialog(checkedTags)

            for (i in 0..filteredArticles.size) {
                val num = savedInstanceState.getInt("badge$i")
                if (num != 0) {
                    tabLayout.getTabAt(i)?.orCreateBadge?.apply {
                        number = num
                        badgeGravity = BadgeDrawable.TOP_END
                    }
                }
            }
        } else {
            setDataToViewPager(articles)
        }


        viewPager.setPageTransformer(HingeTransformation())

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.removeBadge()
            }
        })

        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.filterItem -> {
                    fragmentManager?.let {
                        val tagsFilterDialog = TagsFilterDialog()
                            .withArgs {
                                putBooleanArray(TagsFilterDialog.KEY_CHECKED_ITEMS, checkedTags?.copyOf())
                            }
                        tagsFilterDialog.listener = this
                        tagsFilterDialog.show(it, "tagsFilter")
                    }

                    true
                }
                else -> false
            }
        }
    }

    override fun onApplyDialog(checkedTags: BooleanArray?) {
        this.checkedTags = checkedTags

        val selectedTags = ArticleTag.values().filterIndexed { index, _ ->
            checkedTags?.get(index) == true
        }

        filteredArticles = articles.filter { article -> article.tags.any { selectedTags.contains(it) } }

        setDataToViewPager(filteredArticles)
    }

    private fun setDataToViewPager(articles: List<Article>) {
        val adapter = ArticleAdapter(articles, this)
        viewPager.adapter = adapter
        springDotsIndicator.setViewPager2(viewPager)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getString(articles[position].titleRes)
        }.attach()
    }
}
