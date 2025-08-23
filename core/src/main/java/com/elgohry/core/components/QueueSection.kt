package com.elgohry.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.elgohry.core.domain.entity.Section

@Composable
fun QueueSection(
    modifier: Modifier, section: Section
) {
    val itemWidth = LocalConfiguration.current.screenWidthDp.dp * 0.75f
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(section.items) { item ->
            QueueItem(
                modifier = Modifier
                    .width(itemWidth)
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(12.dp))
                    .padding(12.dp)
                    , episode = item
            )
        }
    }
}