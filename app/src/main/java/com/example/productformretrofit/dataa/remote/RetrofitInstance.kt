package com.example.productformretrofit.dataa.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val url = "https://fakestoreapi.com/"
    val retrofit by lazy {
        Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
