package com.quick.app.components.dialog

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 * 专门用于 Adjust Tip 的 弹窗
 * */
@Composable
fun AppDialog1(
    open: Boolean,
    width: Dp = 400.dp,
    dismissOnClickOutside: Boolean = true,
    duration: Int = 300,
    onClose: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit,
) {
    val transition = updateTransition(open, label = "DialogTransition")

    val alpha by transition.animateFloat(
        label = "AlphaAnim",
        transitionSpec = { tween(duration) }
    ) { if (it) 1f else 0f }

    val scale by transition.animateFloat(
        label = "ScaleAnim",
        transitionSpec = { spring(Spring.DampingRatioMediumBouncy) }
    ) { if (it) 1f else 0.8f }

    if (alpha == 0f && !open) return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = alpha * 0.4f))
            .clickable(
                enabled = dismissOnClickOutside,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClose() },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(width)
                .clickable {}
                .scale(scale)
                .alpha(alpha)
//                .clip(RoundedCornerShape(20.dp))
//                .background(RuTheme.colors.bgWhite)
                .wrapContentSize()

        ) { Column(modifier = Modifier.fillMaxWidth(), content = content) }
    }
}