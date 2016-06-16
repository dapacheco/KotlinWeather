package com.davepacheco.example.kotlinweather.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

/**
 * Created by dpacheco on 16/06/2016.
 * Copyright (C) Sky UK Limited. All rights reserved.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Forecast(val city: City, @JsonProperty("list") val fiveDayForecast: List<WeatherForecast>) {
        val forecastGroupedByDate : Map<Int, List<WeatherForecast>> by lazy {
                fiveDayForecast.groupBy {
                        it.dateTime.get(Calendar.DATE)
                }
        }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class City(val id: String, val name: String, val country: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class WeatherForecast(
        @JsonProperty("dt_txt")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val dateTime: Calendar,
        @JsonProperty("weather") val weather: List<Weather>,
        @JsonProperty("main") val tempAndPressure: TemperatureAndPressure)

@JsonIgnoreProperties(ignoreUnknown = true)
data class TemperatureAndPressure(val temp: Float, @JsonProperty("temp_max") val maxTemp: Float, @JsonProperty("temp_min") val minTemp: Float, val pressure: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Weather(val description: String)