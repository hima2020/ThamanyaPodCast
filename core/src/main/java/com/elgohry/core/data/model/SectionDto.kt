package com.elgohry.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SectionDto(
    val name: String,
    // API sometimes sends "big square" and sometimes "big_square" -> keep raw then normalize in mapper
    val type: String,
    val content: List<SectionItemDto>
)