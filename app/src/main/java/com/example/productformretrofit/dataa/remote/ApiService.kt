package com.example.productformretrofit.dataa.remote

import com.example.productformretrofit.modle.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    suspend fun getAllProducts(): List<Product>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id : Int) : Product
}
