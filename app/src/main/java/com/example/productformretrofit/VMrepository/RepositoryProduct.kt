package com.example.productformretrofit.VMrepository

import com.example.productformretrofit.APIretrofit.ApiService
import com.example.productformretrofit.modle.Product
import com.example.productformretrofit.modle.toEntity
import com.example.productformretrofit.modle.toProduct
import com.example.productformretrofit.roomDataBase.ProductDao
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import java.io.IOException


class RepositoryProduct(private val productDao:ProductDao,
                        private val api: ApiService)
{
    suspend fun fetchAllProduct(): Result<List<Product>> {
        return try {
//            جلب البيانات من room
//            first()لاستخراج البيانات من ال folw
            val local = productDao.getAllProductsDao().first()
//            فحص البيانات اذا لم تكن فارغة نعرضها
            if (local.isNotEmpty()) {
//                اعرضها بعد تحويلها الي بيانات غير room
                Result.success(local.map { it.toProduct() })
            } else {
//                جلب البيانات من الانترنت
                val remote = api.getAllProducts()
//                 تخزين في room في حالة جلب البيانات من الانترنت
                productDao.insertProductsDao(remote.map { it.toEntity() })
//                 ارجاع الي الواجهة
                Result.success(remote)
            }
        } catch (e:Exception){
          Result.failure( Exception( handException(e) ) )
        }
    }

    suspend fun refreshProducts():Result<List<Product>>{
        return try {
//
            val remote = api.getAllProducts()
            productDao.insertProductsDao(remote.map { it.toEntity() })
            Result.success(remote)

        }catch (e:Exception){
            Result.failure(  Exception( handException(e) ) )
        }
    }


    suspend fun fetchProductById(id: Int): Result<Product> {
        return try {
                val remote = api.getProductById(id)
//            تخزين في room
                productDao.insertProductsDao(listOf(remote.toEntity()))
//            ارجاع الي الواجهة
                Result.success(remote)

            } catch (e: Exception) {
//                لو فشل الانترنت
                val local = productDao.getProductByIdDao(id)
            if (local != null){
                Result.success(local.toProduct())
            }else
            {
                Result.failure(  Exception( handException(e) )  )
            }
        }
    }
}


    private fun handException(e: Exception): String {
        return when(e){
            is IOException -> "تحقق من اتصال الانترنت "
            is HttpException -> "حدث خطا اثنا الاتصال بالخادم "
            else -> "خطا غير معروف ${e.localizedMessage}"
        }
    }

