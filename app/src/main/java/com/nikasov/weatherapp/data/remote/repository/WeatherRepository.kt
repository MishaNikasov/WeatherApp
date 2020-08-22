package com.nikasov.weatherapp.data.remote.repository

import com.nikasov.weatherapp.data.remote.model.forecast.ForecastResult
import com.nikasov.weatherapp.data.remote.model.weather.WeatherResult
import com.nikasov.weatherapp.data.remote.service.WeatherApi
import io.reactivex.Flowable
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) {

    fun getWeather(location : String) : Flowable<WeatherResult> {
        return weatherApi.getWeather(location)
    }

    fun getWeatherByCoordinates(
       lat : String,
       lon : String
    ) : Flowable<WeatherResult> {
        return weatherApi.getWeatherByCoordinates(lat, lon)
    }

    fun getFiveDaysWeather(
        lat : String,
        lon : String,
        cnt: Int
    ) : Flowable<ForecastResult> {
        return weatherApi.getFiveDaysWeather(lat, lon, cnt)
    }

}