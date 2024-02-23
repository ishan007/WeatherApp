package com.example.weatherapp.ui.home.entities

import com.example.weatherapp.repository.entities.ForecastReport
import com.example.weatherapp.repository.entities.GeoLocation
import com.example.weatherapp.repository.entities.WeatherReport

data class HomeUiState(
    val isLoading: Boolean = false,
    val shouldShowGeoLocation: Boolean = false,
    val geoLocations: List<GeoLocation> = emptyList(),
    val shouldShowWeather: Boolean = false,
    val weather: String = "null",
    val forecastReport: ForecastReport = ForecastReport(emptyList())
)