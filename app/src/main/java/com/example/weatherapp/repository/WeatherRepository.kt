package com.example.weatherapp.repository

import com.example.weatherapp.repository.entities.ForecastReport
import com.example.weatherapp.repository.entities.GeoLocation
import com.example.weatherapp.repository.entities.WeatherReport
import com.example.weatherapp.repository.network.GeoLocationService
import com.example.weatherapp.repository.network.WeatherReportService
import javax.inject.Inject

const val APP_ID: String = "c7e3124536f4f6580662eb295c10f59e"
class WeatherRepository @Inject constructor(
    private val geoLocationService: GeoLocationService,
    private val weatherReportService: WeatherReportService) {

    suspend fun getGeoLocations(place: String): List<GeoLocation>{
        return geoLocationService.getGeoLocations(place, APP_ID)
    }

    suspend fun getCurrentWeatherReport(geoLocation: GeoLocation): WeatherReport{
        return weatherReportService.getCurrentWeatherReport(
            geoLocation.lat,
            geoLocation.lon,
            APP_ID
        )
    }

    suspend fun getForecastWeatherReport(geoLocation: GeoLocation): ForecastReport{
        return weatherReportService.getForecastWeatherReport(
            geoLocation.lat,
            geoLocation.lon,
            APP_ID
        )
    }

}