package com.elgohry.core.domain.entity

import com.elgohry.core.data.model.ContentType

data class Section(
    val name: String,
    val type: SectionType,
    val contentType: ContentType,
    val order: Int,
    val items: List<SectionItem>
)