package com.project1.apparchitectureexercise1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRetrofit {
    fun dogApiServiceCallBack(): DogApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogApiService::class.java)


        return retrofit
    }
}