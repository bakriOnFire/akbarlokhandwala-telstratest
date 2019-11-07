package com.example.countryinfo.common.network

import com.example.countryinfo.interfaces.ServerRequestHandlerInterface
import org.json.JSONObject

/**
 *
 * Controller class that will divide the server calls from application
 *
 */
class APIController constructor(serviceInjection: ServerRequestHandlerInterface):
    ServerRequestHandlerInterface {
    private val service: ServerRequestHandlerInterface = serviceInjection

    /**
     *
     * Call get request from serverrequesthandler
     * @param path - Url path to get data from
     * @param completionHandler - Provide response as a callback to the calling function
     *
     */
    override fun get(path: String, completionHandler: (response: JSONObject?) -> Unit) {
        service.get(path, completionHandler)
    }
}