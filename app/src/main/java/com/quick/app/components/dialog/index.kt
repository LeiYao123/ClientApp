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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.quick.app.ui.theme.RuTheme
import kotlinx.coroutines.delay

@Composable
fun AppDialog(entry: DialogEntry, onClose: (id: String) -> Unit) {
    val visible = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible.value = true
    }

    // 内部关闭
    fun dismiss() {
        visible.value = false
    }

    // 内部关闭
    LaunchedEffect(visible.value) {
        if (!visible.value) {
            delay(entry.duration.toLong())
            onClose(entry.id)
        }
    }

    // 外部关闭
    LaunchedEffect(entry.requestDelete) {
        if (entry.requestDelete) {
            visible.value = false
            delay(entry.duration.toLong())
            onClose(entry.id)
        }
    }

    val transition = updateTransition(visible.value, label = "DialogTransition")

    val alpha by transition.animateFloat(
        label = "AlphaAnim",
        transitionSpec = { tween(entry.duration) }
    ) { if (it) 1f else 0f }

    val scale by transition.animateFloat(
        label = "ScaleAnim",
        transitionSpec = { spring(Spring.DampingRatioMediumBouncy) }
    ) { if (it) 1f else 0.8f }

    if (alpha == 0f && !visible.value) return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = alpha * 0.4f))
            .clickable(
                enabled = entry.dismissOnClickOutside,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { dismiss() },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(entry.width)
                .scale(scale)
                .alpha(alpha)
                .clip(RoundedCornerShape(20.dp))
                .background(RuTheme.colors.bgWhite)
                .wrapContentSize()
        ) { entry.content() }
    }
}