package com.quick.app.components.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.quick.app.ui.theme.RuColors
import com.quick.app.ui.theme.RuTheme


enum class RuCompactSize { L, M }
enum class RuCompactStyle { STROKE, GHOST, WHITE }

@Composable
fun RuCompactButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    style: RuCompactStyle = RuCompactStyle.STROKE,
    size: RuCompactSize = RuCompactSize.L,
    icon: ImageVector? = null,
    content: @Composable (() -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val rColor = RuTheme.colors
    val color = getRuCompactButton(style, enabled, rColor)

    val textColor = if (isPressed) color.focusColor else color.color

    val backgroundColor by animateColorAsState(
        targetValue = if (isPressed) color.focusBg else color.bg,
        label = "ButtonColor"
    )

    val btnSize = when (size) {
        RuCompactSize.L -> 24.dp
        RuCompactSize.M -> 20.dp
    }


    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick
            )
            .let {
                if (style == RuCompactStyle.GHOST || style == RuCompactStyle.WHITE) it.shadow(
                    elevation = 2.dp,
                    spotColor = Color(0x080A0D14),
                    ambientColor = Color(0x080A0D14)
                )
                else it
            }
            .size(btnSize)
            .clip(RoundedCornerShape(RuTheme.radius.radius6))
            .background(backgroundColor)
            .let {
                if (!enabled) it
                else {
                    if (style == RuCompactStyle.STROKE) it.border(
                        width = 1.dp,
                        color = rColor.strokeSoft.copy(if (isPressed) 0f else 1f),
                        shape = RoundedCornerShape(RuTheme.radius.radius6)
                    )
                    else it
                }

            }
            .padding(if (size == RuCompactSize.L) 2.dp else 1.dp)

    ) {
        if (content != null) content()
        if (icon != null)
            Icon(
                icon,
                modifier = Modifier.size(if (size == RuCompactSize.L) 20.dp else 18.dp),
                contentDescription = "Close",
                tint = textColor
            )
    }
}


data class CompactBgColor(
    val bg: Color,
    val focusBg: Color,
    val color: Color,
    val focusColor: Color,
)

fun getRuCompactButton(
    style: RuCompactStyle,
    enabled: Boolean,
    rColor: RuColors,
): CompactBgColor {
    if (!enabled) {
        return CompactBgColor(
            rColor.bgWhite.copy(0f),
            rColor.bgWeak.copy(0f),
            rColor.iconDisabled,
            rColor.iconDisabled,
        )
    }

    when (style) {
        RuCompactStyle.STROKE -> {
            return CompactBgColor(
                rColor.bgWhite,
                rColor.bgWeak,
                rColor.iconSub,
                rColor.iconStrong
            )
        }

        RuCompactStyle.GHOST -> {
            return CompactBgColor(
                rColor.bgWhite.copy(0f),
                rColor.bgWeak.copy(1f),
                rColor.iconSub,
                rColor.iconStrong
            )
        }

        RuCompactStyle.WHITE -> {
            return CompactBgColor(
                rColor.bgWhite,
                rColor.bgWeak,
                rColor.iconSub,
                rColor.iconStrong
            )
        }
    }
}
