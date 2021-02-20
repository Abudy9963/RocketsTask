package com.example.rocketstask.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class ThrustSeaLevel(
    val kN: Double,
    val lbf: Double
)