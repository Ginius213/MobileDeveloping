package com.example.myapplication.database

import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {
    @GET("breeds")
    suspend fun getBreeds(@Query("limit") limit: Int): BreedResponse

    @GET("facts")
    suspend fun getFacts(@Query("limit") limit: Int): FactResponse
}
