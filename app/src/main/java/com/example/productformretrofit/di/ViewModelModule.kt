package com.example.productformretrofit.di

import com.example.productformretrofit.dataa.RepositoryProduct
import com.example.productformretrofit.ui.viewModel.ViewModelProduct
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideProductViewModel(
        repository: RepositoryProduct
    ): ViewModelProduct {
        return ViewModelProduct(repository)
    }
}
