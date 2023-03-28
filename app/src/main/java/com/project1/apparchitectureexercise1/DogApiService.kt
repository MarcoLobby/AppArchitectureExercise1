package com.project1.apparchitectureexercise1

import retrofit2.http.GET

interface DogApiService {
    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): retrofit2.Response<Data>

}