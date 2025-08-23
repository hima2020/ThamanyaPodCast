package com.elgohry.core.domain.entity

sealed class SectionType {
    data object Square : SectionType()
    data object BigSquare : SectionType()
    data object TwoLinesGrid : SectionType()
    data object Queue : SectionType()
    data class Unknown(val raw: String) : SectionType()
}