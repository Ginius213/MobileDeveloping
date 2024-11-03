package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui_components.DrawerMenu
import com.example.myapplication.ui_components.InfoScreen
import com.example.myapplication.ui_components.MainListItem
import com.example.myapplication.ui_components.MainScreen
import com.example.myapplication.ui_components.MainTopBar
import com.example.myapplication.utils.DrawerEvents
import com.example.myapplication.utils.IdArrayList
import com.example.myapplication.utils.ListItem
import com.example.myapplication.utils.Routes
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var item: ListItem? = null
            MyApplicationTheme() {
                NavHost(
                    navController = navController,
                    startDestination = Routes.MAIN_SCREEN
                ){
                    composable(Routes.MAIN_SCREEN){
                        MainScreen(context = this@MainActivity){ listItem ->
                            item = listItem
                            navController.navigate(Routes.INFO_SCREEN)
                        }
                    }
                    composable(Routes.INFO_SCREEN){
                        InfoScreen(item = item!!)
                    }
                }
            }
        }
    }
}
