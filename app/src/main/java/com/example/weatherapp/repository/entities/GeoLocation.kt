package com.example.weatherapp.repository.entities

data class GeoLocation(
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String
){
    override fun toString(): String {
        return "$name, $country"
    }
}
