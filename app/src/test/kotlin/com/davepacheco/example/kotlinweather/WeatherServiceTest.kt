package com.davepacheco.example.kotlinweather

import com.davepacheco.example.kotlinweather.service.WeatherService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.davepacheco.example.kotlinweather.model.Forecast
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import java.io.File

/**
 * Created by dpacheco on 16/06/2016.
 * Copyright (C) Sky UK Limited. All rights reserved.
 */

@RunWith(MockitoJUnitRunner::class)
class WeatherServiceTest {

    @Mock
    lateinit var service: WeatherService

    @Test
    fun test_forecast_is_parsed_from_json() {

        val mapper = jacksonObjectMapper()

        val json = File("src/test/kotlin/com/davepacheco/example/kotlinweather/sample.json").readText()

        try {
            val forecast = mapper.readValue(json, Forecast::class.java)
            Assert.assertNotNull(forecast)

            Assert.assertEquals("London", forecast.city.name)
            // forecast every three hours for five days
            Assert.assertEquals(40, forecast.fiveDayForecast.size)

            Assert.assertEquals("few clouds", forecast.fiveDayForecast.first().weather.first().description)
        } catch (ex: Exception) {
            Assert.fail("Exception ${ex.message} thrown")
        }
    }

}