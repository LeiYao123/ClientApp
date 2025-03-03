package com.quick.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf


val LocalCustomColors = compositionLocalOf<RuColors> {
    error("No CustomColors provided")
}


@Composable
fun RuTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) darkRuColor() else lightRuColor()
    val mColor = if (darkTheme) darkColorScheme() else lightColorScheme()
    CompositionLocalProvider(LocalCustomColors.provides(colors)) {
        MaterialTheme(colorScheme = mColor, content = content)
    }
}

object RuTheme {
    val colors: RuColors
        @Composable
        get() = LocalCustomColors.current
}
