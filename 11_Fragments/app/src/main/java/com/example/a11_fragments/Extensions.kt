import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment

fun <T : Fragment> T.withArgs(args: Bundle.() -> Unit) : T {
    return apply {
        arguments = Bundle().apply(args)
    }
}

fun <T: Activity> T.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}