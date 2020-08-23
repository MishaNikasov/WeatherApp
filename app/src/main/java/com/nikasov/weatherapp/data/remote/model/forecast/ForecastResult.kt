package com.nikasov.weatherapp.data.remote.model.forecast

data class ForecastResult(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Forecast>,
    val message: Int
)