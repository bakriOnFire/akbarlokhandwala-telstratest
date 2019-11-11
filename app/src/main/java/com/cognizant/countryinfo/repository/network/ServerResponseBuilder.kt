package com.cognizant.countryinfo.repository.network

import com.cognizant.countryinfo.common.Status
import com.cognizant.countryinfo.model.CountryInfoResponse
import com.cognizant.countryinfo.model.ServerResponse
import retrofit2.Response

class ServerResponseBuilder {

    companion object {
        fun create(error: Throwable?): ServerResponse {
            return ServerResponse(Status.Error, null, error, error?.message ?: "unknown error")
        }

        fun create(response: Response<CountryInfoResponse>): ServerResponse {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ServerResponse(Status.Error, null, null, "Empty Data")
                } else {
                    ServerResponse(Status.Success, response.body(), null, "success")

                }
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ServerResponse(Status.Error, null, null, errorMsg ?: "unknown error")
            }
        }
    }
}