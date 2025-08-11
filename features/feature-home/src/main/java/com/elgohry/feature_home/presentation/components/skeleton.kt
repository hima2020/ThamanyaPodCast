package com.elgohry.feature_home.presentation.components
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.skeleton(shapeRadius: Int = 12) =
    clip(RoundedCornerShape(shapeRadius.dp))

@Composable
fun Modifier.shimmer(
    baseColor: Color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
    highlightColor: Color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
): Modifier {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val xAnim by transition.animateFloat(
        initialValue = -300f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer-x"
    )

    val brush = Brush.linearGradient(
        colors = listOf(baseColor, highlightColor, baseColor),
        start = Offset(xAnim, 0f),
        end = Offset(xAnim + 300f, 0f)
    )
    return this.background(brush)
}