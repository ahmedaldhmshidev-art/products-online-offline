package com.example.productformretrofit.VMrepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FactoryVM (private val repository: RepositoryProduct ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelProduct::class.java))
        {
    return ViewModelProduct(repository) as T
}

                throw IllegalArgumentException("Unknown Class for View Model")
            }
        }
