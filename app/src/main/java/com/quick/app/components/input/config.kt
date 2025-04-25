package com.quick.app.components.input

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.quick.app.ui.theme.RuColors

enum class InputSize { M, S, XS }


data class InputSizeModel(
    val h: Dp,
    val gap: Dp,
    val pl: Dp,
    val pr: Dp,
    val addonPd: Dp,
)

fun getInputSize(
    size: InputSize,
): InputSizeModel {
    return when (size) {
        InputSize.M -> InputSizeModel(
            h = 40.dp,
            gap = 8.dp,
            pl = 12.dp,
            pr = 10.dp,
            addonPd = 10.dp
        )

        InputSize.S -> InputSizeModel(
            h = 36.dp,
            gap = 8.dp,
            pl = 10.dp,
            pr = 8.dp,
            addonPd = 8.dp
        )

        InputSize.XS -> InputSizeModel(
            h = 32.dp,
            gap = 6.dp,
            pl = 8.dp,
            pr = 6.dp,
            addonPd = 6.dp
        )
    }
}


data class InputColorModel(
    val borderColor: Color,
    val bgColor: Color,
    val textColor: Color,
    val iconColor: Color,
    val placeholder: Color,
)

enum class InputState {
    NORMAL,
    FOCUS,
    DISABLED,
    ERROR,
}

fun getInputColor(state: InputState, hasText: Boolean, rColor: RuColors): InputColorModel {
    return when (state) {
        InputState.NORMAL -> InputColorModel(
            borderColor = rColor.strokeSoft,
            bgColor = rColor.bgWhite,
            textColor = rColor.textStrong,
            iconColor = if (hasText) rColor.iconSub else rColor.textSoft,
            placeholder = rColor.textSoft,
        )

        InputState.FOCUS -> InputColorModel(
            borderColor = rColor.strokeStrong,
            bgColor = rColor.bgWhite,
            textColor = rColor.textStrong,
            iconColor = if (hasText) rColor.iconSub else rColor.textSoft,
            placeholder = rColor.textSoft,
        )

        InputState.DISABLED -> InputColorModel(
            borderColor = rColor.strokeSoft,
            bgColor = rColor.bgWeak,
            textColor = rColor.textDisabled,
            iconColor = rColor.iconDisabled,
            placeholder = rColor.textDisabled,
        )

        InputState.ERROR -> InputColorModel(
            borderColor = rColor.errorBase,
            bgColor = rColor.bgWhite,
            textColor = rColor.textStrong,
            iconColor = if (hasText) rColor.iconSub else rColor.textSoft,
            placeholder = rColor.textSoft,
        )
    }
}