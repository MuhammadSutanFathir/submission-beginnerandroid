package com.example.submission

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(
    val nameProfile: String,
    val mail: String,
    val photo: String?
) : Parcelable
