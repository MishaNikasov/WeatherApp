package com.nikasov.weatherapp.data

import android.graphics.drawable.Drawable
import com.nikasov.weatherapp.R
import com.nikasov.weatherapp.data.local.model.DailyModel
import com.nikasov.weatherapp.data.local.model.ForecastModel
import com.nikasov.weatherapp.data.local.model.WeatherModel
import com.nikasov.weatherapp.data.remote.model.forecast.Forecast
import com.nikasov.weatherapp.data.remote.model.forecast.ForecastResult
import com.nikasov.weatherapp.data.remote.model.onecall.Daily
import com.nikasov.weatherapp.data.remote.model.weather.WeatherResult
import com.nikasov.weatherapp.utils.DateUtils
import com.nikasov.weatherapp.utils.ResourceProvider
import java.util.*

object ModelConverter {

    fun remoteWeatherToWeatherModel(weatherResult: WeatherResult, resourceProvider: ResourceProvider): WeatherModel {
        val weather = WeatherModel()

        weather.city = "${weatherResult.name}, ${weatherResult.sys.country}"
        weather.type = weatherResult.weather[0].description
        weather.metric = resourceProvider.getString(R.string.c)
        weather.avgTemp = "${(weatherResult.main.temp_min).toInt()}°c / ${(weatherResult.main.temp_max).toInt()}°c"
        weather.date = getDate(
            weatherResult.dt *1000,
            resourceProvider.getString(R.string.day_format)
        )
        weather.windSpeed = "${weatherResult.wind.speed.toInt()} ${resourceProvider.getString(R.string.wind_speed)}"
        weather.feelLikeTemp = "${weatherResult.main.feels_like.toInt()} ${resourceProvider.getString(R.string.c)}"
        weather.pressure = "${weatherResult.main.pressure} ${resourceProvider.getString(R.string.pressure)}"
        weather.humidity = "${weatherResult.main.humidity} %"
        weather.temperature = "${weatherResult.main.temp.toInt()}"
        weather.icon = getIcon(weatherResult.weather[0].icon, resourceProvider)

        return weather
    }

    fun remoteDailyToDailyModel(daily: Daily, resourceProvider: ResourceProvider): DailyModel {
        val model = DailyModel()
        model.weather = daily.weather[0].description
        model.date = getDate(
            daily.dt *1000,
            resourceProvider.getString(R.string.daily_format)
        )
        model.avgTemp = "${(daily.temp.min).toInt()}°c / ${(daily.temp.max).toInt()}°c"
        model.icon = getIcon(daily.weather[0].icon, resourceProvider)
        return model
    }

    fun remoteDailyToForecastModel(
        daily: Daily,
        resourceProvider: ResourceProvider
    ): ForecastModel {
        val forecastModel = ForecastModel()

        forecastModel.weather = daily.weather[0].main
        forecastModel.day = getDate(daily.dt*1000, resourceProvider.getString(R.string.daily_format))
        forecastModel.date = getDate(daily.dt*1000, resourceProvider.getString(R.string.date_format))
        forecastModel.tempMax = "${daily.temp.max.toInt()}°"
        forecastModel.tempMin = "${daily.temp.min.toInt()}°"
        forecastModel.icon = getIcon(daily.weather[0].icon, resourceProvider)

        forecastModel.windSpeed = "${daily.wind_speed.toInt()} ${resourceProvider.getString(R.string.wind_speed)}"
        forecastModel.feelLikeTemp = "${daily.feels_like.day.toInt()} ${resourceProvider.getString(R.string.c)}"
        forecastModel.pressure = "${daily.pressure} ${resourceProvider.getString(R.string.pressure)}"
        forecastModel.humidity = "${daily.humidity} %"

        forecastModel.sunset = getDate(daily.sunset.toLong() * 1000, resourceProvider.getString(R.string.sun_format))
        forecastModel.sunrise = getDate(daily.sunrise.toLong() * 1000, resourceProvider.getString(R.string.sun_format))

        return forecastModel
    }

    private fun getIcon(icon: String, resourceProvider: ResourceProvider): Drawable? {
        if (icon == "01d" || icon ==  "01n")
            return resourceProvider.getDrawable(R.drawable.ic_01d)
        else if (icon == "02d" || icon ==  "02n")
            return resourceProvider.getDrawable(R.drawable.ic_02d)
        else if (icon == "03d" || icon ==  "03n")
            return resourceProvider.getDrawable(R.drawable.ic_03d)
        else if (icon == "04d" || icon ==  "04n")
            return resourceProvider.getDrawable(R.drawable.ic_04d)
        else if (icon == "09d" || icon ==  "09n")
            return resourceProvider.getDrawable(R.drawable.ic_09d)
        else if (icon == "10d" || icon ==  "10n")
            return resourceProvider.getDrawable(R.drawable.ic_10d)
        else if (icon == "11d" || icon ==  "11n")
            return resourceProvider.getDrawable(R.drawable.ic_11d)
        else if (icon == "13d" || icon ==  "13n")
            return resourceProvider.getDrawable(R.drawable.ic_13d)
        else if (icon == "50d" || icon ==  "50n")
            return resourceProvider.getDrawable(R.drawable.ic_50d)
        else
            return resourceProvider.getDrawable(R.drawable.ic_01d)
    }

    private fun getDate(date: Long, type: String): String{
        return DateUtils.formatDate(
            Date(date),
            type
        )
    }
}