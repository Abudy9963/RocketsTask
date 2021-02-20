package com.example.rocketstask.model


data class SecondStage(
    val burn_time_sec: Double,
    val engines: Double,
    val fuel_amount_tons: Double,
    val payloads: Payloads,
    val reusable: Boolean,
    val thrust: Thrust
)