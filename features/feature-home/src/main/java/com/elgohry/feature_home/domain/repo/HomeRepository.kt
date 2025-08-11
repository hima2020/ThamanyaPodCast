package com.elgohry.feature_home.domain.repo

import com.elgohry.core.common.Result
import com.elgohry.core.domain.entity.Section

interface HomeRepository {
    suspend fun getHomeSections(page: Int? = null): Result<List<Section>>
}