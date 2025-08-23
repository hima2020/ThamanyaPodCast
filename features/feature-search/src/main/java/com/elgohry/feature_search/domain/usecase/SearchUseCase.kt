package com.elgohry.feature_search.domain.usecase

import com.elgohry.core.common.Result
import com.elgohry.core.domain.entity.Section
import com.elgohry.feature_search.domain.repo.SearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repo: SearchRepository
) {
    suspend operator fun invoke(q: String, page: Int? = null): Result<List<Section>> =
        repo.search(q, page)
}