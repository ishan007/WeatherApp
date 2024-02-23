package com.example.weatherapp.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun convertUtcToLocale(timestamp: Long): String {
    return try {
        val utcDate = Date(timestamp * 1000L)
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        sdf.format(utcDate)
    } catch (e: Exception) {
        e.printStackTrace()
        "Error converting timestamp"
    }
}