package com.elgohry.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.elgohry.core.domain.entity.Section
import kotlin.collections.getOrNull

@Composable
fun TwoLinesGridSection(
    modifier: Modifier = Modifier,
    section: Section,
) {
    val pairedItems by remember {
        derivedStateOf {
            section.items.sortedByDescending { it.priority }.chunked(2)
        }
    }
    val pagerState = rememberPagerState(pageCount = { pairedItems.size })
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val pageWidth = screenWidth * 0.8f

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        HorizontalPager(
            state = pagerState, contentPadding = PaddingValues(
                start = 16.dp,
                end = (screenWidth - pageWidth - 16.dp)
            ), pageSpacing = 8.dp, modifier = Modifier.fillMaxWidth()
        ) { page ->
            Column(
                modifier = Modifier
                    .width(pageWidth)
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                pairedItems[page].getOrNull(0)?.let { item ->
                    TwoLinesGridItem(
                        item = item)

                }

                pairedItems[page].getOrNull(1)?.let { item ->
                    TwoLinesGridItem(
                        item = item)
                }
            }
        }
    }
}