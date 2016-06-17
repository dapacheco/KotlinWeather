package com.davepacheco.example.kotlinweather

import com.davepacheco.example.kotlinweather.service.WeatherService
import org.jetbrains.anko.doAsync

/**
 * Created by pachecod on 17/06/16.
 */
class ForecastPresenter(val view: ForecastView, val service: WeatherService) {

    init {
        view.showSpinner()
    }

    fun fetchForecastForCity(cityWithCountry: String) {
        doAsync {
            try {
                val response = service.getWeeklyForecast(cityWithCountry).execute()

                if (response.isSuccessful) {
                    view.updateForecast(response.body())
                    view.hideSpinner()
                } else {
                    view.showErrorToast("There was an error getting the forecast")
                }
            } catch (ex: Exception) {
                view.showErrorToast("There was an error getting the forecast ${ex.message}")
            }
        }
    }

}