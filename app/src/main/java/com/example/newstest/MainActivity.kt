package com.example.newstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.ActionBar
import com.example.newstest.common.network.APIController
import com.example.newstest.common.network.ServerConstants
import com.example.newstest.common.network.ServerRequestHandler
import com.example.newstest.model.NewsBaseRespone
import com.google.gson.Gson
import androidx.recyclerview.widget.RecyclerView
import com.example.newstest.adapters.NewsListAdapter
import com.example.newstest.common.AppConstants


class MainActivity : AppCompatActivity() {

    val serverRequestHandler = ServerRequestHandler()
    val apiController = APIController(serverRequestHandler)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mListRecyclerView = findViewById<RecyclerView>(R.id.rv_news_list)

        apiController.get(ServerConstants.GET_NEWS_URL) { response ->
            val newBaseResponse = Gson().fromJson(response.toString(), NewsBaseRespone::class.java)
            val adapter = NewsListAdapter(newBaseResponse.rows)
            mListRecyclerView.adapter = adapter
            val actionBar = supportActionBar
            actionBar?.title = if (!TextUtils.isEmpty(newBaseResponse?.title)) newBaseResponse?.title else AppConstants.DEFAULT_TITLE_TEXT
            Log.d("server response", "papa")
        }
    }
}
