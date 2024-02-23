package com.example.weatherapp.repository.network

import com.example.weatherapp.repository.entities.GeoLocation
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GeoLocationService {

    @GET("geo/1.0/direct?limit=5")
    suspend fun getGeoLocations(@Query("q") place: String, @Query("appid") appId: String): List<GeoLocation>

}