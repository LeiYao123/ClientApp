package com.quick.app.components.popover

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Popup
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun RuPopover(
    modifier: Modifier = Modifier,
    trigger: @Composable (expanded: Boolean, onClick: () -> Unit) -> Unit,
    content: @Composable () -> Unit,
    direction: PopoverDirection = PopoverDirection.Bottom,
    contentHeight: Dp? = null,
    contentWidth: Dp? = null,
    gap: Dp = 4.dp,
) {
    var expanded by remember { mutableStateOf(false) }
    var anchorBounds by remember { mutableStateOf<Rect?>(null) }
    var shouldDismiss by remember { mutableStateOf(false) }

    val density = LocalDensity.current

    Box(modifier = modifier) {
        Box(modifier = Modifier
            .wrapContentSize()
            .onGloballyPositioned { coordinates ->
                if (anchorBounds == null) {
                    val position = coordinates.positionInWindow()
                    val size = coordinates.size.toSize()
                    anchorBounds = Rect(position, size)
                }
            }) {
            trigger(expanded) { if (!shouldDismiss) expanded = true }
        }
        if (anchorBounds != null) {
            val alpha by animateFloatAsState(
                targetValue = if (expanded) 1f else 0f,
                animationSpec = tween(durationMillis = 300),
                label = "Popup Alpha"
            )

            val ty: Float // 向上向下展开动画
            val oy: Int
            val gapPx = with(density) { gap.toPx() }.roundToInt()
            when (direction) {
                // top 时必须传递 contentHeight
                PopoverDirection.Top -> {
                    ty = 40f
                    oy = -with(density) { contentHeight!!.toPx() }.roundToInt() - gapPx
                }

                PopoverDirection.Bottom -> {
                    ty = -40f
                    oy = anchorBounds!!.height.toInt() + gapPx
                }
            }


            val translationY by animateFloatAsState(
                targetValue = if (expanded) 0f else ty,
                animationSpec = tween(durationMillis = 300),
                label = "Popup Offset"
            )
            val scope = rememberCoroutineScope()
            Popup(
                alignment = Alignment.TopStart,
                offset = IntOffset(0, oy),
                onDismissRequest = {
                    if (expanded) {
                        shouldDismiss = true
                        expanded = false
                        scope.launch {
                            delay(300)
                            shouldDismiss = false // 重置
                        }
                    }
                },
            ) {
                Box(
                    Modifier
                        .width(contentWidth ?: with(density) { anchorBounds!!.width.toDp() })
                        .graphicsLayer {
                            this.alpha = alpha
                            this.translationY = translationY
                        }
                ) { content() }
            }
        }
    }
}

enum class PopoverDirection { Top, Bottom }
