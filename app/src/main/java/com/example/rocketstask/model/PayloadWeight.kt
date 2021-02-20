package com.example.rocketstask.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class PayloadWeight(
    val id: String,
    val kg: Double,
    val lb: Double,
    val name: String
)