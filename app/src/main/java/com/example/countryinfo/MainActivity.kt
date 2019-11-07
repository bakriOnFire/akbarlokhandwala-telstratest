package com.example.countryinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.example.countryinfo.common.network.APIController
import com.example.countryinfo.common.network.ServerConstants
import com.example.countryinfo.common.network.ServerRequestHandler
import com.example.countryinfo.model.CountryInfoBaseResponse
import com.google.gson.Gson
import androidx.recyclerview.widget.RecyclerView
import com.example.countryinfo.adapters.CountryInfoListAdapter
import com.example.countryinfo.common.Utility
import com.example.newstest.R

/**
 *
 * Activity with list view showing country info
 * This class loads the country info from server and displays it in a recycler view
 *
 */
class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName
    val serverRequestHandler = ServerRequestHandler()
    val apiController = APIController(serverRequestHandler)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mListRecyclerView = findViewById<RecyclerView>(R.id.rv_country_info_list)

        // Check for internet connectivity before making a server request
        if(Utility().isConnectedToInternet(this)) {
            // Get the server data and initialize recycler adapter to display country info
            apiController.get(ServerConstants.GET_NEWS_URL) { response ->
                val newBaseResponse = Gson().fromJson(response.toString(), CountryInfoBaseResponse::class.java)
                val adapter = CountryInfoListAdapter(newBaseResponse.rows)
                mListRecyclerView.adapter = adapter

                // Change action bar title with the title from server
                val actionBar = supportActionBar
                actionBar?.title =
                    if (!TextUtils.isEmpty(newBaseResponse?.title)) newBaseResponse?.title else getString(R.string.default_app_title)
            }
        } else {
            Log.d(TAG, "No network available")
            Utility()
                .showErrorAlert(this, getString(R.string.error_title), getString(R.string.error_internet_msg));
        }
    }
}
