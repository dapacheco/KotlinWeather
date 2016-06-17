package com.davepacheco.example.kotlinweather.service

import com.davepacheco.example.kotlinweather.BuildConfig
import com.davepacheco.example.kotlinweather.model.Forecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by pachecod on 16/06/16.
 */
interface WeatherService {
    //http://api.openweathermap.org/data/2.5/forecast/daily?q=London,uk&appid=

    @GET("forecast/daily")
    fun getWeeklyForecast(@Query("q") cityNameWithCountry: String,
                          @Query("appid") apiKey: String = BuildConfig.OPEN_WEATHER_API_KEY,
                          @Query("units") units : String = "metric",
                          @Query("cnt") days: Int = 7): Call<Forecast>
}