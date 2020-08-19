package com.nikasov.weatherapp.ui.fragment.root

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikasov.weatherapp.data.remote.model.WeatherResult
import com.nikasov.weatherapp.data.remote.repository.WeatherRepository
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class RootViewModel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    var cityName = MutableLiveData<String>()
    var weatherType = MutableLiveData<String>()
    var weatherDescription = MutableLiveData<String>()
    var temperature = MutableLiveData<String>()
    var avgTemperature = MutableLiveData<String>()

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

    fun onSearchClick(location: String) {
        getWeatherByCity(location)
    }

    private fun setWeather(weatherResult: WeatherResult) {
        cityName.postValue(weatherResult.name)
        weatherType.postValue(weatherResult.weather[0].main)
        weatherDescription.postValue(weatherResult.weather[0].description)
        temperature.postValue("${weatherResult.main.temp} °C")
        avgTemperature.postValue("${weatherResult.main.temp_min}°c/${weatherResult.main.temp_max}°c")

        Timber.d("weatherResult: $cityName, $weatherType, $weatherDescription")
    }
}