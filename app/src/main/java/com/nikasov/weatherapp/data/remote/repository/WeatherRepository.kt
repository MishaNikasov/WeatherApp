package com.nikasov.weatherapp.data.remote.repository

import com.nikasov.weatherapp.data.remote.model.WeatherResult
import com.nikasov.weatherapp.data.remote.service.WeatherApi
import io.reactivex.Flowable
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) {

    fun getWeather(location : String) : Flowable<WeatherResult> {
        return weatherApi.getWeather(location)
    }

}