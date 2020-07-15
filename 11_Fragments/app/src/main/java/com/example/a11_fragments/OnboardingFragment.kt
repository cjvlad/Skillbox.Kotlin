import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.a11_fragments.R
import kotlinx.android.synthetic.main.fragment_article.*

class OnboardingFragment(): Fragment(R.layout.fragment_article) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textView.setText(requireArguments().getInt(KEY_TEXT))
        requireView().setBackgroundResource(requireArguments().getInt(KEY_COLOR))
        imageView.setImageResource(requireArguments().getInt(KEY_IMAGE))
    }

    companion object {
        private const val KEY_TEXT = "text"
        private const val KEY_IMAGE = "image"
        private const val KEY_COLOR = "color"

        fun newInstance(
            @StringRes textRes: Int,
            @ColorRes bgColorRes: Int,
            @DrawableRes drawableRes: Int
        ): OnboardingFragment {
            return OnboardingFragment().whithArguments {
                putInt(KEY_TEXT, textRes)
                putInt(KEY_IMAGE, drawableRes)
                putInt(KEY_COLOR, bgColorRes)
            }
        }
    }
}