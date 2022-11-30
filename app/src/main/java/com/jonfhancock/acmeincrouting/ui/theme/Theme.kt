package com.jonfhancock.acmeincrouting.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFF5e92f3),
    primaryVariant = Color(0xFF1565C0),
    secondary = Color(0xFFff7043)
)

private val LightColorPalette = lightColors(
    primary = Color(0xFF003c8f),
    primaryVariant = Color(0xFF1565C0),
    secondary = Color(0xFFc63f17)
)

@Composable
fun AcmeIncRoutingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}