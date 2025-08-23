package com.elgohry.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.elgohry.core.domain.entity.SectionItem


@Composable
fun QueueItem(
    modifier: Modifier,
    episode: SectionItem,
) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp)
                )
        ) {
            AsyncImage(
                model = episode.avatarUrl,
                contentScale = ContentScale.Crop,
                contentDescription = episode.name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(6.dp))
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
            ) {
                Text(
                    text = episode.name,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp,
                )

                Text(
                    text = episode.durationSec.toString(),
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }


        }
    }
}
