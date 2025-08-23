package com.elgohry.design_system.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
     primary = Color(0xFF410002) ,
 onPrimary = Color.White,
    primaryContainer = Color(0xFFA6E4E3),
    onPrimaryContainer = Color(0xFF003131),

    secondary = Color(0xFF2E4057)    ,
 onSecondary = Color.White,
    secondaryContainer = Color(0xFFD3D9E6),
    onSecondaryContainer = Color(0xFF141B26),

    tertiary = Color(0xFFEA526F)        ,
 onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFD9E0),
    onTertiaryContainer = Color(0xFF3C0B17),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF111417),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF111417),
    surfaceVariant = Color(0xFFE2E2E8),
    onSurfaceVariant = Color(0xFFD6D8DA),

    outline = Color(0xFF757780),
    error = Color(0xFFBA1A1A),
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002)
)

private val DarkColors = darkColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor
)

@Composable
fun AppTheme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = AppTypography,
        content = content
    )
}