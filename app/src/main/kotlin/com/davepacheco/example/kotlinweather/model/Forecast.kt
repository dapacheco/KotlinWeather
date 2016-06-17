package com.davepacheco.example.kotlinweather.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

/**
 * Created by dpacheco on 16/06/2016.
 * Copyright (C) Sky UK Limited. All rights reserved.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Forecast(@JsonProperty("city") val city: City, @JsonProperty("list") val dailyForecast: List<DayForecast>)

@JsonIgnoreProperties(ignoreUnknown = true)
data class City(@JsonProperty("id") val id: String,
                @JsonProperty("name") val name: String,
                @JsonProperty("country") val country: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class DayForecast(
        @JsonProperty("dt")
        val dateTimeInSeconds: Long,
        @JsonProperty("temp") val temperature: Temperature) {

        @JsonProperty("weather") private var weatherList: List<Weather> = listOf()

        val weather : Weather by lazy {
                weatherList.first()
        }

        val date: Calendar by lazy {
                val cal = Calendar.getInstance()
                cal.timeInMillis = dateTimeInSeconds * 1000
                cal
        }

}

@JsonIgnoreProperties(ignoreUnknown = true)
data class Temperature(@JsonProperty("day") val day: Float,
                       @JsonProperty("max") val maxTemp: Float,
                       @JsonProperty("min") val minTemp: Float)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Weather(@JsonProperty("description") val description: String)