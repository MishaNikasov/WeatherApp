package com.nikasov.weatherapp.data.remote.service

import com.nikasov.weatherapp.data.remote.model.onecall.OnecallResult
import com.nikasov.weatherapp.data.remote.model.weather.WeatherResult
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") location : String
    ) : WeatherResult

    @GET("weather")
    suspend fun getWeatherByCoordinates(
        @Query("lat") lat : String,
        @Query("lon") lon : String
    ) : WeatherResult

    @GET("onecall")
    suspend fun getDailyForecast(
        @Query("lat") lat : String,
        @Query("lon") lon : String,
        @Query("cnt") cnt : Int,
        @Query("exclude") exclude : String = "hourly,minutely,current"
    ) : OnecallResult

}