package com.quick.app.components.toast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quick.app.components.svgicon.SvgIcon
import com.quick.app.components.svgicon.SvgPath
import com.quick.app.ui.theme.RuTheme
import kotlinx.coroutines.delay


@Composable
fun ToastHost(viewModel: ToastManager = viewModel()) {
    val toasts by viewModel.toasts.collectAsState()

    LaunchedEffect(Unit) {
        Toast.init(viewModel)
    }

    if (toasts.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.navigationBars)
        ) {
            toasts.forEachIndexed { index, toast ->
                key(toast.id) {
                    // 防止重叠
                    val animatedPadding by animateDpAsState(
                        targetValue = (32 + index * 50).dp,
                        animationSpec = tween(durationMillis = 250), label = ""
                    )

                    val aniPos = getToastItemAnimate(toast.position, animatedPadding)

                    Box(
                        modifier = Modifier
                            .align(aniPos.pos)
                            .padding(aniPos.pd)
                    ) {
                        ToastItem(
                            data = toast,
                            onDismiss = { viewModel.dismiss(toast.id) }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ToastItem(
    data: ToastModel,
    onDismiss: () -> Unit,
) {
    val rColor = RuTheme.colors
    val shape = RoundedCornerShape(size = RuTheme.radius.radius8)
    val cfg = getToastItem(data.status, data.style, rColor)
    val cfgSize = getToastItemSize(data.size)

    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { visible = true }

    LaunchedEffect(data.isDismissing) {
        if (data.isDismissing) {
            visible = false
            delay(250)
            onDismiss()
        }
    }

    val titleRow: @Composable () -> Unit = {
        Row(
            horizontalArrangement = Arrangement.spacedBy(cfgSize.gap.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            cfg.icon(cfgSize.iconSize)
            Text(
                text = data.message,
                color = cfg.textColor,
                style = cfgSize.textSize,
                modifier = Modifier.weight(1f)
            )
            if (data.showDismiss) {
                SvgIcon(
                    SvgPath.close_line,
                    size = cfgSize.iconSize,
                    tint = Color.White.copy(alpha = 0.72f),
                    modifier = Modifier.clickable { onDismiss() }
                )
            }
        }
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(initialOffsetY = { -100 }) + fadeIn(tween(250)),
        exit = slideOutVertically(targetOffsetY = { -100 }) + fadeOut(tween(250)),
        label = "toast_anim",
    ) {
        Box(
            modifier = Modifier
                .let {
                    if (data.style == ToastStyle.Stroke) {
                        it
                            .shadow(
                                elevation = 32.dp,
                                spotColor = Color(0x1A0E121B),
                                ambientColor = Color(0x1A0E121B)
                            )
                            .border(
                                width = 1.dp,
                                color = rColor.strokeSoft,
                                shape = shape
                            )
                    } else it
                }
                .width(390.dp)
                .background(cfg.bgColor, shape)
                .padding(cfgSize.padding),
        ) {
            if (data.desc == null) titleRow()
            else Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                titleRow()
                Text(
                    data.desc,
                    color = cfg.textColor.copy(alpha = if (data.style == ToastStyle.Filled) 1f else 0.72f),
                    style = RuTheme.typo.paragraphS,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            }
        }
//        Row(
//            modifier = Modifier
//                .let {
//                    if (data.style == ToastStyle.Stroke) {
//                        it
//                            .shadow(
//                                elevation = 32.dp,
//                                spotColor = Color(0x1A0E121B),
//                                ambientColor = Color(0x1A0E121B)
//                            )
//                            .border(
//                                width = 1.dp,
//                                color = rColor.strokeSoft,
//                                shape = shape
//                            )
//                    } else it
//                }
//                .width(390.dp)
//                .background(cfg.bgColor, shape)
//                .padding(cfgSize.padding),
//            horizontalArrangement = Arrangement.spacedBy(cfgSize.gap.dp)
//        ) {
//            cfg.icon(cfgSize.iconSize)
//            if (data.size == ToastSize.L && data.title != null)
//                Column(
//                    modifier = Modifier.weight(1f),
//                    verticalArrangement = Arrangement.spacedBy(4.dp)
//                ) {
//                    Text(data.title, color = cfg.textColor, style = RuTheme.typo.labelS)
//                    Text(
//                        data.message,
//                        color = cfg.textColor.copy(alpha = if (data.style == ToastStyle.Filled) 1f else 0.72f),
//                        style = cfgSize.textSize
//                    )
//                }
//            else
//                Text(
//                    text = data.message,
//                    color = cfg.textColor,
//                    style = cfgSize.textSize,
//                    modifier = Modifier.weight(1f)
//                )
//            if (data.showDismiss) {
//                Expanded(1f)
//                SvgIcon(
//                    SvgPath.close_line,
//                    size = cfgSize.iconSize,
//                    tint = Color.White.copy(alpha = 0.72f),
//                    modifier = Modifier.clickable { onDismiss() }
//                )
//            }
//        }
    }
}
