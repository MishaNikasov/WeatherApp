package com.nikasov.weatherapp.utils

import com.google.android.material.transition.*
import com.nikasov.weatherapp.common.Constants

object TransitionUtils {

    fun getContainerTransform(): MaterialContainerTransform {
        val transition = MaterialContainerTransform()
        transition.duration = Constants.ANIM_DURATION
        return transition
    }

    fun getElevationScale(isGrowing: Boolean): MaterialElevationScale {
        val transition = MaterialElevationScale(isGrowing)
        transition.duration = Constants.ANIM_DURATION
        return transition
    }

    fun getFadeTransition(): MaterialFadeThrough {
        val transition = MaterialFadeThrough()
        transition.duration = Constants.ANIM_DURATION
        return transition
    }

    fun getHold(): Hold {
        val transition = Hold()
        transition.duration = Constants.ANIM_DURATION
        return transition
    }

    fun getTransitionBackward(direction: Boolean): MaterialSharedAxis {

        val materialSharedAxis = if (direction) {
            MaterialSharedAxis(MaterialSharedAxis.X, true)
        } else {
            MaterialSharedAxis(MaterialSharedAxis.X, false)
        }
        materialSharedAxis.duration = Constants.ANIM_DURATION

        return materialSharedAxis
    }

}