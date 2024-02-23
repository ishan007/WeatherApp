package com.example.weatherapp.repository.entities

import com.google.gson.annotations.SerializedName

data class ForecastReport(
    @SerializedName("list") val forecasts: List<WeatherReport>
)
