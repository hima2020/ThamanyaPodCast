package com.elgohry.feature_search.data.source

import com.elgohry.core.common.ErrorMapper
import com.elgohry.core.common.Result
import com.elgohry.core.data.mapper.toSections
import com.elgohry.core.domain.entity.Section
import com.elgohry.core.network.SearchApi
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(
    private val api: SearchApi
) {
    suspend fun search(query: String, page: Int?): Result<List<Section>> = try {

    val dto = api.search(  query,page)
    Result.Success(dto.toSections())
} catch (t: Throwable) {
    ErrorMapper.map(t)

}
}