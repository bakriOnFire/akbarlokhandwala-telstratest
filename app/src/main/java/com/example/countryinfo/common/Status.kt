package com.example.countryinfo.common

sealed class Status {
    object Success: Status()
    object Error: Status()
}