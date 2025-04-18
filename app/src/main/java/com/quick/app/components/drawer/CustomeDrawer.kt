package com.quick.app.components.drawer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun CustomDrawer(
    open: Boolean,
    onClose: () -> Unit,
    direction: DrawerDirection,
    width: Dp,
    scrimColor: Color = Color.Black.copy(alpha = 0.5f),
    enableEdgeSwipe: Boolean = true,
    animationDuration: Int = 300,
    content: @Composable ColumnScope.() -> Unit,
) {
    val density = LocalDensity.current
    val scope = rememberCoroutineScope()
    val drawerWidthPx = with(density) { width.toPx() }

    val offsetX = remember { Animatable(drawerClosedOffset(direction, drawerWidthPx)) }

    // 控制显示动画：滑入 / 滑出
    LaunchedEffect(open) {
        if (open) {
            offsetX.animateTo(0f, tween(animationDuration))
        } else {
            offsetX.animateTo(
                drawerClosedOffset(direction, drawerWidthPx),
                tween(animationDuration)
            )
        }
    }

    // Drawer 是否在屏幕上（用于保留手势 / 动画）
    val visible = open || offsetX.value != drawerClosedOffset(direction, drawerWidthPx)

    if (visible) {
        Box(modifier = Modifier.fillMaxSize()) {
            // 遮罩层渐隐渐显动画
            AnimatedVisibility(
                visible = open,
                enter = fadeIn(tween(animationDuration)),
                exit = fadeOut(tween(animationDuration))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(scrimColor)
                        .clickable { onClose() }
                )
            }

            // 手势滑动逻辑
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(open) {
                        detectHorizontalDragGestures(
                            onHorizontalDrag = { change, dragAmount ->
                                change.consume()
                                scope.launch {
                                    val newOffset = (offsetX.value + dragAmount).coerceIn(
                                        drawerMinOffset(direction, drawerWidthPx),
                                        drawerMaxOffset(direction, drawerWidthPx)
                                    )
                                    offsetX.snapTo(newOffset)
                                }
                            },
                            onDragEnd = {
                                val shouldClose = when (direction) {
                                    DrawerDirection.Left -> offsetX.value < -drawerWidthPx * 0.5f
                                    DrawerDirection.Right -> offsetX.value > drawerWidthPx * 0.5f
                                }
                                scope.launch {
                                    if (shouldClose) {
                                        onClose()
                                    } else {
                                        offsetX.animateTo(0f, tween(animationDuration))
                                    }
                                }
                            }
                        )
                    }
            ) {
                // Drawer 面板
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(width)
                        .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    Column(content = content)
                }
            }

            // 可选：关闭状态下保留边缘滑动开启区域
            if (!open && enableEdgeSwipe) {
                val edgeWidth = 20.dp
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(edgeWidth)
                        .align(
                            if (direction == DrawerDirection.Left) Alignment.CenterStart
                            else Alignment.CenterEnd
                        )
                        .pointerInput(Unit) {
                            detectHorizontalDragGestures { change, dragAmount ->
                                change.consume()
                                if ((direction == DrawerDirection.Left && dragAmount > 10) ||
                                    (direction == DrawerDirection.Right && dragAmount < -10)
                                ) {
                                    scope.launch { offsetX.animateTo(0f, tween(animationDuration)) }
                                }
                            }
                        }
                )
            }
        }
    }
}


private fun drawerClosedOffset(direction: DrawerDirection, widthPx: Float): Float {
    return when (direction) {
        DrawerDirection.Left -> -widthPx
        DrawerDirection.Right -> widthPx
    }
}

private fun drawerMinOffset(direction: DrawerDirection, widthPx: Float): Float {
    return when (direction) {
        DrawerDirection.Left -> -widthPx
        DrawerDirection.Right -> 0f
    }
}

private fun drawerMaxOffset(direction: DrawerDirection, widthPx: Float): Float {
    return when (direction) {
        DrawerDirection.Left -> 0f
        DrawerDirection.Right -> widthPx
    }
}