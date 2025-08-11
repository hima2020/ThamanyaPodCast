package com.elgohry.core.common

import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

object ErrorMapper {
    fun map(throwable: Throwable): Result.Error = when (throwable) {
        is IOException -> Result.Error("Network error, please check connection", throwable)
        is HttpException -> Result.Error("Server error (${throwable.code()})", throwable)
        is UnknownHostException -> Result.Error("No Internet Connection")
        else -> Result.Error("Unexpected error", throwable)
    }
}