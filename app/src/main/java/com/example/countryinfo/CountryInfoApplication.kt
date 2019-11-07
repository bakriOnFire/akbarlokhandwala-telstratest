package com.example.countryinfo

import android.app.Application
import android.text.TextUtils
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 *
 * Android application class to initialize volley request queue
 *
 */
class CountryInfoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    // create a new singleton request queue
    val requestQueue: RequestQueue? = null
        get() {
            if (field == null) {
                return Volley.newRequestQueue(applicationContext)
            }
            return field
        }

    /**
     *
     * Add a new request to the volley request queue
     * @param request - Volley request object
     * @param tag - Request tag
     *
     */
    fun <T> addToRequestQueue(request: Request<T>, tag: String) {
        request.tag = if (TextUtils.isEmpty(tag)) TAG else tag
        requestQueue?.add(request)
    }

    companion object {
        private val TAG = CountryInfoApplication::class.java.simpleName
        @get:Synchronized var instance: CountryInfoApplication? = null
            private set
    }
}