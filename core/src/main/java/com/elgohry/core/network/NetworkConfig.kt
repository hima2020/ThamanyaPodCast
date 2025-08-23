package com.elgohry.core.network

import okhttp3.MediaType.Companion.toMediaType

object NetworkConfig {
    const val BASE_URL = "https://api-v2-b2sit6oh3a-uc.a.run.app/"
    const val SEARCH_BASE_URL = "https://mock.apidog.com/m1/735111-711675-default/"
    val JSON_MEDIA_TYPE = "application/json".toMediaType()
    const val TIMEOUT_SEC = 30L
}