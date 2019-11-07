package com.example.countryinfo.model

import com.google.gson.annotations.SerializedName

data class CountryInfoBaseResponse(
    @SerializedName("title") val title : String,
    @SerializedName("rows") val rows : List<Rows>
)