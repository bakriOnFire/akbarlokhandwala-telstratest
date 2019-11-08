package com.example.countryinfo.common

import android.app.Activity
import android.app.AlertDialog
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
        return false;
    }

    /**
     *
     * Show an error alert with single button that will dismiss the currently running activity
     *
     * @param context - Application or activity context
     * @param title - Display title for alert dialog
     * @param message - Display message for alert dialog
     *
     */
    fun showErrorAlert(context: Context, title: String, message: String, onPositiveButtonClicked: () -> Unit) {
        val builder = AlertDialog.Builder(context)
        //set title for alert dialog
        builder.setTitle(title)
        //set message for alert dialog
        builder.setMessage(message)
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Ok"){dialogInterface, which ->
            onPositiveButtonClicked()
        }

        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}