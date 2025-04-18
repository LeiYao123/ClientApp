package com.quick.app.components.drawer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first

const val animationDuration = 300

@Composable
fun DrawerBody(entry: DrawerEntry, onClose: (id: String) -> Unit) {
    val transitionState = remember {
        MutableTransitionState(false).apply {
            targetState = true // 触发进入动画
        }
    }

    LaunchedEffect(entry.requestDelete) {
        if (entry.requestDelete) {
            transitionState.targetState = false // 播放 exit
        }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { transitionState.targetState to transitionState.currentState }.collect { (target, current) ->
            if (current && !target) {
                // 正在退出
                snapshotFlow { transitionState.isIdle }
                    .filter { it }
                    .first()
                onClose(entry.id)
            }
        }
    }

    // 遮罩层：单独处理渐隐渐显动画
    AnimatedVisibility(
        visibleState = transitionState,
        enter = fadeIn(animationSpec = tween(animationDuration)),
        exit = fadeOut(animationSpec = tween(animationDuration))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable {
                    if (entry.dismissOnClickOutside) {
                        transitionState.targetState = false // 播放 exit
                    }
                }
        )
    }

    // 抽屉面板：单独处理滑动 + 淡入动画
    AnimatedVisibility(
        visibleState = transitionState,
        enter = slideInHorizontally(
            animationSpec = tween(animationDuration),
            initialOffsetX = { fullWidth -> if (entry.direction == DrawerDirection.Left) -fullWidth else fullWidth }
        ),
        exit = slideOutHorizontally(
            animationSpec = tween(animationDuration),
            targetOffsetX = { fullWidth -> if (entry.direction == DrawerDirection.Left) -fullWidth else fullWidth }
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable { }
                    .width(entry.width)
                    .align(if (entry.direction == DrawerDirection.Left) Alignment.CenterStart else Alignment.CenterEnd)
                    .background(MaterialTheme.colorScheme.surface),
            ) {
                Column(modifier = Modifier.fillMaxSize(), content = entry.content)
            }
        }
    }
}