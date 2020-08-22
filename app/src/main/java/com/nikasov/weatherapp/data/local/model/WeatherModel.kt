package com.nikasov.weatherapp.data.local.model

import android.graphics.drawable.Drawable

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
    var temperature: String = "",
    var icon: Drawable? = null
)