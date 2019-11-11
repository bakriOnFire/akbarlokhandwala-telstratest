package com.cognizant.countryinfo.repository.network

import com.cognizant.countryinfo.model.CountryInfoResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 *
 * Interface holding server request method declaration
 *
 */
interface CountryInfoService {
    @GET("s/2iodh4vg0eortkl/facts.js")
    fun getCountryInfoService() : Call<CountryInfoResponse>
}