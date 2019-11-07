package com.example.countryinfo.common.network

import android.util.Log
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.example.countryinfo.CountryInfoApplication
import com.example.countryinfo.interfaces.ServerRequestHandlerInterface
import org.json.JSONObject


class ServerRequestHandler : ServerRequestHandlerInterface {
    val TAG = ServerRequestHandler::class.java.simpleName

    /**
     *
     * Create a volley get request and pass the response back
     * @param path - Url path to get data from
     * @param completionHandler - Provide response as a callback to the calling function
     *
     */
    override fun get(path: String, completionHandler: (response: JSONObject?) -> Unit) {
        // Json request to get data from server
        val jsonObjReq = object : JsonObjectRequest(Request.Method.GET, path, null,
            Response.Listener<JSONObject> { response ->
                Log.d(TAG, "/post request OK! Response: $response")
                completionHandler(response)
            },
            Response.ErrorListener { error ->
                VolleyLog.e(TAG, "/post request fail! Error: ${error.message}")
                completionHandler(null)
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
        }

        // Add the jsonRequest to volley's request queue
        CountryInfoApplication.instance?.addToRequestQueue(jsonObjReq, TAG)
    }
}