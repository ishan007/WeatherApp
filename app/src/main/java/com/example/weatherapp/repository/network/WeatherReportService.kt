package com.example.weatherapp.repository.network

import com.example.weatherapp.repository.entities.ForecastReport
import com.example.weatherapp.repository.entities.WeatherReport
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherReportService {

    @GET("data/2.5/weather?units=metric")
    suspend fun getCurrentWeatherReport(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String
    ): WeatherReport

    @GET("data/2.5/forecast?units=metric")
    suspend fun getForecastWeatherReport(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String
    ): ForecastReport

}