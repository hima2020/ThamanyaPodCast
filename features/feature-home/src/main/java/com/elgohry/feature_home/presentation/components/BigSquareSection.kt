package com.elgohry.feature_home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.elgohry.core.domain.entity.Section

@Composable
fun BigSquareSection(
    modifier: Modifier, section: Section
) {
    val width = LocalConfiguration.current.screenWidthDp.dp * 0.6f
    val height = width * 0.7f
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(section.items) { item ->
            BigSquareItem(
                modifier = Modifier
                    .width(width)
                    .height(height)
                    .clip(RoundedCornerShape(12.dp)), itemContent = item
            )
        }
    }
}