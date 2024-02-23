package com.example.weatherapp.ui.home.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListPopupWindow
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.repository.entities.GeoLocation
import com.example.weatherapp.ui.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var placeEditText: EditText
    private lateinit var currentWeatherTextView: TextView
    private lateinit var forecastAdapter: ForecastAdapter
    private lateinit var loader: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()
        initViewModel()
    }

    private fun initView(){
        loader = findViewById(R.id.loader)
        currentWeatherTextView = findViewById(R.id.weather_info_text_view)
        forecastAdapter = ForecastAdapter()
        val forecastRecyclerView = findViewById<RecyclerView>(R.id.weather_list)
        forecastRecyclerView.adapter = forecastAdapter
        forecastRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        placeEditText = findViewById(R.id.place_edit_text)
        findViewById<Button>(R.id.check_weather_btn).setOnClickListener {
            getGeoLocations()
        }
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.homeUiState.observe(this){ homeUiState ->
            when{
                homeUiState.isLoading -> {
                    loader.visibility = View.VISIBLE
                }
                homeUiState.shouldShowGeoLocation -> {
                    loader.visibility = View.GONE
                    showGeoLocations(homeUiState.geoLocations)
                }
                homeUiState.shouldShowWeather -> {
                    loader.visibility = View.GONE
                    currentWeatherTextView.text = homeUiState.weather
                    forecastAdapter.update(homeUiState.forecastReport.forecasts)
                }
            }
        }
    }

    private fun getGeoLocations(){
        val place = placeEditText.text.toString()
        viewModel.getGeoLocations(place)
    }

    private fun showGeoLocations(geoLocations: List<GeoLocation>){
        val dropDownMenu = ListPopupWindow(this)
        dropDownMenu.anchorView = placeEditText
        dropDownMenu.height = 600
        dropDownMenu.setAdapter(ArrayAdapter<GeoLocation>(this, android.R.layout.simple_spinner_dropdown_item, geoLocations))
        dropDownMenu.setOnItemClickListener { _, _, position, _ ->
            run {
                dropDownMenu.dismiss()
                val geoLocation = geoLocations[position]
                viewModel.getWeatherReport(geoLocation)
            }
        }
        dropDownMenu.show()
    }


}