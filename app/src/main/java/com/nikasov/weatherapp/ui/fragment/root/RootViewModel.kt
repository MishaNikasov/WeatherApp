package com.nikasov.weatherapp.ui.fragment.root

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikasov.weatherapp.common.Constants
import com.nikasov.weatherapp.data.ModelConverter
import com.nikasov.weatherapp.data.local.model.DailyModel
import com.nikasov.weatherapp.data.local.model.WeatherModel
import com.nikasov.weatherapp.data.remote.model.onecall.OnecallResult
import com.nikasov.weatherapp.data.remote.model.weather.WeatherResult
import com.nikasov.weatherapp.data.remote.repository.WeatherRepository
import com.nikasov.weatherapp.utils.ResourceProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RootViewModel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    var view : View? = null
    var root : View? = null

    var cityId: Int? = null

    val isLoading = MutableLiveData<Boolean>(false)
    val weather = MutableLiveData<WeatherModel>()
    val dailyList = MutableLiveData<List<DailyModel>>()

    fun getWeatherByCoordinates(
        lat : String,
        lon : String
    ) {

        viewModelScope.launch {

            isLoading.postValue(true)

            val weatherResult = async {
                weatherRepository.getWeatherByCoordinates(lat, lon)
            }
            val forecastResult = async {
                weatherRepository.getFiveDaysWeather(lat, lon, Constants.FORECAST_DAYS)
            }

            setWeather(weatherResult.await())
            setForecast(forecastResult.await())

            isLoading.postValue(false)
        }
    }

    private fun setForecast(onecallResult: OnecallResult) {

        val modelsList = arrayListOf<DailyModel>()

        for (i in 0..2) {
            val day = onecallResult.daily[i]
            val model = ModelConverter.remoteDailyToLocal(day, resourceProvider)
            modelsList.add(model)
        }
        dailyList.postValue(modelsList)
    }

    private fun setWeather(weatherResult: WeatherResult) {
        cityId = weatherResult.id
        weather.postValue(ModelConverter.remoteWeatherToLocal(weatherResult, resourceProvider))
    }
}