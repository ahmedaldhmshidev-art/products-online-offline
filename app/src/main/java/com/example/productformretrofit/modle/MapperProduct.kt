package com.example.productformretrofit.modle

import com.example.productformretrofit.dataa.db.ProductEntity
// تحويل من بيانات وصلت من الانترنت الي بيانات قابلة لتخزين محلي
fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        idEntity = id ,
        titleEntity = title,
        priceEntity = price,
        descriptionEntity = description,
        categoryEntity = category,
        imageEntity = image
    )
}
// تحويل من بيانات مخزنه الي بيانات قابله لتحكم والعرض
fun ProductEntity.toProduct():Product{
    return Product(
        id = idEntity,
        title = titleEntity,
        price = priceEntity,
        description = descriptionEntity,
        category = categoryEntity,
        image = imageEntity
    )
}