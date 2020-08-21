package com.nikasov.weatherapp.utils

import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialSharedAxis

object TransitionUtils {

    fun getContainerTransform(): MaterialContainerTransform {
        val transition = MaterialContainerTransform()
        transition.duration = 350
        return transition
    }

    fun getElevationScale(isGrowing: Boolean): MaterialElevationScale {
        val transition = MaterialElevationScale(isGrowing)
        transition.duration = 350
        return transition
    }

    fun getTransitionBackward(direction: Boolean): MaterialSharedAxis {

        val materialSharedAxis = if (direction) {
            MaterialSharedAxis(MaterialSharedAxis.X, true)
        } else {
            MaterialSharedAxis(MaterialSharedAxis.X, false)
        }
        materialSharedAxis.duration = 350

        return materialSharedAxis
    }

}