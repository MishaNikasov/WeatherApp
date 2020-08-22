package com.nikasov.weatherapp.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import javax.inject.Inject

class ResourceProvider @Inject constructor (var context : Context) {

    fun getString(resId : Int) : String{
        return context.resources.getString(resId)
    }

    fun getDrawable(resId : Int) : Drawable? {
        return ContextCompat.getDrawable(context, resId)
    }

}