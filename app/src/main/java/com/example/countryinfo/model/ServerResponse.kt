package com.example.countryinfo.model

import com.example.countryinfo.common.Status
import retrofit2.Response

data class ServerResponse(val status: Status, val successData: CountryInfoBaseResponse?, val errorData: Throwable?, val message: String?)