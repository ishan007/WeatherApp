package com.example.weatherapp.ui.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.repository.entities.WeatherReport

class ForecastAdapter: RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    private val forecasts: ArrayList<WeatherReport> = arrayListOf()

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        private val forecastTextView: TextView = view.findViewById(R.id.forecast_text)

        fun bind(weatherInfo: WeatherReport){
            forecastTextView.text =
                "Date: ${weatherInfo.date} \nTemperature = ${weatherInfo.main.temp}\u2103 \nMaximum Temperature = ${weatherInfo.main.tempMax}\u2103 \nMinimum Temperature = ${weatherInfo.main.tempMin}\u2103"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecasts, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return forecasts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(forecasts[position])
    }

    fun update(forecasts: List<WeatherReport>){
        this.forecasts.addAll(forecasts)
        notifyDataSetChanged()
    }



}