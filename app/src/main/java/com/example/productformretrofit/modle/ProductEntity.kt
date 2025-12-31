package com.example.productformretrofit.roomDataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productEntity_DB")
data class ProductEntity(
    @PrimaryKey val idEntity :Int ,
    val titleEntity:String,
    val priceEntity : Double ,
    val descriptionEntity :String ,
    val categoryEntity : String,
    val imageEntity : String
)

