package com.example.countryinfo.repository

import android.util.Log
import com.example.countryinfo.model.CountryInfoBaseResponse
import com.example.countryinfo.repository.network.CountryInfoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * Repository class that will divide the server calls from application
 *
 */
class CountryInfoRepository {

    private val tag = CountryInfoRepository::class.java.simpleName

    /**
     *
     * Call get request from serverrequesthandler
     * @param path - Url path to get data from
     * @param completionHandler - Provide response as a callback to the calling function
     *
     */
    fun get(path: String, completionHandler: (response: CountryInfoBaseResponse?) -> Unit) {
        val retrofitClient = Retrofit.Builder()
            .baseUrl(path)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofitClient.create(CountryInfoService::class.java)
        val call = service.getCountryInfoService()
        call.enqueue(object : Callback<CountryInfoBaseResponse> {
            override fun onResponse(
                call: Call<CountryInfoBaseResponse>?,
                response: Response<CountryInfoBaseResponse>?
            ) {
                if(response?.code() == 200)
                {
                    completionHandler(response.body())
                }
            }

            override fun onFailure(call: Call<CountryInfoBaseResponse>?, t: Throwable?) {
                Log.d(tag, t?.message)
            }
        })
    }
}