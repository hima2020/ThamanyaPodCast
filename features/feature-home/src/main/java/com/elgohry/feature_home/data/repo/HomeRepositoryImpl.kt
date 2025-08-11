package com.elgohry.feature_home.data.repo

import com.elgohry.core.common.Result
import com.elgohry.feature_home.data.source.HomeRemoteDataSource
import com.elgohry.core.domain.entity.Section
import com.elgohry.feature_home.domain.repo.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val remote: HomeRemoteDataSource
) : HomeRepository {

    override suspend fun getHomeSections(page: Int?): Result<List<Section>> =
        remote.getHomeSections(page)
}