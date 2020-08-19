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

}