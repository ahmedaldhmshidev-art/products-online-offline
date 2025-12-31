package com.example.productformretrofit.modle

sealed class StatesUI<out T> {
    object LoadingStates : StatesUI<Nothing>()
    data class Success<out T>(val data : T) : StatesUI<T>()
    data class ErrorStates(val messageError : String) : StatesUI<Nothing>()
}