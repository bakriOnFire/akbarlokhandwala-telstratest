package com.example.countryinfo.model

import com.google.gson.annotations.SerializedName

data class CountryInfoResponse(
    @SerializedName("title") val title : String,
    @SerializedName("rows") val rows : List<Rows>
)