package com.nikasov.weatherapp.data.remote.repository

import com.nikasov.weatherapp.data.remote.model.forecast.ForecastResult
import com.nikasov.weatherapp.data.remote.model.onecall.OnecallResult
import com.nikasov.weatherapp.data.remote.model.weather.WeatherResult
import com.nikasov.weatherapp.data.remote.service.WeatherApi
import retrofit2.http.Query
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
    ) : OnecallResult {
        return weatherApi.getFiveDaysWeather(lat, lon, cnt)
    }

    suspend fun getForecastByCityId(
       id : Int
    ) : ForecastResult {
        return weatherApi.getForecastByCityId(id)
    }


}