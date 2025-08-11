package com.elgohry.core.network

import com.elgohry.core.data.model.HomeResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {
    @GET("home_sections")
    suspend fun getHomeSections(
        @Query("page") page: Int? = null
    ): HomeResponseDto
}