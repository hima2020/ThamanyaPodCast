package com.elgohry.core.domain.entity

data class Section(
    val name: String,
    val type: SectionType,
    val items: List<SectionItem>
)