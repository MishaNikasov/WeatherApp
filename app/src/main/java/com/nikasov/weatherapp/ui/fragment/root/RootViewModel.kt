package com.nikasov.weatherapp.ui.fragment.root

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikasov.weatherapp.common.Constants
import com.nikasov.weatherapp.data.ModelConverter
import com.nikasov.weatherapp.data.local.model.DailyModel
import com.nikasov.weatherapp.data.local.model.WeatherModel
import com.nikasov.weatherapp.data.remote.model.forecast.ForecastResult
import com.nikasov.weatherapp.data.remote.model.weather.WeatherResult
import com.nikasov.weatherapp.data.remote.repository.WeatherRepository
import com.nikasov.weatherapp.utils.ResourceProvider
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class RootViewModel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>(false)
    val weather = MutableLiveData<WeatherModel>()
    val dailyList = MutableLiveData<List<DailyModel>>()

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

        weatherRepository.getFiveDaysWeather(lat, lon, Constants.FORECAST_DAYS)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<ForecastResult> {
                override fun onSubscribe(d: Disposable) {
                    isLoading.postValue(true)
                }
                override fun onComplete() {
                    isLoading.postValue(false)
                }
                override fun onError(e: Throwable) {
                    Timber.d("onError: ${e.localizedMessage}")
                }
                override fun onNext(forecast: ForecastResult) {
                    val modelsList = arrayListOf<DailyModel>()
                    for (i in 0..2) {
                        val day = forecast.daily[i]
                        val model = ModelConverter.remoteDailyToLocal(day, resourceProvider)
                        modelsList.add(model)
                        Timber.d("${day.toString()}")
                    }
                    dailyList.postValue(modelsList)
                }
            })
    }

    private fun setWeather(weatherResult: WeatherResult) {
        weather.postValue(ModelConverter.remoteWeatherToLocal(weatherResult, resourceProvider))
    }
}