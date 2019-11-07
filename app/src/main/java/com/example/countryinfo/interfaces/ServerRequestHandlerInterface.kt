package com.example.countryinfo.interfaces

import android.graphics.Bitmap
import org.json.JSONObject

/**
 *
 * Interface holding server request method declaration
 *
 */
interface ServerRequestHandlerInterface {
    fun get(path: String, completionHandler: (response: JSONObject?) -> Unit)
}