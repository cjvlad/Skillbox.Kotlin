package com.example.a10_fragments

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FormState(
    var valid: Boolean,
    val message: String): Parcelable