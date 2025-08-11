package com.elgohry.feature_home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elgohry.core.domain.entity.Section

@Composable
fun HomeList(
    items: List<Section>,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    if (items.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            HomeEmpty({

            })
        }
        return
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(
            items = items,
            key = { _, section ->
                // Build a stable key from known fields
                "${section.contentType}-${section.order}-${section.name}"
            }
        ) { _, section ->
            SectionCard(section)
        }
    }
}
