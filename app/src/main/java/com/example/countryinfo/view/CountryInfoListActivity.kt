package com.example.countryinfo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.countryinfo.common.Status
import com.example.countryinfo.view.adapters.CountryInfoListAdapter
import com.example.countryinfo.common.Utility
import com.example.countryinfo.model.ServerResponse
import com.example.countryinfo.viewmodel.CountryInfoViewModel
import com.example.newstest.R
import kotlinx.android.synthetic.main.activity_country_info.*

/**
 *
 * Activity with list view showing country info
 * This class loads the country info from server and displays it in a recycler view
 *
 */
class CountryInfoListActivity : AppCompatActivity() {

    private val tag = CountryInfoListActivity::class.java.simpleName
    private val adapter = CountryInfoListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_info)

        // Set all the listeners for this activity in this function
        setListeners()

        // Set custom adapter to recycler view adapter
        rv_country_info_list.adapter = adapter

        // Set observers on view model to listen to data download and populate recycler view adapter
        setViewModelObservers()
    }

    /**
     * Set event listeners for this activity
     */
    private fun setListeners() {

        //Set swipe to refresh listener when the user pulls to refresh
        swipeContainer.setOnRefreshListener {

            val countryInfoViewModel = ViewModelProviders.of(this).get(CountryInfoViewModel::class.java)

            // Refresh the view model init to get data
            countryInfoViewModel.init()

            swipeContainer.isRefreshing = false
        }
    }

    /**
     * Set observers on view model to listen to data download and populate recycler view adapter
     */
    private fun setViewModelObservers() {

        val countryInfoViewModel = ViewModelProviders.of(this).get(CountryInfoViewModel::class.java)

        // Initialize the view model to get data
        countryInfoViewModel.init()

        // Observe CountryInfoViewModel live data to get title and country info from server
        countryInfoViewModel.getData().observe(this, Observer<ServerResponse> {
            serverResponse ->

            when(serverResponse.status)
            {
                Status.Success -> {
                    // Set the received data into list adapter
                    adapter?.setCountryInfoList(serverResponse.successData?.rows!!)

                    // Change action bar title with the title from server
                    val actionBar = supportActionBar
                    actionBar?.title =
                        if (!TextUtils.isEmpty(serverResponse.successData?.title)) serverResponse.successData?.title else getString(R.string.default_app_title)

                    tv_empty_view.visibility = View.GONE
                    rv_country_info_list.visibility = View.VISIBLE
                }
                Status.Error -> {
                    //Show no data view if no data is available
                    tv_empty_view.text = getString(R.string.list_no_data_available)
                    tv_empty_view.visibility = View.VISIBLE
                    rv_country_info_list.visibility = View.GONE
                }

            }
        })

        // Once the observer is set check for internet connectivity. If no network is connected
        // show no internet message and hide recycler view
        if(!Utility().isConnectedToInternet(this)) {
            //Show no internet connection message in case of no network
            Log.d(tag, "No network available")
            tv_empty_view.text = getString(R.string.list_no_internet_connection)
            tv_empty_view.visibility = View.VISIBLE
            rv_country_info_list.visibility = View.GONE
        }
    }
}
