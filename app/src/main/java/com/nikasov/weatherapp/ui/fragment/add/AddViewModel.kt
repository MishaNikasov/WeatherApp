package com.nikasov.weatherapp.ui.fragment.add

import android.view.ViewGroup
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nikasov.weatherapp.data.remote.model.weather.WeatherResult
import com.nikasov.weatherapp.data.remote.repository.WeatherRepository
import com.nikasov.weatherapp.utils.AnimationUtil
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class AddViewModel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

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
                }
                override fun onError(e: Throwable) {
                    Timber.d("onError: ${e.localizedMessage}")
                }
            })
    }


    fun onSearchClick(weatherBlock: ViewGroup, location: String) {
        weatherBlock.animation = AnimationUtil.fading(weatherBlock)
        getWeatherByCity(location)
    }
}