package com.nikasov.weatherapp.utils

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.nikasov.weatherapp.R

object AnimationUtil {

    fun fading(view: View): Animation {
        view.visibility = View.VISIBLE
        return getAnimation(view.context, R.anim.fading)
    }

    fun scale(view: View): Animation  {
        view.visibility = View.VISIBLE
        return getAnimation(view.context, R.anim.scale)
    }
    fun moveY(view: View): Animation  {
        view.visibility = View.VISIBLE
        return getAnimation(view.context, R.anim.move_y)
    }

    private fun getAnimation(context: Context, resId : Int) : Animation{
        return AnimationUtils.loadAnimation(context, resId)
    }

}