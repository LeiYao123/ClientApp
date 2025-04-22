package com.quick.app.components.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quick.app.extension.topBorder
import com.quick.app.ui.theme.RuTheme

@Composable
fun RuDrawerFooter(
    showDivider: Boolean = true,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(RuTheme.colors.bgWhite)
            .let { if (showDivider) it.topBorder(1.dp, RuTheme.colors.strokeSoft) else it }
            .padding(20.dp),
    ) { content() }
}