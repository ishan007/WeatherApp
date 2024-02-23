package com.example.weatherapp.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.GetGeoLocationUseCase
import com.example.weatherapp.domain.GetWeatherReportUseCase
import com.example.weatherapp.repository.entities.GeoLocation
import com.example.weatherapp.ui.home.entities.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val geoLocationUseCase: GetGeoLocationUseCase,
    private val getWeatherReportUseCase: GetWeatherReportUseCase
) : ViewModel(){

    private val _homeUiState = MutableLiveData<HomeUiState>()
    val homeUiState: LiveData<HomeUiState> = _homeUiState

    fun getGeoLocations(place: String){
        viewModelScope.launch {
            _homeUiState.value = HomeUiState(isLoading = true)
            val geoLocations = geoLocationUseCase.getGeoLocations(place)
            _homeUiState.value = HomeUiState(isLoading = false, shouldShowGeoLocation = true, geoLocations = geoLocations)
        }
    }

    fun getWeatherReport(geoLocation: GeoLocation){
        viewModelScope.launch {
            _homeUiState.value = HomeUiState(isLoading = true)
            val currentWeatherDeferred = async { getWeatherReportUseCase.getCurrentWeatherReport(geoLocation) }
            val forecastWeatherDeferred = async { getWeatherReportUseCase.getForecastWeatherReport(geoLocation) }
            val currentWeatherReport = currentWeatherDeferred.await()
            val forecastReport = forecastWeatherDeferred.await()
            _homeUiState.value = HomeUiState(
                isLoading = false,
                shouldShowWeather = true,
                weather = "Weather today: ${currentWeatherReport.weather[0].description}. \n\n" +
                        "Current Temperature = ${currentWeatherReport.main.temp}\u2103 \n" +
                        "Maximum Temperature = ${currentWeatherReport.main.tempMax}\u2103 \n" +
                        "Minimum Temperature = ${currentWeatherReport.main.tempMin}\u2103 ",
                forecastReport = forecastReport
            )
        }
    }

    private fun getCurrentWeatherReport(geoLocation: GeoLocation){
        viewModelScope.launch {
            val weatherReport = getWeatherReportUseCase.getCurrentWeatherReport(geoLocation)
            _homeUiState.value = HomeUiState(
                isLoading = false,
                shouldShowWeather = true,
                weather = "Weather today: ${weatherReport.weather[0].description}. \n\n" +
                        "Current Temperature = ${weatherReport.main.temp}\u2103 \n" +
                        "Maximum Temperature = ${weatherReport.main.tempMax}\u2103 \n" +
                        "Minimum Temperature = ${weatherReport.main.tempMin}\u2103 "
            )
        }
    }

    private fun getForecastWeatherReport(geoLocation: GeoLocation){
        viewModelScope.launch {
            _homeUiState.value = HomeUiState(isLoading = true)
            val forecast = getWeatherReportUseCase.getForecastWeatherReport(geoLocation)
            _homeUiState.value = HomeUiState(
                isLoading = false,
                shouldShowWeather = true,
                forecastReport = forecast
            )
        }
    }

}