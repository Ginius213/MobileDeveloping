package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.screens.MainCard
import com.example.myapplication.screens.TabLayout
import com.example.myapplication.ui.theme.MyApplicationTheme

const val API_KEY = "7f6eebb6101546f29f2122941242210"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                getData("London", this)
                Image(
                    painter = painterResource(id = R.drawable.background_im),
                    contentDescription = "im1",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.7f),
                    contentScale = ContentScale.FillHeight
                )
                Column {
                    MainCard()
                    TabLayout()
                }
            }
        }
    }
}


private fun getData(city: String, context: Context){
    val url = "https://api.weatherapi.com/v1/forecast.json?key=$API_KEY" +
            "&q=$city"+
            "&days=" +
            "3" +
            "&aqi=no&alerts=no\n"
    val queue = Volley.newRequestQueue(context)
    val sRequest = StringRequest(
        Request.Method.GET,
        url,
        {
            response ->
            Log.d("MyLog", "Response : $response")
        },
        {
            Log.d("MyLog", "VolleyError $it")
        }
    )
    queue.add(sRequest)
}