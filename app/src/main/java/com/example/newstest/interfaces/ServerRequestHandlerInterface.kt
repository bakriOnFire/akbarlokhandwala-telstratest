package com.example.newstest.interfaces

import org.json.JSONObject

interface ServerRequestHandlerInterface {
    fun get(path: String, completionHandler: (response: JSONObject?) -> Unit)
}