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
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quick.app.components.spacer.Expanded
import com.quick.app.components.svgicon.SvgIcon
import com.quick.app.components.svgicon.SvgPath
import com.quick.app.ui.theme.RuColors
import com.quick.app.ui.theme.RuTheme
import kotlinx.coroutines.delay


@Composable
fun ToastHost(viewModel: ToastManager = viewModel()) {
    val toasts by viewModel.toasts.collectAsState()

    LaunchedEffect(Unit) {
        Toast.init(viewModel)
    }

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

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(initialOffsetY = { -100 }) + fadeIn(tween(250)),
        exit = slideOutVertically(targetOffsetY = { -100 }) + fadeOut(tween(250)),
        label = "toast_anim",
    ) {
        Row(
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
            horizontalArrangement = Arrangement.spacedBy(cfgSize.gap.dp)
        ) {
            cfg.icon(cfgSize.iconSize)
            if (data.size == ToastSize.L && data.title != null)
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(data.title, color = cfg.textColor, style = RuTheme.typo.labelS)
                    Text(
                        data.message,
                        color = cfg.textColor.copy(alpha = if (data.style == ToastStyle.Filled) 1f else 0.72f),
                        style = cfgSize.textSize
                    )
                }
            else
                Text(
                    text = data.message,
                    color = cfg.textColor,
                    style = cfgSize.textSize,
                    modifier = Modifier.weight(1f)
                )
            if (data.showDismiss) {
                Expanded(1f)
                SvgIcon(
                    SvgPath.close_line,
                    size = cfgSize.iconSize,
                    tint = Color.White.copy(alpha = 0.72f),
                    modifier = Modifier.clickable { onDismiss() }
                )
            }
        }
    }
}

data class ToastItemAnimate(
    val pos: Alignment,
    val pd: PaddingValues,
)


fun getToastItemAnimate(position: ToastPosition, animatedPadding: Dp): ToastItemAnimate {
    return when (position) {
        ToastPosition.TopCenter -> ToastItemAnimate(
            pos = Alignment.TopCenter,
            pd = PaddingValues(top = animatedPadding)
        )

        ToastPosition.TopRight -> ToastItemAnimate(
            pos = Alignment.TopEnd,
            pd = PaddingValues(top = animatedPadding, end = 32.dp)
        )

        ToastPosition.BottomRight -> ToastItemAnimate(
            pos = Alignment.BottomEnd,
            pd = PaddingValues(bottom = animatedPadding, end = 32.dp)
        )
    }
}

data class ItemSizeModel(
    val padding: PaddingValues,
    val gap: Int,
    val iconSize: Int,
    val textSize: TextStyle,
)

fun getToastItemSize(size: ToastSize): ItemSizeModel {
    return when (size) {
        ToastSize.XS -> ItemSizeModel(
            padding = PaddingValues(8.dp),
            gap = 8,
            iconSize = 16,
            textSize = RuTheme.typo.paragraphXS,
        )

        ToastSize.S -> ItemSizeModel(
            padding = PaddingValues(horizontal = 10.dp, vertical = 8.dp),
            gap = 8,
            iconSize = 20,
            textSize = RuTheme.typo.paragraphS,
        )

        ToastSize.L -> ItemSizeModel(
            padding = PaddingValues(start = 14.dp, end = 14.dp, top = 14.dp, bottom = 16.dp),
            gap = 12,
            iconSize = 20,
            textSize = RuTheme.typo.paragraphS,
        )
    }
}


data class ItemModel(
    val bgColor: Color,
    val textColor: Color,
    val icon: @Composable (si: Int) -> Unit,
)

