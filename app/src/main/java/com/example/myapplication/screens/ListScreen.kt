package com.example.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.MainViewModel
import com.example.myapplication.R
import com.example.myapplication.database.Breed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(viewModel: MainViewModel) {
    val breeds = viewModel.breeds.value
    val inputText = viewModel.inputText.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier.size(32.dp).padding(end = 8.dp)
            )
            Text(
                text = "I Like Cats",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { viewModel.onInputTextChange(it) },
                label = { Text("Enter number of breeds") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Button(
                onClick = { viewModel.fetchBreeds() },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text("GET")
            }
        }

        Text(
            text = "What are the breeds of kitties",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        LazyColumn (){
            items(breeds) { breed ->
                BreedItem(breed)
            }
        }
    }
}



@Composable
fun BreedItem(breed: Breed) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.cat),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(5.dp)
        )
        Column {
            Text(
                text = breed.breed,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .align(Alignment.Start)
            )
            Text(
                text = "From country: ${breed.country}",
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = "Coat: ${breed.coat}",
                modifier = Modifier.align(Alignment.Start)
            )
        }
    }
}
