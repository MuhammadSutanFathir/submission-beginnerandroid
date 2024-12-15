package com.example.submission

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Flag(
    val name: String,
    val description: String,
    val photo: Int,
    val ibukota : String,
    val tanggal : String,
) : Parcelable
