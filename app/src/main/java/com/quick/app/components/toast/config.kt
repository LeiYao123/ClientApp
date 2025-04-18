package com.quick.app.components.toast

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.quick.app.components.svgicon.SvgIcon
import com.quick.app.components.svgicon.SvgPath
import com.quick.app.ui.theme.RuColors
import com.quick.app.ui.theme.RuTheme


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
