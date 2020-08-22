package com.nikasov.weatherapp.data.local.model

import android.graphics.drawable.Drawable

data class DailyModel (
    var weather: String = "",
    var date: String = "",
    var avgTemp: String = "",
    var icon: Drawable? = null
)