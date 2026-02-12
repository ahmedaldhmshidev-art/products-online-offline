package com.example.productformretrofit.di

import android.content.Context
import androidx.room.Room
import com.example.productformretrofit.dataa.db.DataBaseProduct
import com.example.productformretrofit.dataa.db.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DataBaseProduct {
        return Room.databaseBuilder(
            context,
            DataBaseProduct::class.java,
            "product_database"
        ).build()
    }

    @Provides
    fun provideProductDao(db: DataBaseProduct): ProductDao {
        return db.productDao()
    }
}
