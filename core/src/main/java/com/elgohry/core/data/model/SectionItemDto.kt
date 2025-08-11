package com.elgohry.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SectionItemDto(
    // Common
    val name: String? = null,
    val description: String? = null,
    @SerialName("avatar_url") val avatarUrl: String? = null,
    val duration: Long? = null,
    val language: String? = null,
    @SerialName("release_date") val releaseDate: String? = null,
    val score: Double? = null,

    // Podcast
    @SerialName("podcast_id") val podcastId: String? = null,
    @SerialName("episode_count") val episodeCount: Int? = null,
    val priority: Int? = null,
    val popularityScore: Int? = null,

    // Episode
    @SerialName("episode_id") val episodeId: String? = null,
    @SerialName("episode_type") val episodeType: String? = null,
    @SerialName("podcast_name") val podcastName: String? = null,
    @SerialName("author_name") val authorName: String? = null,
    val number: Int? = null,
    @SerialName("audio_url") val audioUrl: String? = null,
    @SerialName("separated_audio_url") val separatedAudioUrl: String? = null,

    // AudioBook
    @SerialName("audiobook_id") val audiobookId: String? = null,

    // AudioArticle
    @SerialName("article_id") val articleId: String? = null,

    // Extra episode ranking fields showing in payload
    @SerialName("podcastPopularityScore") val episodePodcastPopularityScore: Int? = null,
    @SerialName("podcastPriority") val episodePodcastPriority: Int? = null
)