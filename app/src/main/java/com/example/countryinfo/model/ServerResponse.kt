package com.example.countryinfo.model

import com.example.countryinfo.common.Status

data class ServerResponse(val status: Status, val successData: CountryInfoResponse?, val errorData: Throwable?, val message: String?)