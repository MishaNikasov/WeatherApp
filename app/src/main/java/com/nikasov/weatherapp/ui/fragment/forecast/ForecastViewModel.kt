package com.nikasov.weatherapp.ui.fragment.forecast

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikasov.weatherapp.data.ModelConverter
import com.nikasov.weatherapp.data.local.model.ForecastModel
import com.nikasov.weatherapp.data.remote.repository.WeatherRepository
import com.nikasov.weatherapp.utils.ResourceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ForecastViewModel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository,
    private val resourceProvider: ResourceProvider
): ViewModel() {

    val forecastList = MutableLiveData<List<ForecastModel>>()

    fun getForecastList(lat : String, lon : String) {
        viewModelScope.launch (Dispatchers.IO) {
            val list = arrayListOf<ForecastModel>()
            val forecast = weatherRepository.getDailyForecast(lat, lon, 3)
            Timber.d("$lat, $lon")

            forecast.daily.forEach {
                val model = ModelConverter.remoteDailyToForecastModel(it, resourceProvider)
                list.add(model)
            }

            forecastList.postValue(list)
        }
    }
}