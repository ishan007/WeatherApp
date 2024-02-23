package com.example.weatherapp.di.repository

import com.example.weatherapp.repository.network.GeoLocationService
import com.example.weatherapp.repository.network.WeatherReportService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val CONNECTION_TIMEOUT = 30L
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideWeatherReportService(retrofit: Retrofit): WeatherReportService{
        return retrofit.create(WeatherReportService::class.java)
    }

    @Provides
    @Singleton
    fun provideGeoLocationService(retrofit: Retrofit): GeoLocationService{
        return retrofit.create(GeoLocationService::class.java)
    }
    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        return okHttpClientBuilder.build()
    }

}