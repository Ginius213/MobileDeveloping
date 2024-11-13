package com.example.myapplication.database

data class BreedResponse(
    val data: List<Breed>
)

data class Breed(
    val breed: String,
    val country: String,
    val origin: String,
    val coat: String,
    val pattern: String
)

data class FactResponse(
    val data: List<Fact>
)

data class Fact(
    val fact: String
)