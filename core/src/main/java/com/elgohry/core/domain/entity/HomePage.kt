package com.elgohry.core.domain.entity

data class HomePage(
    val sections: List<Section>,
    val pagination: Pagination
)