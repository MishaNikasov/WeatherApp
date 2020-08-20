package com.nikasov.weatherapp.utils

import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale

object TransitionUtils {

    fun getContainerTransform(): MaterialContainerTransform {
        val transition = MaterialContainerTransform()
        transition.duration = 350
        return transition
    }


    fun getContainerTransform(isGrowing: Boolean): MaterialElevationScale {
        val transition = MaterialElevationScale(isGrowing)
        transition.duration = 350
        return transition
    }

}