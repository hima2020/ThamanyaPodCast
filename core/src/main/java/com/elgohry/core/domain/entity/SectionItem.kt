package com.elgohry.core.domain.entity

sealed class SectionItem {
    data class Podcast(
            val id: String,
            val name: String,
            val descriptionHtml: String?,
            val avatarUrl: String?,
            val episodeCount: Int?,
            val durationSec: Long?,
            val language: String?,
            val priority: Int?,
            val popularityScore: Int?,
            val score: Double?
    ) : SectionItem()

    data class Episode(
            val id: String,
            val name: String,
            val podcastName: String?,
            val authorName: String?,
            val type: String?,
            val number: Int?,
            val durationSec: Long?,
            val avatarUrl: String?,
            val audioUrl: String?,
            val separatedAudioUrl: String?,
            val releaseDateIso: String?,
            val popularityScore: Int?,
            val priority: Int?,
            val score: Double?
    ) : SectionItem()

    data class AudioBook(
            val id: String,
            val name: String,
            val authorName: String?,
            val description: String?,
            val avatarUrl: String?,
            val durationSec: Long?,
            val language: String?,
            val releaseDateIso: String?,
            val score: Double?
    ) : SectionItem()

    data class AudioArticle(
            val id: String,
            val name: String,
            val authorName: String?,
            val description: String?,
            val avatarUrl: String?,
            val durationSec: Long?,
            val releaseDateIso: String?,
            val score: Double?
    ) : SectionItem()
}