package com.example.newstest.common.network

import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import com.example.newstest.NewsTestApplication
import com.example.newstest.interfaces.ServerRequestHandlerInterface
import org.json.JSONObject

class ServerRequestHandler : ServerRequestHandlerInterface {
    val TAG = ServerRequestHandler::class.java.simpleName

    override fun get(path: String, completionHandler: (response: JSONObject?) -> Unit) {
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

        NewsTestApplication.instance?.addToRequestQueue(jsonObjReq, TAG)
    }
}