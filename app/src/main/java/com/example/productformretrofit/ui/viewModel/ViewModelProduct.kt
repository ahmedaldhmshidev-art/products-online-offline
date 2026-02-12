package com.example.productformretrofit.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productformretrofit.dataa.RepositoryProduct
import com.example.productformretrofit.modle.Product
import com.example.productformretrofit.modle.StatesUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelProduct @Inject constructor(val repository : RepositoryProduct) : ViewModel() {
    // ====== State للقائمة ======
    private val _productsState =
        MutableLiveData<StatesUI<List<Product>>>()
    val productsState: LiveData<StatesUI<List<Product>>> = _productsState

    // ====== State للتفاصيل ======
    private val _productDetailsState =
        MutableLiveData<StatesUI<Product>>()
    val productDetailsState: LiveData<StatesUI<Product>> = _productDetailsState


    fun loadProducts() {
        viewModelScope.launch {
//            البيانات في حالة جاري التحميل
            _productsState.value = StatesUI.LoadingStates
//      جلب البيانات من كلاس repository
            val result = repository.fetchAllProduct()
//      البيانات بعد ما جلبت تدخل دالة العرض اليدوي
            handleResult(result)

        }
    }

    fun refreshProduct(){
        viewModelScope.launch {
            _productsState.value = StatesUI.LoadingStates
            val result = repository.refreshProducts()
            handleResult(result)
        }
    }

    private fun handleResult(result: Result<List<Product>>) {

        result .onSuccess {
            _productsState.value = StatesUI.Success(it)

        }.onFailure {
            _productsState.value = StatesUI.ErrorStates(it.message ?: "حدث خطا غير متوقع ")
        }
    }

    fun loadProductDetails(id: Int) {
        viewModelScope.launch {
            _productDetailsState.value = StatesUI.LoadingStates

            repository.fetchProductById(id)
                .onSuccess { body ->

                        _productDetailsState.value = StatesUI.Success(body)
                    }

                .onFailure { exception ->
                   _productDetailsState.value = StatesUI.ErrorStates(exception.message ?: "خطا غير متوقع")
                }
        }
    }

}

