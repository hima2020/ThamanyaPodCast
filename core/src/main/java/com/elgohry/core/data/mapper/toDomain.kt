package com.elgohry.core.data.mapper

import com.elgohry.core.data.model.*
import com.elgohry.core.domain.entity.*
import com.elgohry.core.domain.entity.Section



fun HomeResponseDto.toDomain(): HomePage =
    HomePage(
        sections = sections.map { it.toDomain() },
        pagination = Pagination(
            nextPage = pagination.nextPage,
            totalPages = pagination.totalPages
        )
    )


fun HomeResponseDto.toSections(): List<Section> =
    sections.map { it.toDomain() }



fun SectionDto.toDomain(): Section {
    val typeDomain = when (type.trim().lowercase()) {
        "square" -> SectionType.Square
        "big_square", "big square" -> SectionType.BigSquare
        "2_lines_grid", "two_lines_grid", "2linesgrid" -> SectionType.TwoLinesGrid
        "queue" -> SectionType.Queue
        else -> SectionType.Unknown(type) // keep the raw API value
    }

    val cType = when (contentType) {
        ContentTypeDto.PODCAST -> ContentType.PODCAST
        ContentTypeDto.EPISODE -> ContentType.EPISODE
        ContentTypeDto.AUDIO_BOOK -> ContentType.AUDIO_BOOK
        ContentTypeDto.AUDIO_ARTICLE -> ContentType.AUDIO_ARTICLE
    }


    return Section(
        name = name,
        type = typeDomain,
        contentType = cType,
        order = order,
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
        episodeCount = episodeCount,
        durationSec = duration,
        language = language,
        priority = priority,
        popularityScore = popularityScore,
        score = score
    )
}


