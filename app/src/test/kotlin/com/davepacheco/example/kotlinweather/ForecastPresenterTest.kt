package com.davepacheco.example.kotlinweather

import com.davepacheco.example.kotlinweather.model.City
import com.davepacheco.example.kotlinweather.model.DayForecast
import com.davepacheco.example.kotlinweather.model.Forecast
import com.davepacheco.example.kotlinweather.model.Temperature
import com.davepacheco.example.kotlinweather.service.WeatherService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.runners.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response
import retrofit2.mock.Calls
import java.util.*

/**
 * Created by pachecod on 17/06/16.
 */
@RunWith(MockitoJUnitRunner::class)
class ForecastPresenterTest {

    @Mock
    private lateinit var service: WeatherService

    @Mock
    private lateinit var view: ForecastView

    @Test
    fun test_presenter_shows_loading_spinner() {
        val presenter = ForecastPresenter(view, service)

        verify(view, atLeastOnce()).showSpinner()
    }

    @Test
    fun test_view_is_updated_with_forecast() {
        val presenter = ForecastPresenter(view, service)

        val cityName = "London,UK"

        `when`(service.getWeeklyForecast(cityName)).thenReturn(forecastResponse())

        presenter.fetchForecastForCity(cityName)

        verify(view, atLeastOnce()).showSpinner()
        verify(service, timeout(100).times(1)).getWeeklyForecast(cityName)
        verify(view, atLeastOnce()).hideSpinner()
    }

    private fun forecastResponse(): Call<Forecast> {
        val forecast = Forecast(City("0", "London", "UK"),
                        listOf(DayForecast(Calendar.getInstance().timeInMillis, Temperature(20f, 30f, 15f))))
        return Calls.response(Response.success(forecast))
    }
}

