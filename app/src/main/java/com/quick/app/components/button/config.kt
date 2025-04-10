package com.quick.app.components.button

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.quick.app.ui.theme.PaletteTokens
import com.quick.app.ui.theme.RuColors

enum class RuButtonType {
    PRIMARY,
    NEUTRAL,
    ERROR
}

enum class RuButtonStyle {
    FILLED,
    STROKE,
    LIGHTER,
    GHOST
}

enum class RuButtonSize {
    XL,
    L,
    M,
    S,
    XS,
    XXS,
}


fun getBorderAlpha(pressed: Boolean, style: RuButtonStyle): Float {
    if (style == RuButtonStyle.STROKE && !pressed) return 1f
    if (style == RuButtonStyle.LIGHTER && pressed) return 1f
    return 0f
}


data class RectSize(
    val padding: Dp,
    val gap: Dp,
    val height: Dp,
)

fun getRuButtonSize(size: RuButtonSize, onlyIcon: Boolean = false): RectSize {
    if (onlyIcon) {
        return when (size) {
            RuButtonSize.XL -> RectSize(padding = 0.dp, gap = 0.dp, height = 48.dp)
            RuButtonSize.L -> RectSize(padding = 0.dp, gap = 0.dp, height = 44.dp)
            RuButtonSize.M -> RectSize(padding = 0.dp, gap = 0.dp, height = 40.dp)
            RuButtonSize.S -> RectSize(padding = 0.dp, gap = 0.dp, height = 36.dp)
            RuButtonSize.XS -> RectSize(padding = 0.dp, gap = 0.dp, height = 32.dp)
            RuButtonSize.XXS -> RectSize(padding = 0.dp, gap = 0.dp, height = 28.dp)
        }
    }
    return when (size) {
        RuButtonSize.XL -> RectSize(padding = 14.dp, gap = 4.dp, height = 48.dp)
        RuButtonSize.L -> RectSize(padding = 12.dp, gap = 4.dp, height = 44.dp)
        RuButtonSize.M -> RectSize(padding = 10.dp, gap = 4.dp, height = 40.dp)
        RuButtonSize.S -> RectSize(padding = 8.dp, gap = 4.dp, height = 36.dp)
        RuButtonSize.XS -> RectSize(padding = 6.dp, gap = 2.dp, height = 32.dp)
        RuButtonSize.XXS -> RectSize(padding = 6.dp, gap = 2.dp, height = 28.dp)
    }
}


data class BgColor(
    val bg: Color,
    val focusBg: Color,
    val color: Color,
    val focusColor: Color,
)

fun getRuButtonType(
    type: RuButtonType,
    style: RuButtonStyle,
    enabled: Boolean,
    rColor: RuColors,
): BgColor {
    if (!enabled) {
        return BgColor(
            rColor.bgWeak,
            rColor.bgWeak,
            rColor.textDisabled,
            rColor.textDisabled,
        )
    }
    return when (type) {
        RuButtonType.PRIMARY -> {
            when (style) {
                RuButtonStyle.FILLED -> BgColor(
                    rColor.primaryBase,
                    rColor.primaryDarker,
                    rColor.textWhite,
                    rColor.textWhite,
                )

                RuButtonStyle.STROKE -> BgColor(
                    rColor.bgWhite.copy(0f),
                    rColor.primaryAlpha10,
                    rColor.primaryBase,
                    rColor.primaryBase,
                )

                RuButtonStyle.LIGHTER -> BgColor(
                    rColor.primaryAlpha10,
                    rColor.bgWhite.copy(alpha = 0f),
                    rColor.primaryBase,
                    rColor.primaryBase,
                )

                RuButtonStyle.GHOST -> BgColor(
                    Color.Transparent,
                    rColor.primaryAlpha10,
                    rColor.primaryBase,
                    rColor.primaryBase,
                )
            }
        }

        RuButtonType.NEUTRAL -> {
            when (style) {
                RuButtonStyle.FILLED -> BgColor(
                    rColor.bgStrong,
                    rColor.bgSurface,
                    rColor.textWhite,
                    rColor.textWhite,
                )

                RuButtonStyle.STROKE -> BgColor(
                    rColor.bgWhite,
                    rColor.bgWeak,
                    rColor.textSub,
                    rColor.textStrong
                )

                RuButtonStyle.LIGHTER -> BgColor(
                    rColor.bgWeak,
                    rColor.bgWhite,
                    rColor.textSub,
                    rColor.textStrong
                )

                RuButtonStyle.GHOST -> BgColor(
                    rColor.bgWhite.copy(alpha = 0f),
                    rColor.bgWeak.copy(alpha = 1f),
                    rColor.textSub,
                    rColor.textStrong
                )
            }
        }

        RuButtonType.ERROR -> {
            when (style) {
                RuButtonStyle.FILLED -> BgColor(
                    rColor.errorBase,
                    PaletteTokens.red_700,
                    rColor.textWhite,
                    rColor.textWhite
                )

                RuButtonStyle.STROKE -> BgColor(
                    rColor.bgWhite.copy(0f),
                    PaletteTokens.red_alpha_10,
                    rColor.errorBase,
                    rColor.errorBase
                )

                RuButtonStyle.LIGHTER -> BgColor(
                    PaletteTokens.red_alpha_10,
                    rColor.bgWhite.copy(alpha = 0f),
                    rColor.errorBase,
                    rColor.errorBase
                )

                RuButtonStyle.GHOST -> BgColor(
                    rColor.bgWhite.copy(alpha = 0f),
                    PaletteTokens.red_alpha_10,
                    rColor.errorBase,
                    rColor.errorBase
                )
            }
        }
    }
}

