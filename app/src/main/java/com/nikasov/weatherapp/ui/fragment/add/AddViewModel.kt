package com.nikasov.weatherapp.ui.fragment.add

import android.view.ViewGroup
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikasov.weatherapp.data.remote.model.weather.WeatherResult
import com.nikasov.weatherapp.data.remote.repository.WeatherRepository
import com.nikasov.weatherapp.utils.AnimationUtil
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import timber.log.Timber

class AddViewModel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private fun getWeatherByCity(location: String) {
        viewModelScope.launch {
            weatherRepository.getWeather(location)
        }
    }

    fun onSearchClick(weatherBlock: ViewGroup, location: String) {
        weatherBlock.animation = AnimationUtil.fading(weatherBlock)
        getWeatherByCity(location)
    }
}