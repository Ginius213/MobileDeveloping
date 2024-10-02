package com.example.mobiledeveloping

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontFamily.Companion.Monospace
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

class Main {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SecondScreen() {
        val scope = rememberCoroutineScope()


        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("My First App", fontFamily = FontFamily.Monospace) },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.FavoriteBorder, contentDescription = "Menu", Modifier.size(100.dp))
                        }
                    }
                )
            },

            bottomBar = {
                BottomAppBar {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween


                    ) {
                        IconButton(onClick = { /* Действие */ }) {
                            Icon(Icons.Filled.DateRange, contentDescription = "Date Icon", Modifier.size(100.dp))
                        }
                        IconButton(onClick = { /* Действие */ }) {
                            Icon(Icons.Filled.Home, contentDescription = "Home Icon", Modifier.size(100.dp))
                        }
                        IconButton(onClick = { /* Действие */ }) {
                            Icon(Icons.Filled.AccountCircle, contentDescription = "Account Icon", Modifier.size(100.dp))
                        }
                    }
                }

            },
        ) { padding ->
            padding

            LazyColumn(
                modifier = Modifier.fillMaxSize()

            ) {

                item {
                    Text(
                        modifier = Modifier

                            .padding(top = 60.dp, bottom = 20.dp, start = 10.dp), fontWeight = FontWeight.Bold, fontSize = 18.sp,

                        text = "Hello! That's your last chats"
                    )
                }
                items(100) { index ->
                    Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                        .fillMaxWidth()

                        .border(0.1.dp, color = Color.Black)
                    )
                    {




                        Image( painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = "Simple Image", modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.LightGray)
                                .size(50.dp)


                        )
                        Column {


                            Text(
                                modifier = Modifier

                                    .background(

                                        Color.White

                                    )

                                    .padding(start = 5.dp, top = 5.dp)
                                    .fillMaxWidth(),
                                fontWeight = FontWeight.Bold,
                                text = "Chat number $index",
                                fontSize = 14.sp,


                                )
                            Text(
                                modifier = Modifier
                                    .background(

                                        Color.White

                                    )

                                    .padding(start = 5.dp, top = 5.dp)
                                    .fillMaxWidth(),
                                text = "That a simple example for chat number $index. I just want to see this text on two lines so that you can try to make a restriction",
                                fontSize = 14.sp,
                            )
                        }
                    }
                }
            }
        }
    }

    @Preview
    @Composable

    private fun preview() {
        SecondScreen()
    }
}