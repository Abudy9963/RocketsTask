package com.example.rocketstask.model


data class FirstStage(
    val burn_time_sec: Double,
    val engines: Double,
    val fuel_amount_tons: Double,
    val reusable: Boolean,
    val thrust_sea_level: ThrustSeaLevelX,
    val thrust_vacuum: ThrustVacuumX
)