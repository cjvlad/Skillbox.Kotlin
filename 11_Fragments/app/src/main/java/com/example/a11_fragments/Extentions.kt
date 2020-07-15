package com.example.a11_fragments
import android.os.Bundle
import androidx.fragment.app.Fragment

fun <T: Fragment> T.whithArguments(action: Bundle.() -> Unit ): T {
    return apply{
        val args :Bundle = Bundle().apply(action)
        arguments = args
    }
}

//fun <T: Activity> T.toast(message: String) {
//    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//}