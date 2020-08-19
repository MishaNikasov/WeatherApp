package com.nikasov.weatherapp.utils

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import javax.inject.Inject

class ResourceProvider @Inject constructor (var context : Context) {
    fun getString(resId : Int) : String{
        return context.resources.getString(resId)
    }

    fun getAnimation(resId : Int) : Animation{
        return AnimationUtils.loadAnimation(context, resId)
    }
}