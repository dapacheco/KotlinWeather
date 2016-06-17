package com.davepacheco.example.kotlinweather

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import com.davepacheco.example.kotlinweather.model.DayForecast
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout
import java.util.*

/**
 * Created by pachecod on 17/06/16.
 */
class ForecastAdapter(context: Context, dailyForecast: List<DayForecast>) : ArrayAdapter<DayForecast>(context, 0, dailyForecast) {
    private val ankoContext = AnkoContext.createReusable(context, this)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val weatherForecast = getItem(position)


        return ankoContext.verticalLayout {
            orientation = LinearLayout.HORIZONTAL

            val dayOfWeek = textView()
            dayOfWeek.text = weatherForecast.date.dayOfWeek()
            dayOfWeek.textSize = 20f
            dayOfWeek.lparams {
                weight = 1f
                width = 0
            }


            verticalLayout {
                textView(weatherForecast.weather.description.capitalize())

                textView(weatherForecast.temperature.minTemp.toString())

                textView(weatherForecast.temperature.maxTemp.toString())
            }.lparams {
                weight = 2f
                width = 0
            }

        }
    }
}

fun Calendar.dayOfWeek() =
        when (get(Calendar.DAY_OF_WEEK)) {
            1 -> "Monday"
            2 -> "Tuesday"
            3 -> "Wednesday"
            4 -> "Thursday"
            5 -> "Friday"
            6 -> "Saturday"
            7 -> "Sunday"
            else -> "Free day!"
        }

