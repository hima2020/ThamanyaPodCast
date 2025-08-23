package com.elgohry.feature_search.data.repo

import com.elgohry.core.common.ErrorMapper
import com.elgohry.core.common.Result
import com.elgohry.feature_search.data.source.SearchRemoteDataSource
import com.elgohry.core.domain.entity.Section
import com.elgohry.feature_search.domain.repo.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val remote: SearchRemoteDataSource
) : SearchRepository {
    override suspend fun search(query: String, page: Int?): Result<List<Section>> =
        remote.search(query,page)
}