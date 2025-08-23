package com.elgohry.core.network

import com.elgohry.core.data.model.HomeResponseDto
import com.elgohry.core.domain.entity.Section
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search")
    suspend fun search(
        @Query("q") query: String,
        @Query("page") page: Int? = null
    ): HomeResponseDto
}