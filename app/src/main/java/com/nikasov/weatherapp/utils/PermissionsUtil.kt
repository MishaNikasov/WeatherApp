package com.nikasov.weatherapp.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import com.nikasov.weatherapp.common.Constants
import pub.devrel.easypermissions.EasyPermissions


object PermissionsUtil {

    fun hasLocationPermission(context: Context): Boolean {

        val perms = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        return EasyPermissions.hasPermissions(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    fun requestLocationPermission(activity: Activity) {
        EasyPermissions.requestPermissions(
            activity,
            "Just accept this",
            Constants.LOCATION_PERMISSION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }
}