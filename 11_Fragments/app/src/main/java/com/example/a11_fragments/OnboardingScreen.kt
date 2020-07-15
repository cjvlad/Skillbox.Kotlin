package com.example.a11_fragments

import android.annotation.SuppressLint
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

@SuppressLint("SupportAnnotationUsage")
data class OnboardingScreen(
    @StringRes val tag: List<TypeOfPlanes>,
    @StringRes val textRes: Int,
    @ColorRes val bgColorRes: Int,
    @DrawableRes val drawableRes: Int
)