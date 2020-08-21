package com.nikasov.weatherapp.ui.bindings

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.nikasov.weatherapp.R
import com.nikasov.weatherapp.data.remote.model.WeatherResult
import com.nikasov.weatherapp.utils.DateUtils
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*

//@BindingAdapter("load_image")
//fun bindingImage(imageView : ImageView, imageUrl : String?) {
//    imageUrl?.let{
//        Glide
//            .with(imageView.context)
//            .load(imageUrl)
//            .centerCrop()
//            .dontAnimate()
//            .placeholder(R.drawable.recipe_holder)
//            .into(imageView)
//    }
//}

@BindingAdapter("date_setter")
fun bindingDate(textView : TextView, date: Int?) {
    date?.let {
        val dateInMillis =  date.toLong()
        val currentDate = Calendar.getInstance()
        currentDate.timeInMillis = dateInMillis*1000

        val str =  DateUtils.formatDate(
            Calendar.getInstance().time,
            textView.context.resources.getString(R.string.day_format))

        textView.text = str
    }
}

@BindingAdapter("avg_temperature")
fun bindingAvgTemperature(textView : TextView, weatherResult: WeatherResult?) {
    weatherResult?.let { result ->
        val str = "${(result.main.temp_min).toInt()}°c / ${(result.main.temp_max).toInt()}°c"
        textView.text = str
    }
}

@BindingAdapter("temperature")
fun bindingTemperature(textView : TextView, weatherResult: WeatherResult?) {
    weatherResult?.let {
        val str = textView.context.resources.getString(R.string.c)
        textView.text = str
    }
}

@BindingAdapter("double_to_int_convert")
fun bindingIntText(textView : TextView, double: Double?) {
    double?.let{
        textView.text = double.toInt().toString()
    }
}

@BindingAdapter("wind_speed")
fun bindingWindSpeed(textView : TextView, windSpeed: Double?) {
    windSpeed?.let{
        val str = "${windSpeed.toInt()} ${textView.context.resources.getString(R.string.wind_speed)}"
        textView.text = str
    }
}

@BindingAdapter("pressure")
fun bindingPressure(textView : TextView, pressure: Int?) {
    pressure?.let{
        val str = "${pressure.toInt()} ${textView.context.resources.getString(R.string.pressure)}"
        textView.text = str
    }
}

@BindingAdapter("humidity")
fun bindingHumidity(textView : TextView, pressure: Int?) {
    pressure?.let{
        val str = "${pressure.toInt()} %"
        textView.text = str
    }
}

@BindingAdapter("temperature_feel_like")
fun bindingTemperatureFeelLike(textView : TextView, temperature: Double?) {
    temperature?.let{
        val str = "${temperature.toInt()} ${textView.context.resources.getString(R.string.c)}"
        textView.text = str
    }
}

@BindingAdapter("double_convert")
fun bindingDoubleText(textView : TextView, double : Double?) {
    double?.let{
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        val formatDouble = df.format(double)
        textView.text = formatDouble
    }
}