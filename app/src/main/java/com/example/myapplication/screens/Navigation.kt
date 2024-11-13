package com.example.myapplication.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Menu") },
            label = { Text("Menu") },
            selected = false,
            onClick = { navController.navigate("facts")}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "List") },
            label = { Text("List") },
            selected = false,
            onClick = { navController.navigate("list") }

        )
    }
}