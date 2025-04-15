package com.quick.app.extension

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.leftBorder(width: Dp = 1.dp, color: Color): Modifier = this.then(
    Modifier.drawBehind {
        val strokeWidth = width.toPx()
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(0f, size.height),
            strokeWidth = strokeWidth
        )
    }
)

fun Modifier.topBorder(width: Dp = 1.dp, color: Color): Modifier = this.then(
    Modifier.drawBehind {
        val strokeWidth = width.toPx()
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = strokeWidth
        )
    }
)

fun Modifier.rightBorder(width: Dp = 1.dp, color: Color): Modifier = this.then(
    Modifier.drawBehind {
        val strokeWidth = width.toPx()
        drawLine(
            color = color,
            start = Offset(size.width, 0f),
            end = Offset(size.width, size.height),
            strokeWidth = strokeWidth
        )
    }
)

fun Modifier.bottomBorder(width: Dp = 1.dp, color: Color): Modifier = this.then(
    Modifier.drawBehind {
        val strokeWidth = width.toPx()
        drawLine(
            color = color,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = strokeWidth
        )
    }
)