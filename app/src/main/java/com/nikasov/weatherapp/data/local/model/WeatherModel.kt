package com.nikasov.weatherapp.data.local.model

data class WeatherModel(
    var city: String = "",
    var type: String = "",
    var metric: String = "",
    var avgTemp: String = "",
    var date: String = "",
    var windSpeed: String = "",
    var feelLikeTemp: String = "",
    var pressure: String = "",
    var humidity: String = "",
    var temperature: String = ""
)