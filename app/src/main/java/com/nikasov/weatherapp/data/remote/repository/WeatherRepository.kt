package com.nikasov.weatherapp.data.remote.repository

import com.nikasov.weatherapp.data.remote.model.onecall.OnecallResult
import com.nikasov.weatherapp.data.remote.model.weather.WeatherResult
import com.nikasov.weatherapp.data.remote.service.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) {

    suspend fun getWeather(location : String) : WeatherResult {
        return weatherApi.getWeather(location)
    }

    suspend fun getWeatherByCoordinates(
       lat : String,
       lon : String
    ) : WeatherResult {
        return weatherApi.getWeatherByCoordinates(lat, lon)
    }

    suspend fun getDailyForecast(
        lat : String,
        lon : String,
        cnt: Int
    ) : OnecallResult {
        return weatherApi.getDailyForecast(lat, lon, cnt)
    }

}