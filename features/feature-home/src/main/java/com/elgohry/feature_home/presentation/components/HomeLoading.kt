package com.elgohry.feature_home.presentation.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elgohry.feature_home.presentation.components.loading.SectionBlockTitleSkeleton
import com.elgohry.feature_home.presentation.components.loading.SquareRowSkeleton

@Composable
 fun HomeLoading() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Podcasts (square)
        item { SectionBlockTitleSkeleton() }
        item { SquareRowSkeleton() }

        // Trending Episodes (big card)
        item { SectionBlockTitleSkeleton() }
        item { SquareRowSkeleton() }

        // Bestselling Audiobooks (two lines grid-ish)
        item { SectionBlockTitleSkeleton() }
        item { SquareRowSkeleton() }

        // New Podcasts (queue)
        item { SectionBlockTitleSkeleton() }
        item { SquareRowSkeleton() }
    }
}