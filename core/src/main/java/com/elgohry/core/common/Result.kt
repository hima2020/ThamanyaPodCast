package com.elgohry.core.common

sealed class Result<out T> {
    data class Success<T>(val data: T): Result<T>()
    data class Error(val message: String, val cause: Throwable? = null): Result<Nothing>()
}

inline fun <T> Result<T>.onSuccess(block: (T) -> Unit): Result<T> {
    if (this is Result.Success) block(data)
    return this
}
inline fun <T> Result<T>.onError(block: (String, Throwable?) -> Unit): Result<T> {
    if (this is Result.Error) block(message, cause)
    return this
}