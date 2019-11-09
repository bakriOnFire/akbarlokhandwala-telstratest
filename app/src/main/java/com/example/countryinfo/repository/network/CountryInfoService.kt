package com.example.countryinfo.repository.network

import com.example.countryinfo.model.CountryInfoBaseResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 *
 * Interface holding server request method declaration
 *
 */
interface CountryInfoService {
    @GET("s/2iodh4vg0eortkl/facts.js")
    fun getCountryInfoService() : Call<CountryInfoBaseResponse>
}