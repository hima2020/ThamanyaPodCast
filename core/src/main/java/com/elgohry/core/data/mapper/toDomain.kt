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

    val itemsDomain: List<SectionItem> = when (cType) {
        ContentType.PODCAST -> content.mapNotNull { it.asPodcast() }
        ContentType.EPISODE -> content.mapNotNull { it.asEpisode() }
        ContentType.AUDIO_BOOK -> content.mapNotNull { it.asAudioBook() }
        ContentType.AUDIO_ARTICLE -> content.mapNotNull { it.asAudioArticle() }
    }

    return Section(
        name = name,
        type = typeDomain,
        contentType = cType,
        order = order,
        items = itemsDomain
    )
}

private fun SectionItemDto.asPodcast(): SectionItem.Podcast? {
    val id = podcastId ?: return null
    return SectionItem.Podcast(
        id = id,
        name = name.orEmpty(),
        descriptionHtml = description,
        avatarUrl = avatarUrl,
        episodeCount = episodeCount,
        durationSec = duration,
        language = language,
        priority = priority,
        popularityScore = popularityScore,
        score = score
    )
}

private fun SectionItemDto.asEpisode(): SectionItem.Episode? {
    val id = episodeId ?: return null
    return SectionItem.Episode(
        id = id,
        name = name.orEmpty(),
        podcastName = podcastName,
        authorName = authorName,
        type = episodeType,
        number = number,
        durationSec = duration,
        avatarUrl = avatarUrl,
        audioUrl = audioUrl,
        separatedAudioUrl = separatedAudioUrl,
        releaseDateIso = releaseDate,
        popularityScore = episodePodcastPopularityScore ?: popularityScore,
        priority = episodePodcastPriority ?: priority,
        score = score
    )
}

private fun SectionItemDto.asAudioBook(): SectionItem.AudioBook? {
    val id = audiobookId ?: return null
    return SectionItem.AudioBook(
        id = id,
        name = name.orEmpty(),
        authorName = authorName,
        description = description,
        avatarUrl = avatarUrl,
        durationSec = duration,
        language = language,
        releaseDateIso = releaseDate,
        score = score
    )
}

private fun SectionItemDto.asAudioArticle(): SectionItem.AudioArticle? {
    val id = articleId ?: return null
    return SectionItem.AudioArticle(
        id = id,
        name = name.orEmpty(),
        authorName = authorName,
        description = description,
        avatarUrl = avatarUrl,
        durationSec = duration,
        releaseDateIso = releaseDate,
        score = score
    )
}