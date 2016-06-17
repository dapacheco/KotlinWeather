package com.davepacheco.example.kotlinweather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.davepacheco.example.kotlinweather.model.Forecast
import com.davepacheco.example.kotlinweather.service.WeatherService
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.anko.toast
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class MainActivity : AppCompatActivity(), ForecastView {

    private lateinit var presenter: ForecastPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = ForecastPresenter(this, weatherService())
        presenter.fetchForecastForCity("London,UK")
    }

    override fun hideSpinner() {
        runOnUiThread {
            loading_spinner.visibility = View.GONE
        }
    }

    override fun showSpinner() {
        runOnUiThread {
            loading_spinner.visibility = View.VISIBLE
        }
    }

    override fun updateForecast(forecast: Forecast) {
        runOnUiThread {
            forecast_list.adapter = ForecastAdapter(this, forecast.dailyForecast)
        }
    }

    override fun showErrorToast(message: String) {
        runOnUiThread {
            Log.e("MainActivity", message)
            toast(message)
        }
    }

    private fun weatherService(): WeatherService {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.interceptors().add(loggingInterceptor)


        val retrofit = Retrofit.Builder().baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(JacksonConverterFactory.create()).client(httpClientBuilder.build()).build()

        return retrofit.create(WeatherService::class.java)
    }
}