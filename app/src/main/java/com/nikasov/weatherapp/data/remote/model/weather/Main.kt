package com.nikasov.weatherapp.data.remote.model.weather

data class Main(
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double,
    val feels_like: Double
)