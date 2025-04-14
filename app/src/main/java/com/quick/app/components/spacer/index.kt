package com.quick.app.components.spacer

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RuSpacer(w: Int = 0, h: Int = 0) {
    Spacer(
        modifier = Modifier
            .width(w.dp)
            .height(h.dp)
    )
}

@Composable
fun RowScope.Expanded(weight: Float = 1f) {
    Spacer(modifier = Modifier.weight(weight))
}

@Composable
fun ColumnScope.Expanded(weight: Float = 1f) {
    Spacer(modifier = Modifier.weight(weight))
}