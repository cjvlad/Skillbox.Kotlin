package com.example.a11_fragments

import android.os.Bundle

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_article.*

class OnboardingFragment(): Fragment(R.layout.fragment_article) { // создали фрагмент

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireView().setBackgroundResource(requireArguments().getInt(KEY_COLOR))
        textView.setText(requireArguments().getInt(KEY_TEXT))
        imageView.setImageResource(requireArguments().getInt(KEY_IMAGE))
    }

    companion object {
        private const val KEY_TEXT = "text"
        private const val KEY_IMAGE = "image"
        private const val KEY_COLOR = "color"

        fun newInstance(  // при вызове создается новый фрагмент
            @StringRes textRes: Int,
            @ColorRes bgColorRes: Int,
            @DrawableRes drawableRes: Int

        ): OnboardingFragment {
            return OnboardingFragment().withArguments {
                putInt(KEY_TEXT, textRes)
                putInt(KEY_COLOR, bgColorRes)
                putInt(KEY_IMAGE, drawableRes)
            }
        }

    }
}