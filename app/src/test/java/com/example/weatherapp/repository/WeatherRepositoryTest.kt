package com.example.weatherapp.repository

import com.example.weatherapp.BaseUnitTest
import com.example.weatherapp.repository.entities.GeoLocation
import com.example.weatherapp.repository.entities.Main
import com.example.weatherapp.repository.entities.Weather
import com.example.weatherapp.repository.entities.WeatherReport
import com.example.weatherapp.repository.network.GeoLocationService
import com.example.weatherapp.repository.network.WeatherReportService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito

class WeatherRepositoryTest: BaseUnitTest() {



    @Mock
    private lateinit var geoLocationService: GeoLocationService
    @Mock
    private lateinit var weatherReportService: WeatherReportService

    @InjectMocks
    private lateinit var repository: WeatherRepository

    @Test
    fun getGeoLocations_PlaceIsProvided_ReturnListOfGeoLocationWithSameName(){
        runBlocking {
            val place = "Delhi"
            val expectedGeoLocations = listOf(GeoLocation(name = place, lat = 1.2, lon=1.3, country= "IN"))
            Mockito.`when`(geoLocationService.getGeoLocations(place, APP_ID)).thenReturn(expectedGeoLocations)

            val actualGeoLocations = repository.getGeoLocations(place)
            Assert.assertEquals(expectedGeoLocations.size, actualGeoLocations.size)
            Assert.assertEquals(expectedGeoLocations[0].lat, actualGeoLocations[0].lat, 0.0)
            Assert.assertEquals(expectedGeoLocations[0].lon, actualGeoLocations[0].lon, 0.0)
        }
    }

    @Test
    fun getCurrentWeatherReport_LatAndLonIsProvided_returnWeatherIno(){
        runBlocking {
            val geoLocation = GeoLocation(name = "Delhi", lat = 1.2, lon=1.3, country= "IN")
            val expectedWeatherReport = WeatherReport(
                Main(temp= 19.0, tempMax = 25.0, tempMin = 26.0),
                listOf(Weather("Good weather")),
                "22-Feb-2024"
            )
            Mockito.`when`(repository.getCurrentWeatherReport(geoLocation)).thenReturn(expectedWeatherReport)

            val actualWeatherReport = repository.getCurrentWeatherReport(geoLocation)

            Assert.assertEquals(expectedWeatherReport.weather[0].description, actualWeatherReport.weather[0].description)
            Assert.assertEquals(expectedWeatherReport.date, actualWeatherReport.date)
            Assert.assertEquals(expectedWeatherReport.main.temp, actualWeatherReport.main.temp, 0.0)
            Assert.assertEquals(expectedWeatherReport.main.tempMin, actualWeatherReport.main.tempMin, 0.0)
            Assert.assertEquals(expectedWeatherReport.main.tempMax, actualWeatherReport.main.tempMax, 0.0)
        }
    }

}