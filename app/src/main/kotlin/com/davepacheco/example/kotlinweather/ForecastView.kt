package com.davepacheco.example.kotlinweather

import com.davepacheco.example.kotlinweather.model.Forecast

/**
 * Created by pachecod on 17/06/16.
 */
interface  ForecastView {
    fun hideSpinner()

    fun showSpinner()

    fun updateForecast(forecast: Forecast)

    fun showErrorToast(message: String)

}
