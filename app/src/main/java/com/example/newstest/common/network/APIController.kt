package com.example.newstest.common.network

import com.example.newstest.interfaces.ServerRequestHandlerInterface
import org.json.JSONObject

class APIController constructor(serviceInjection: ServerRequestHandlerInterface): ServerRequestHandlerInterface {
    private val service: ServerRequestHandlerInterface = serviceInjection

    override fun get(path: String, completionHandler: (response: JSONObject?) -> Unit) {
        service.get(path, completionHandler)
    }
}