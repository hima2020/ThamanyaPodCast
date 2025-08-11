package com.elgohry.feature_home.presentation.components.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elgohry.feature_home.presentation.components.shimmer
import com.elgohry.feature_home.presentation.components.skeleton

@Composable

 fun SectionBlockTitleSkeleton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(22.dp)
            .skeleton()
            .shimmer()
    )
}