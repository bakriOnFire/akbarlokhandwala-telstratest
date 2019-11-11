package com.cognizant.countryinfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cognizant.countryinfo.model.ServerResponse
import com.cognizant.countryinfo.repository.CountryInfoRepository


open class CountryInfoViewModel(): ViewModel() {

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