fun getToastItem(status: ToastStatus, style: ToastStyle, rColor: RuColors): ItemModel {
    return when (status) {
        ToastStatus.ERROR -> {
            when (style) {
                ToastStyle.Filled -> ItemModel(
                    bgColor = rColor.errorBase,
                    textColor = rColor.textWhite,
                    icon = { si: Int ->
                        SvgIcon(SvgPath.error_warning_fill, si, rColor.bgWhite)
                    },
                )

                ToastStyle.Light -> ItemModel(
                    bgColor = rColor.errorLight,
                    textColor = rColor.textStrong,
                    icon = { si -> SvgIcon(SvgPath.error_warning_fill, si, rColor.errorBase) },
                )

                ToastStyle.Lighter -> ItemModel(
                    bgColor = rColor.errorLighter,
                    textColor = rColor.textStrong,
                    icon = { si -> SvgIcon(SvgPath.error_warning_fill, si, rColor.errorBase) },
                )

                ToastStyle.Stroke -> ItemModel(
                    bgColor = rColor.bgWhite,
                    textColor = rColor.textStrong,
                    icon = { si -> SvgIcon(SvgPath.error_warning_fill, si, rColor.errorBase) },
                )
            }
        }

        ToastStatus.WARNING -> {
            when (style) {
                ToastStyle.Filled -> ItemModel(
                    bgColor = rColor.warningBase,
                    textColor = rColor.textWhite,
                    icon = { si -> SvgIcon(SvgPath.alert_fill, si, rColor.bgWhite) },
                )

                ToastStyle.Light -> ItemModel(
                    bgColor = rColor.warningLight,
                    textColor = rColor.textStrong,
                    icon = { si -> SvgIcon(SvgPath.alert_fill, si, rColor.warningBase) },
                )

                ToastStyle.Lighter -> ItemModel(
                    bgColor = rColor.warningLighter,
                    textColor = rColor.textStrong,
                    icon = { si -> SvgIcon(SvgPath.alert_fill, si, rColor.warningBase) },
                )

                ToastStyle.Stroke -> ItemModel(
                    bgColor = rColor.bgWhite,
                    textColor = rColor.textStrong,
                    icon = { si -> SvgIcon(SvgPath.alert_fill, si, rColor.warningBase) },
                )
            }
        }

        ToastStatus.SUCCESS -> {
            when (style) {
                ToastStyle.Filled -> ItemModel(
                    bgColor = rColor.successBase,
                    textColor = rColor.textWhite,
                    icon = { si -> SvgIcon(SvgPath.select_box_circle_fill, si, rColor.bgWhite) },
                )

                ToastStyle.Light -> ItemModel(
                    bgColor = rColor.successLight,
                    textColor = rColor.textStrong,
                    icon = { si ->
                        SvgIcon(
                            SvgPath.select_box_circle_fill,
                            si,
                            rColor.successBase
                        )
                    },
                )

                ToastStyle.Lighter -> ItemModel(
                    bgColor = rColor.successLighter,
                    textColor = rColor.textStrong,
                    icon = { si ->
                        SvgIcon(
                            SvgPath.select_box_circle_fill,
                            si,
                            rColor.successBase
                        )
                    },
                )

                ToastStyle.Stroke -> ItemModel(
                    bgColor = rColor.bgWhite,
                    textColor = rColor.textStrong,
                    icon = { si ->
                        SvgIcon(
                            SvgPath.select_box_circle_fill,
                            si,
                            rColor.successBase
                        )
                    },
                )
            }
        }

        ToastStatus.INFO -> {
            when (style) {
                ToastStyle.Filled -> ItemModel(
                    bgColor = rColor.primaryBase,
                    textColor = rColor.textWhite,
                    icon = { si -> SvgIcon(SvgPath.information_fill, si, rColor.bgWhite) },
                )

                ToastStyle.Light -> ItemModel(
                    bgColor = rColor.informationLight,
                    textColor = rColor.textStrong,
                    icon = { si -> SvgIcon(SvgPath.information_fill, si, rColor.primaryBase) },
                )

                ToastStyle.Lighter -> ItemModel(
                    bgColor = rColor.informationLighter,
                    textColor = rColor.textStrong,
                    icon = { si -> SvgIcon(SvgPath.information_fill, si, rColor.primaryBase) },
                )

                ToastStyle.Stroke -> ItemModel(
                    bgColor = rColor.bgWhite,
                    textColor = rColor.textStrong,
                    icon = { si -> SvgIcon(SvgPath.information_fill, si, rColor.primaryBase) },
                )
            }
        }

        ToastStatus.FEATURE -> {
            when (style) {
                ToastStyle.Filled -> ItemModel(
                    bgColor = rColor.fadedBase,
                    textColor = rColor.textWhite,
                    icon = { si -> SvgIcon(SvgPath.magic_fill, si, rColor.bgWhite) },
                )

                ToastStyle.Light -> ItemModel(
                    bgColor = rColor.fadedLight,
                    textColor = rColor.textStrong,
                    icon = { si -> SvgIcon(SvgPath.magic_fill, si, rColor.fadedBase) },
                )

                ToastStyle.Lighter -> ItemModel(
                    bgColor = rColor.fadedLighter,
                    textColor = rColor.textStrong,
                    icon = { si -> SvgIcon(SvgPath.magic_fill, si, rColor.fadedBase) },
                )

                ToastStyle.Stroke -> ItemModel(
                    bgColor = rColor.bgWhite,
                    textColor = rColor.textStrong,
                    icon = { si -> SvgIcon(SvgPath.magic_fill, si, rColor.fadedBase) },
                )
            }
        }
    }
}
