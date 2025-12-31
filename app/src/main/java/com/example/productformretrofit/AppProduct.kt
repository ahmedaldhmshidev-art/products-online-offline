package com.example.productformretrofit

import android.app.Application
import com.example.productformretrofit.APIretrofit.RetrofitInstance
import com.example.productformretrofit.VMrepository.RepositoryProduct
import com.example.productformretrofit.roomDataBase.DataBaseProduct

class AppProduct : Application() {

    private val database by lazy {
        DataBaseProduct.getDatabase(this)
    }
    private val api by lazy {
        RetrofitInstance.apiService
    }
    val repository by lazy {
        RepositoryProduct(database.productDao(), api)
    }
}

