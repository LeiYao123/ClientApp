package com.quick.app.extension

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
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

enum class BorderSide { Top, Bottom, Start, End }


fun Modifier.borderSide(
    sides: Set<BorderSide>,
    color: Color,
    width: Dp = 1.dp,
    cornerRadius: Dp = 0.dp,
): Modifier = this.then(
    Modifier.drawBehind {
        val strokeWidth = width.toPx()
        val halfStroke = strokeWidth / 2
        val radius = cornerRadius.toPx()

        // 裁剪圆角，避免边框画出角落外
        if (radius > 0f) {
            clipPath(Path().apply {
                addRoundRect(
                    RoundRect(
                        rect = Rect(Offset.Zero, size),
                        cornerRadius = CornerRadius(radius, radius)
                    )
                )
            }) {}
        }
        sides.forEach { side ->
            when (side) {
                BorderSide.Top -> drawLine(
                    color = color,
                    strokeWidth = strokeWidth,
                    start = Offset(0f, halfStroke),
                    end = Offset(size.width, halfStroke)
                )

                BorderSide.Bottom -> drawLine(
                    color = color,
                    strokeWidth = strokeWidth,
                    start = Offset(0f, size.height - halfStroke),
                    end = Offset(size.width, size.height - halfStroke)
                )

                BorderSide.Start -> drawLine(
                    color = color,
                    strokeWidth = strokeWidth,
                    start = Offset(halfStroke, 0f),
                    end = Offset(halfStroke, size.height)
                )

                BorderSide.End -> drawLine(
                    color = color,
                    strokeWidth = strokeWidth,
                    start = Offset(size.width - halfStroke, 0f),
                    end = Offset(size.width - halfStroke, size.height)
                )
            }
        }
    }
)