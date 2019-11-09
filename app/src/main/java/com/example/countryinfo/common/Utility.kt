package com.example.countryinfo.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 *
 * Utility class for holding general purpose helper functions
 *
 */
class Utility {

    /**
     *
     * Check if the device is connected to internet or not
     * @param context - Application or activity context
     *
     */
    fun isConnectedToInternet(context: Context) : Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (cm != null) {
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            return activeNetwork?.isConnectedOrConnecting == true
        }
        return false
    }
}