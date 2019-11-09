package com.example.countryinfo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.countryinfo.common.ServerConstants
import com.example.countryinfo.model.Rows
import com.example.countryinfo.repository.CountryInfoRepository

class CountryInfoViewModel(application: Application): AndroidViewModel(application) {

    private val countryInfoList: MutableLiveData<Pair<String, List<Rows>>> = MutableLiveData()
    private val countryInfoRepository = CountryInfoRepository()

    fun init() {
        getCountryInfoFromRepository()
    }

    private fun getCountryInfoFromRepository() {
        countryInfoRepository.get(ServerConstants.GET_NEWS_URL) { response ->

            countryInfoList.value = Pair(response!!.title, response!!.rows)
        }
    }

    fun getCountryInfoList() : MutableLiveData<Pair<String, List<Rows>>> {
        return countryInfoList
    }
}