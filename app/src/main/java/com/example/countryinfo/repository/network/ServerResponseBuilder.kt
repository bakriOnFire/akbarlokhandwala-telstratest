package com.example.countryinfo.repository.network

import com.example.countryinfo.common.Status
import com.example.countryinfo.model.CountryInfoBaseResponse
import com.example.countryinfo.model.ServerResponse
import retrofit2.Response

class ServerResponseBuilder {

    companion object {
        fun create(error: Throwable?): ServerResponse {
            return ServerResponse(Status.ERROR, null, error, error?.message ?: "unknown error")
        }

        fun create(response: Response<CountryInfoBaseResponse>): ServerResponse {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ServerResponse(Status.ERROR, null, null, "Empty Data")
                } else {
                    ServerResponse(Status.SUCCESS, response.body(), null, "success")

                }
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ServerResponse(Status.ERROR, null, null, errorMsg ?: "unknown error")
            }
        }
    }
}