package com.elgohry.core.components.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elgohry.core.components.shimmer
import com.elgohry.core.components.skeleton

@Composable
 fun SquareRowSkeleton() {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(6) {
                Column(
                    modifier = Modifier.width(140.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(140.dp)
                            .skeleton()
                            .shimmer()
                    )
                    Box(
                        modifier = Modifier
                            .height(16.dp)
                            .fillMaxWidth(0.9f)
                            .skeleton()
                            .shimmer()
                    )
                    Box(
                        modifier = Modifier
                            .height(12.dp)
                            .fillMaxWidth(0.6f)
                            .skeleton()
                            .shimmer()
                    )
                }
            }
        }
    }
}