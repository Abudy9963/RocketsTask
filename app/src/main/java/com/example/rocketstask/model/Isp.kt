package com.example.rocketstask.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class Isp(
    val sea_level: Double,
    val vacuum: Double
)