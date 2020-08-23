package com.nikasov.weatherapp.data.remote.repository

import com.nikasov.weatherapp.data.remote.model.forecast.ForecastResult
import com.nikasov.weatherapp.data.remote.model.weather.WeatherResult
import com.nikasov.weatherapp.data.remote.service.WeatherApi
import io.reactivex.Flowable
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

    suspend fun getFiveDaysWeather(
        lat : String,
        lon : String,
        cnt: Int
    ) : ForecastResult {
        return weatherApi.getFiveDaysWeather(lat, lon, cnt)
    }

}