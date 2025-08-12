package com.elgohry.core.domain.entity

data class SectionItem (
            val id: String?="",
            val name: String,
            val descriptionHtml: String?="",
            val avatarUrl: String?="",
            val authorName: String?="",
            val episodeCount: Int?=0,
            val durationSec: Long?=0,
            val language: String?="",
            val priority: Int?=0,
            val popularityScore: Int?=0,
            val score: Double?=0.0,

        )