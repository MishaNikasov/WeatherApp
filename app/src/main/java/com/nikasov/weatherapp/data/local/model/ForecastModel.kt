package com.nikasov.weatherapp.data.local.model


import android.graphics.drawable.Drawable

data class ForecastModel (

    var lat: String,
    var lon: String,
    var dateRaw: Long,

    var weather: String = "",
    var day: String = "",
    var date: String = "",
    var tempMin: String = "",
    var tempMax: String = "",
    var icon: Drawable? = null
)