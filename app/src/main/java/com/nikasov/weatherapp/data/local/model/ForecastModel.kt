package com.nikasov.weatherapp.data.local.model


import android.graphics.drawable.Drawable

data class ForecastModel (

    var weather: String = "",
    var day: String = "",
    var date: String = "",
    var tempMin: String = "",
    var tempMax: String = "",
    var icon: Drawable? = null,

    var windSpeed: String = "",
    var feelLikeTemp: String = "",
    var pressure: String = "",
    var humidity: String = "",

    var sunset: String = "",
    var sunrise: String = ""
)