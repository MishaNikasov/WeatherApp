package com.nikasov.weatherapp.ui.fragment.forecast

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikasov.weatherapp.data.ModelConverter
import com.nikasov.weatherapp.data.local.model.ForecastModel
import com.nikasov.weatherapp.data.remote.repository.WeatherRepository
import com.nikasov.weatherapp.utils.ResourceProvider
import kotlinx.coroutines.launch

class ForecastViewModel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository,
    private val resourceProvider: ResourceProvider
): ViewModel() {

    val forecastList = MutableLiveData<List<ForecastModel>>()

    fun getForecastList(cityId: Int) {
        viewModelScope.launch {
            val list = arrayListOf<ForecastModel>()
            val forecast = weatherRepository.getForecastByCityId(cityId)

            forecast.list.forEach {
                val model = ModelConverter.remoteToForecast(it, resourceProvider)
                list.add(model)
            }
            forecastList.postValue(list)
        }
    }
}