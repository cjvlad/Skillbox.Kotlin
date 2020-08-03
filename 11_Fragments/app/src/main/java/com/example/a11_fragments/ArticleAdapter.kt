import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ArticleAdapter(
    private val articles: List<Article>,
    activity: FragmentActivity,
    private val callback: ArticleFragment.OnGenerateEventListener
): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun createFragment(position: Int): Fragment {
        val article = articles[position]
        val articleFragment = ArticleFragment.newInstance(article.imageRes, article.textRes)

        articleFragment.setOnHeadlineSelectedListener(callback)

        return articleFragment
    }
}
