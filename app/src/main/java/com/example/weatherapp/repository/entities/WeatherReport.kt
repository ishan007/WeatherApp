package com.example.weatherapp.repository.entities

import com.google.gson.annotations.SerializedName

data class WeatherReport(
    val main: Main,
    val weather: List<Weather>,
    @SerializedName("dt_txt") val date: String
)

data class Main(
    val temp: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double
)

data class Weather(
    val description: String
)