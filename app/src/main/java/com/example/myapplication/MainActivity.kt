package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.myapplication.data.WeatherModel
import com.example.myapplication.screens.DialogSearch
import com.example.myapplication.screens.MainCard
import com.example.myapplication.screens.TabLayout
import com.example.myapplication.screens.WeatherApi
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_KEY = "7f6eebb6101546f29f2122941242210"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val daysList = remember {
                    mutableStateOf(listOf<WeatherModel>())
                }
                val dialogState = remember {
                    mutableStateOf(false)
                }
                val currentDay = remember {
                    mutableStateOf(WeatherModel(
                    "",
                    "",
                    "0.0",
                    "",
                    "",
                    "0.0",
                    "0.0",
                    listOf(),
                    )
                    )
                }
                if(dialogState.value){
                    DialogSearch(dialogState, onSumbit = {
                        getData(it, this, daysList, currentDay)
                    })
                }
                getData("London", this, daysList, currentDay)
                Image(
                    painter = painterResource(id = R.drawable.background_im),
                    contentDescription = "im1",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.7f),
                    contentScale = ContentScale.FillHeight
                )
                Column {
                    MainCard(currentDay, onClickSync = {
                        getData("London", this@MainActivity, daysList, currentDay)
                    }, onClickSearch = {
                        dialogState.value = true
                    }
                    )
                    TabLayout(daysList, currentDay)
                }
            }
        }
    }
}


private fun getData(
    city: String, context: Context,
    daysList: MutableState<List<WeatherModel>>,
    currentDay: MutableState<WeatherModel>
) {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: WeatherApi = retrofit.create(WeatherApi::class.java)

    val job = CoroutineScope(Dispatchers.IO).launch {
        val weather = service.getWeather(city)
        Log.d("WeatherData", weather.toString())
        daysList.value = weather.forecast.forecastday.map { forecastDay ->
            WeatherModel(
                city = weather.location.name,
                time = forecastDay.date,
                currentTemp = "",
                condition = forecastDay.day.condition.text,
                icon = forecastDay.day.condition.icon,
                maxTemp = forecastDay.day.maxTemp.toFloat().toInt().toString() + "°C",
                minTemp = forecastDay.day.minTemp.toFloat().toInt().toString() + "°C",
                hours = forecastDay.hour // Здесь передаем список объектов HourDto
            )
        }
        currentDay.value = daysList.value.first().copy(
            time = weather.current.time,
            currentTemp = weather.current.currentTemp.toFloat().toInt().toString() + "°C",
        )
    }
    job
}