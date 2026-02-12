package com.example.productformretrofit.di

import com.example.productformretrofit.dataa.RepositoryProduct
import com.example.productformretrofit.dataa.remote.ApiService
import com.example.productformretrofit.dataa.db.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        dao: ProductDao,
        api: ApiService
    ): RepositoryProduct {
        return RepositoryProduct(dao, api)
    }
}
