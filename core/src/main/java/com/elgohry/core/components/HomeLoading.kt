package com.elgohry.core.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elgohry.core.components.loading.SectionBlockTitleSkeleton
import com.elgohry.core.components.loading.SquareRowSkeleton

@Composable
 fun HomeLoading() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { SectionBlockTitleSkeleton() }
        item { SquareRowSkeleton() }

        item { SectionBlockTitleSkeleton() }
        item { SquareRowSkeleton() }

        item { SectionBlockTitleSkeleton() }
        item { SquareRowSkeleton() }

        item { SectionBlockTitleSkeleton() }
        item { SquareRowSkeleton() }
    }
}