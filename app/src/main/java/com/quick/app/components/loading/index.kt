package com.quick.app.components.loading

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import com.quick.app.components.svgicon.SvgIcon
import com.quick.app.components.svgicon.SvgPath

@Composable
fun Loading(
    modifier: Modifier = Modifier,
    size: Int = 20,
    tint: Color = Color.Unspecified,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "spin")

    // 使用 cubic-bezier(.83,0,.17,1) 创建自定义 Easing 插值器
    val customEasing = CubicBezierEasing(0.83f, 0f, 0.17f, 1f)

    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = customEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotationAngle"
    )
    SvgIcon(SvgPath.loader_3_line,
        size = size,
        tint = tint,
        modifier = modifier
            .graphicsLayer {
                rotationZ = angle
            })
}