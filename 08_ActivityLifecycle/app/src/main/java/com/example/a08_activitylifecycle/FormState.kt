package com.example.a08_activitylifecycle
import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FormState(
    var valid: Boolean,
    val message: String):Parcelable
