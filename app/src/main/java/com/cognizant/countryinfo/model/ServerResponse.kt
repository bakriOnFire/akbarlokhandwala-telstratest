package com.cognizant.countryinfo.model

import com.cognizant.countryinfo.common.Status

data class ServerResponse(val status: Status, val successData: CountryInfoResponse?, val errorData: Throwable?, val message: String?)