package com.example.countryinfo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.countryinfo.model.Rows
import com.example.countryinfo.model.ServerResponse
import com.example.countryinfo.repository.CountryInfoRepository


open class CountryInfoViewModel(application: Application): AndroidViewModel(application) {

    private val serverResponse: MutableLiveData<ServerResponse> = MutableLiveData()
    private val countryInfoRepository = CountryInfoRepository()

    /**
     *
     * Initialize view model to get get country info data from server
     *
     */
    fun init() {
        getCountryInfoFromRepository()
    }

    /**
     *
     * Get country info from repository
     *
     */
    private fun getCountryInfoFromRepository() {
        countryInfoRepository.getCountryInfoHttp(
            completionHandler = {
                serverResponse.value = it
            }
        )
    }

    /**
     *
     * Return live data object of country info
     *
     */
    fun getData() : MutableLiveData<ServerResponse> {
        return serverResponse
    }
}