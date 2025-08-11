package com.elgohry.feature_home.presentation.components
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elgohry.core.domain.entity.Section
import com.elgohry.core.domain.entity.SectionItem
import com.elgohry.core.domain.entity.SectionType


@Composable
fun SectionCard(
    section: Section,
    modifier: Modifier = Modifier,
    onItemClick: (SectionItem) -> Unit = {}
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = section.name,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(12.dp))

            when (section.type) {
                SectionType.Queue -> {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        section.items.forEach { item ->
                            when (item) {
                                is SectionItem.Episode -> EpisodeRow(item) { onItemClick(item) }
                                is SectionItem.Podcast -> PodcastRow(item) { onItemClick(item) }
                                is SectionItem.AudioBook -> AudioBookRow(item) { onItemClick(item) }
                                is SectionItem.AudioArticle -> AudioArticleRow(item) { onItemClick(item) }
                            }
                        }
                    }
                }

                else -> {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        content = {
                            items(
                                count = section.items.size,
                                key = { i ->
                                    "${section.contentType}-${section.order}-${section.items[i]::class.simpleName}-${i}"
                                }
                            ) { i ->
                                when (val item = section.items[i]) {
                                    is SectionItem.Podcast -> PodcastTile(item) { onItemClick(item) }
                                    is SectionItem.Episode -> EpisodeTile(item) { onItemClick(item) }
                                    is SectionItem.AudioBook -> AudioBookTile(item) { onItemClick(item) }
                                    is SectionItem.AudioArticle -> AudioArticleTile(item) { onItemClick(item) }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}