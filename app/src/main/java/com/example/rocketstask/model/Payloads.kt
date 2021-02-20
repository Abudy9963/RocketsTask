package com.example.rocketstask.model

import android.os.Parcelable
import com.example.rocketstask.model.CompositeFairing
import kotlinx.android.parcel.Parcelize


data class Payloads(
    val composite_fairing: CompositeFairing,
    val option_1: String,
    val option_2: String
)