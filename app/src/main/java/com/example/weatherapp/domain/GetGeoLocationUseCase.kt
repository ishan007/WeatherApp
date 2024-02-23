package com.example.weatherapp.domain

import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.repository.entities.GeoLocation
import javax.inject.Inject

class GetGeoLocationUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend fun getGeoLocations(place: String): List<GeoLocation>{
        return weatherRepository.getGeoLocations(place)
    }
}