package com.nikasov.weatherapp.data.local.model

import com.google.gson.annotations.SerializedName

data class CityModel (
    @SerializedName("name")
    var city: String
)