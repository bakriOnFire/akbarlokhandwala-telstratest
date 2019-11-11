package com.cognizant.countryinfo.repository

import android.util.Log
import com.cognizant.countryinfo.common.ServerConstants
import com.cognizant.countryinfo.model.CountryInfoResponse
import com.cognizant.countryinfo.model.ServerResponse
import com.cognizant.countryinfo.repository.network.CountryInfoService
import com.cognizant.countryinfo.repository.network.ServerResponseBuilder
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
    val retrofitClient = Retrofit.Builder()
        .baseUrl(ServerConstants.GET_COUNTRY_INFO_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofitClient.create(CountryInfoService::class.java)

    /**
     *
     * get country info from server using retrofit
     * @param completionHandler - Provide response as a callback to the calling function
     *
     */
    fun getCountryInfoHttp(completionHandler: (response: ServerResponse?) -> Unit) {

        val call = service.getCountryInfoService()
        call.enqueue(object : Callback<CountryInfoResponse> {
            override fun onResponse(
                call: Call<CountryInfoResponse>?,
                response: Response<CountryInfoResponse>?
            ) {
                val isSuccess = response?.isSuccessful
                if(isSuccess != null  && isSuccess)
                {
                    completionHandler(ServerResponseBuilder.create(response))
                }
            }

            override fun onFailure(call: Call<CountryInfoResponse>?, t: Throwable?) {
                Log.d(tag, t?.message)

                completionHandler(ServerResponseBuilder.create(t))
            }
        })
    }
}