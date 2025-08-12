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
) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 8.dp)
        ) {
            SectionHeaderView(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                title = section.name,
                titleColor = MaterialTheme.colorScheme.onBackground
            )

            when (section.type) {
                SectionType.Square -> {
                    SquareSection(
                        modifier = Modifier, section = section
                    )
                }

                SectionType.TwoLinesGrid -> {
                    TwoLinesGridSection(
                        modifier = Modifier.fillMaxWidth(), section
                    )
                }

                SectionType.BigSquare -> {
                    BigSquareSection(
                        modifier = Modifier, section = section
                    )
                }

                SectionType.Queue -> {
                    QueueSection(Modifier.fillMaxWidth(), section)
                }

                else -> {}
            }
        }


}