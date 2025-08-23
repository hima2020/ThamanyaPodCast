package com.elgohry.feature_search.domain.repo

import com.elgohry.core.common.Result
import com.elgohry.core.domain.entity.Section

interface SearchRepository {
    suspend fun search(query: String, page: Int? = null): Result<List<Section>>
}