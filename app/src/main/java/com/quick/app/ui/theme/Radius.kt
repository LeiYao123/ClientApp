package com.quick.app.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class RuRadius(
    val radius4: Dp = 4.dp,
    val radius6: Dp = 6.dp,
    val radius8: Dp = 8.dp,
    val radius10: Dp = 10.dp,
    val radius12: Dp = 12.dp,
    val radius16: Dp = 16.dp,
    val radius20: Dp = 20.dp,
    val radius24: Dp = 24.dp,
    var radiusFull: Dp = 999.dp,
)
