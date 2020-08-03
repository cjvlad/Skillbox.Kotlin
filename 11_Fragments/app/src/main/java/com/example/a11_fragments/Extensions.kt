import android.os.Bundle
import androidx.fragment.app.Fragment

fun <T : Fragment> T.withArgs(args: Bundle.() -> Unit) : T {
    return apply {
        arguments = Bundle().apply(args)
    }
}