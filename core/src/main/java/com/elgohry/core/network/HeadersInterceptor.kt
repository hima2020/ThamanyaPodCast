package com.elgohry.core.network

import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .build()
        return chain.proceed(req)
    }
}