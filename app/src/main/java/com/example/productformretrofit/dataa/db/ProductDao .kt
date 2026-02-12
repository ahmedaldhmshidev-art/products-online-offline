package com.example.productformretrofit.dataa.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

   @Query("SELECT * FROM productEntity_DB")
   fun getAllProductsDao(): Flow<List<ProductEntity>>

   @Query("SELECT * FROM productEntity_DB WHERE idEntity = :productId")
   suspend fun getProductByIdDao(productId: Int): ProductEntity?

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertProductsDao(products: List<ProductEntity>)
}
