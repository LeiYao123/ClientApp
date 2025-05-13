package com.quick.app.components.profileIndicator

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.quick.app.ui.theme.PaletteTokens
import kotlin.math.roundToInt

@Composable
fun NewPolyline(
    width: Dp,
    height: Dp,
    stage: ProfileStage,
    extraPath: Boolean = false,
) {
    val strokeWidth = 2.dp
    val cornerRadius = 8.dp
    var path by remember { mutableStateOf(Path()) }

    // 动态计算像素单位
    val radiusPx = with(LocalDensity.current) { cornerRadius.toPx() }
    val strokePx = with(LocalDensity.current) { strokeWidth.toPx() }

    Canvas(
        modifier = Modifier
            .size(width, height)
            .background(Color.Transparent)
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen // 离屏渲染优化[8](@ref)
            }


    ) {
        // 关键优化 2：精确坐标对齐（避免浮点误差）
        val endX = (size.width * 1.0f).roundToInt().toFloat()
        val endY = (size.height * 1.0f).roundToInt().toFloat()
        if (stage == ProfileStage.New)
            path = Path().apply {
                moveTo(0.5f, 0.5f) // 偏移0.5像素避免边缘模糊
                lineTo(endX - 0.5f, 0.5f)
                lineTo(endX - 0.5f, endY - 0.5f)
            }

        if (stage == ProfileStage.Regular || stage == ProfileStage.Returning)
            path = Path().apply {
                moveTo(0.5f, 0.5f) // 偏移0.5像素避免边缘模糊
                lineTo(endX - 0.5f, 0.5f)
                lineTo(endX - 0.5f, endY - 0.5f)
                lineTo(0.5f, endY - 0.5f)
            }

        if (stage == ProfileStage.Regular && extraPath)
            path = Path().apply {
                moveTo(0.5f, 0.5f)
                lineTo(0.5f, endY - 0.5f)
                lineTo(endX - 0.5f, endY - 0.5f)
            }


        drawPath(
            path = path,
            color = PaletteTokens.green_500,
            style = Stroke(
                width = strokePx,
                join = StrokeJoin.Miter, // 关键参数：强制圆角连接
                miter = 0f,              // 消除尖角
                pathEffect = PathEffect.cornerPathEffect(radiusPx)
            )
        )
    }
}




