package com.nikasov.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun formatDate(date : Date, type : String) : String {
        val simpleDateFormat = SimpleDateFormat(type, Locale.getDefault())
        return simpleDateFormat.format(date)
    }

}