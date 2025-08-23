package com.elgohry.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.elgohry.core.domain.entity.Section

@Composable
fun SquareSection(
    modifier: Modifier, section: Section
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(section.items) { item ->
            SquareItem(
                modifier = Modifier
                    .width(130.dp)
                    .clip(RoundedCornerShape(12.dp)), itemContent = item
            )
        }
    }
}