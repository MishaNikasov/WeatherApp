package com.nikasov.weatherapp.data.remote.model.forecast

data class ForecastResult(
    val daily: List<Daily>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)