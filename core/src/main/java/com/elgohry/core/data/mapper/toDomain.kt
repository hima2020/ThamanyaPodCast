package com.elgohry.core.data.mapper

import com.elgohry.core.data.model.*
import com.elgohry.core.domain.entity.*
import com.elgohry.core.domain.entity.Section



fun HomeResponseDto.toSections(): List<Section> =
    sections.map { it.toDomain() }



fun SectionDto.toDomain(): Section {
    val typeDomain = when (type.trim().lowercase()) {
        "square" -> SectionType.Square
        "big_square", "big square" -> SectionType.BigSquare
        "2_lines_grid", "two_lines_grid", "2linesgrid" -> SectionType.TwoLinesGrid
        "queue" -> SectionType.Queue
        else -> SectionType.Unknown(type)
    }



    return Section(
        name = name,
        type = typeDomain,
        items = content.mapNotNull { it.toDomain() }
    )
}

private fun SectionItemDto.toDomain(): SectionItem? {
    val id = podcastId ?: episodeId?:audiobookId?:articleId?:return null
    return SectionItem(
        id = id,
        name = name.orEmpty(),
        descriptionHtml = description,
        avatarUrl = avatarUrl,
        authorName=authorName,
        language = language,

    )
}


