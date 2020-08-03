import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.a11_fragments.R
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment: Fragment(R.layout.fragment_article) {
    lateinit var callback: OnGenerateEventListener

    fun setOnHeadlineSelectedListener(callback: OnGenerateEventListener) {
        this.callback = callback
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        articleImage.setImageResource(requireArguments().getInt(KEY_IMAGE))
        articleText.setText(requireArguments().getInt(KEY_TEXT))

        generateEventButton.setOnClickListener {
            callback.onGenerateEventClick()
        }
    }

    interface OnGenerateEventListener {
        fun onGenerateEventClick()
    }

    companion object {
        private const val KEY_IMAGE = "image_res"
        private const val KEY_TEXT = "text_res"

        fun newInstance(
            @DrawableRes imageRes: Int,
            @StringRes textRes: Int
        ): ArticleFragment {
            return ArticleFragment().withArgs {
                putInt(KEY_IMAGE, imageRes)
                putInt(KEY_TEXT, textRes)
            }
        }
    }
}