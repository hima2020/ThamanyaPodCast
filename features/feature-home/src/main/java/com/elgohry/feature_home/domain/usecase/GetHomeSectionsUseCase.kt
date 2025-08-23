package com.elgohry.feature_home.domain.usecase

import com.elgohry.core.common.Result
import com.elgohry.core.domain.entity.Section
import com.elgohry.feature_home.domain.repo.HomeRepository
import javax.inject.Inject

class GetHomeSectionsUseCase @Inject constructor(
    private val repo: HomeRepository
) {
    suspend operator fun invoke(page: Int? = null): Result<List<Section>> =
        repo.getHomeSections(page)
}