package com.nikasov.weatherapp.data.remote.model.onecall

data class OnecallResult(
    val daily: List<Daily>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)