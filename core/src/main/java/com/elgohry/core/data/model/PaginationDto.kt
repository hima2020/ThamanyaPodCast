package com.elgohry.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginationDto(
    @SerialName("next_page") val nextPage: String? = null,
    @SerialName("total_pages") val totalPages: Int = 0
)