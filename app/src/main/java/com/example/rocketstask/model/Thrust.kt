package com.example.rocketstask.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class Thrust(
    val kN: Double,
    val lbf: Double
)