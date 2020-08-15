
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Article(
    @DrawableRes val imageRes: Int,
    @StringRes val textRes: Int,
    @StringRes val titleRes: Int,
    @ColorRes val bgColorRes: Int,
    val tags: List<ArticleTag>
)
