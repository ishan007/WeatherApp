package com.example.weatherapp.domain

import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.repository.entities.ForecastReport
import com.example.weatherapp.repository.entities.GeoLocation
import com.example.weatherapp.repository.entities.WeatherReport
import javax.inject.Inject

class GetWeatherReportUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend fun getCurrentWeatherReport(geoLocation: GeoLocation): WeatherReport{
        return weatherRepository.getCurrentWeatherReport(geoLocation)
    }

    suspend fun getForecastWeatherReport(geoLocation: GeoLocation): ForecastReport{
        return weatherRepository.getForecastWeatherReport(geoLocation)
    }

}