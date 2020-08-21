package com.nikasov.weatherapp.ui.fragment.root

import android.view.ViewGroup
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikasov.weatherapp.data.remote.model.WeatherResult
import com.nikasov.weatherapp.data.remote.repository.WeatherRepository
import com.nikasov.weatherapp.utils.AnimationUtil
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class RootViewModel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    var isLoading = MutableLiveData<Boolean>(false)
    var weather = MutableLiveData<WeatherResult>()

    private fun getWeatherByCity(location: String) {
        weatherRepository.getWeather(location)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<WeatherResult> {
                override fun onComplete() {
                }
                override fun onSubscribe(d: Disposable) {
                }
                override fun onNext(result: WeatherResult) {
                    setWeather(result)
                }
                override fun onError(e: Throwable) {
                    Timber.d("onError: ${e.localizedMessage}")
                }
            })
    }

    fun getWeatherByCoordinates(
        lat : String,
        lon : String
    ) {
        weatherRepository.getWeatherByCoordinates(lat, lon)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<WeatherResult> {
                override fun onSubscribe(d: Disposable) {
                    isLoading.postValue(true)
                }
                override fun onNext(result: WeatherResult) {
                    setWeather(result)
                }
                override fun onComplete() {
                    isLoading.postValue(false)
                }
                override fun onError(e: Throwable) {
                    Timber.d("onError: ${e.localizedMessage}")
                }
            })
    }

    // add map

    fun onSearchClick(weatherBlock: ViewGroup, location: String) {
        weatherBlock.animation = AnimationUtil.fading(weatherBlock)
        getWeatherByCity(location)
    }

    private fun setWeather(weatherResult: WeatherResult) {
        weather.postValue(weatherResult)
    }
}