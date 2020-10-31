import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ArticleAdapter(
    private val articles: List<Article>,
    fragment: Fragment
): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun createFragment(position: Int): Fragment {
        val article = articles[position]
        return ArticleFragment.newInstance(
            textRes = article.textRes,
            bgColorRes = article.bgColorRes,
            imageRes = article.imageRes
        )
    }
}
