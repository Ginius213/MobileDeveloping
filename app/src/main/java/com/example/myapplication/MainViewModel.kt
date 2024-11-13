package com.example.myapplication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.database.Breed
import com.example.myapplication.database.Fact
import com.example.myapplication.database.RetrofitClient
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _breeds = mutableStateOf<List<Breed>>(emptyList())
    val breeds: State<List<Breed>> = _breeds

    private val _facts = mutableStateOf<List<Fact>>(emptyList())
    val facts: State<List<Fact>> = _facts

    private val _error = mutableStateOf<String?>(null)


    private val _inputText = mutableStateOf("")
    val inputText: State<String> = _inputText

    init {
        fetchFacts()
        fetchInitialBreeds()
    }

    fun onInputTextChange(newText: String) {
        _inputText.value = newText
    }

    fun fetchBreeds() {
        viewModelScope.launch {
            _error.value = null
            val count = _inputText.value.toIntOrNull()
            if (count != null && count > 0) {
                val response = RetrofitClient.apiService.getBreeds(count)
                _breeds.value = response.data

            } else {
                _error.value = "Invalid input. Please enter a valid number."
            }
        }
    }

    private fun fetchInitialBreeds() {
        viewModelScope.launch {
            val response = RetrofitClient.apiService.getBreeds(10)
            _breeds.value = response.data

        }
    }

    fun fetchFacts() {
        viewModelScope.launch {
            val response = RetrofitClient.apiService.getFacts(100)
            val shuffeldFacts = response.data.shuffled()
            _facts.value = shuffeldFacts
        }
    }
}