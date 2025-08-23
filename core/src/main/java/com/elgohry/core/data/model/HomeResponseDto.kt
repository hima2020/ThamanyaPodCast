package com.elgohry.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HomeResponseDto(
    val sections: List<SectionDto>,
    val pagination: PaginationDto?=null
)