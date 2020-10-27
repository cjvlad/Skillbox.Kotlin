package com.example.a10_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(R.layout.fragment_detail) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        inputText.text = requireArguments().getString(KEY_LOGIN)
    }

    companion object {
        private const val KEY_LOGIN = "key_login"

        fun newInstance(text: String): DetailFragment {
            return DetailFragment().withArguments {
                putString(KEY_LOGIN, text)
            }
        }
    }
}
