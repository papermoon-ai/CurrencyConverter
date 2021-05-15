package com.example.currencyconverter.domain.result

sealed class Resource<T>(
    val data: T? = null,
    val exception: Throwable? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Failure<T>(exception: Throwable) : Resource<T>(exception = exception)
}

inline fun <reified T> Resource<T>.doIfSuccess(callback: (data: T) -> Unit) {
    if (this is Resource.Success) {
        callback(data!!)
    }
}

inline fun <reified T> Resource<T>.doIfFailure(callback: (throwable: Throwable) -> Unit) {
    if (this is Resource.Failure) {
        callback(exception!!)
    }
}