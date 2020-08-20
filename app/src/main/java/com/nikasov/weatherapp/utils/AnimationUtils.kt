package com.nikasov.weatherapp.utils

import android.view.animation.Animation
import com.nikasov.weatherapp.R
import javax.inject.Inject

class AnimationUtils @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun fading(): Animation {
        return resourceProvider.getAnimation(R.anim.fading)
    }

}