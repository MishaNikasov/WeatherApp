package com.nikasov.weatherapp.data.remote.service

import com.nikasov.weatherapp.data.remote.model.WeatherResult
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    fun getWeather(
        @Query("q") location : String
    ) : Flowable<WeatherResult>

    @GET("weather")
    fun getWeatherByCoordinates(
        @Query("lat") lat : String,
        @Query("lon") lon : String
    ) : Flowable<WeatherResult>

}