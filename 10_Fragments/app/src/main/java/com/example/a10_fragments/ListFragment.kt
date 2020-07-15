package com.example.a10_fragments
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.fragment.app.Fragment

class ListFragment: Fragment(R.layout.fragment_list) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view.let { it as ViewGroup }
            .children
            .mapNotNull { it as? TextView }
            .forEach { textView ->
                textView.setOnClickListener {
                    onTextClick(textView.text.toString())
                }
            }
    }

    private fun onTextClick(text: String) {
        (parentFragment as? ItemSelectListener)?.onItemSelected(text)

    }
}