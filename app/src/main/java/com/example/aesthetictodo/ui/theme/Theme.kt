package com.example.aesthetictodo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = PinkPrimary,
    secondary = PinkSecondary,
    tertiary = PinkTertiary,
    background = AestheticBackground,
    surface = CardWhite,
    onPrimary = CocoaText,
    onBackground = CocoaText,
    onSurface = CocoaText
)

@Composable
fun AestheticTodoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}