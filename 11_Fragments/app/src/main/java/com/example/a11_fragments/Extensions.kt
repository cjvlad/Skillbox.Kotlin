import android.os.Bundle
import androidx.fragment.app.Fragment

fun <T: Fragment> T.whithArguments(action: Bundle.() -> Unit ): T {
    return apply{
        val args :Bundle = Bundle().apply(action)
        arguments = args
    }
}