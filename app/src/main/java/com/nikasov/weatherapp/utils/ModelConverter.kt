package com.nikasov.weatherapp.utils

import android.content.Context
import com.nikasov.weatherapp.R
import com.nikasov.weatherapp.data.local.model.WeatherModel
import com.nikasov.weatherapp.data.remote.model.WeatherResult
import java.util.*

object ModelConverter {

    fun remoteToLocal(weatherResult: WeatherResult, context: Context): WeatherModel {
        val weather = WeatherModel()

        weather.city = "${weatherResult.name}, ${weatherResult.sys.country}"
        weather.type = weatherResult.weather[0].main
        weather.metric = context.resources.getString(R.string.c)
        weather.avgTemp = "${(weatherResult.main.temp_min).toInt()}°c / ${(weatherResult.main.temp_max).toInt()}°c"
        weather.date = getDate(weatherResult.dt, context)
        weather.windSpeed = "${weatherResult.wind.speed.toInt()} ${context.resources.getString(R.string.wind_speed)}"
        weather.feelLikeTemp = "${weatherResult.main.feels_like.toInt()} ${context.resources.getString(R.string.c)}"
        weather.pressure = "${weatherResult.main.pressure} ${context.resources.getString(R.string.pressure)}"
        weather.humidity = "${weatherResult.main.humidity} %"
        weather.temperature = "${weatherResult.main.temp.toInt()}"

        return weather
    }


    private fun getDate(date: Int, context: Context): String{

        val dateInMillis =  date.toLong()
        val currentDate = Calendar.getInstance()
        currentDate.timeInMillis = dateInMillis*1000

        return DateUtils.formatDate(
            Calendar.getInstance().time,
            context.resources.getString(R.string.day_format))
    }

}