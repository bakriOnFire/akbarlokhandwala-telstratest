package com.example.newstest.model

import com.google.gson.annotations.SerializedName

data class NewsBaseRespone(
    @SerializedName("title") val title : String,
    @SerializedName("rows") val rows : List<Rows>
)