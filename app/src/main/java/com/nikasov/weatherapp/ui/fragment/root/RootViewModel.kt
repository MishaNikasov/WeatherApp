package com.nikasov.weatherapp.ui.fragment.root

import android.view.ViewGroup
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikasov.weatherapp.R
import com.nikasov.weatherapp.data.remote.model.WeatherResult
import com.nikasov.weatherapp.data.remote.repository.WeatherRepository
import com.nikasov.weatherapp.utils.DateUtils
import com.nikasov.weatherapp.utils.ResourceProvider
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*

class RootViewModel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    var cityName = MutableLiveData<String>()
    var weatherType = MutableLiveData<String>()
    var weatherDescription = MutableLiveData<String>()
    var temperature = MutableLiveData<String>()
    var avgTemperature = MutableLiveData<String>()
    var date = MutableLiveData<String>()

    private fun getWeatherByCity(location: String) {
        weatherRepository.getWeather(location)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<WeatherResult> {
                override fun onComplete() {
                    Timber.d("onComplete")
                }
                override fun onSubscribe(d: Disposable) {
                }
                override fun onNext(result: WeatherResult) {
                    setWeather(result)
                    Timber.d("onNext: ${result.name}")
                }
                override fun onError(e: Throwable) {
                    Timber.d("onError: ${e.localizedMessage}")
                }
            })
    }

    // weather by current coordinates
    // add map

    fun onSearchClick(weatherBlock: ViewGroup, location: String) {
        weatherBlock.animation = resourceProvider.getAnimation(R.anim.fading)
        getWeatherByCity(location)
    }

    private fun setWeather(weatherResult: WeatherResult) {

        cityName.postValue("${weatherResult.name}, ${weatherResult.sys.country}")
        date.postValue(DateUtils.formatDate(Calendar.getInstance().time, resourceProvider.getString(R.string.day_format)))
        weatherType.postValue(weatherResult.weather[0].main)
        weatherDescription.postValue(weatherResult.weather[0].description)
        temperature.postValue("${(weatherResult.main.temp).toInt()} °C")
        avgTemperature.postValue("${(weatherResult.main.temp_min).toInt()}°c / ${(weatherResult.main.temp_max).toInt()}°c")

        Timber.d("weatherResult: $cityName, $weatherType, $weatherDescription")
    }
}