package com.elgohry.feature_home.data.source

import com.elgohry.core.common.ErrorMapper
import com.elgohry.core.common.Result
import com.elgohry.core.data.mapper.toSections
import com.elgohry.core.domain.entity.Section
import com.elgohry.core.network.HomeApi
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(
    private val api: HomeApi
) {
    suspend fun getHomeSections(page: Int?): Result<List<Section>> = try {
        val dto = api.getHomeSections(page)
        Result.Success(dto.toSections())
    } catch (t: Throwable) {
       ErrorMapper.map(t)

    }
}