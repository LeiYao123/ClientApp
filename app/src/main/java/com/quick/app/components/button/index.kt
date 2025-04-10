@file:Suppress("ConstPropertyName")

package com.quick.app.components.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.quick.app.components.loading.Loading
import com.quick.app.ui.theme.RuTheme


val shape = RoundedCornerShape(size = RuTheme.radius.radius10)

@Composable
fun RuButton(
    text: String? = null,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    type: RuButtonType = RuButtonType.NEUTRAL,
    style: RuButtonStyle = RuButtonStyle.STROKE,
    size: RuButtonSize = RuButtonSize.M,
    iconLeft: ImageVector? = null,
    iconRight: ImageVector? = null,
    content: @Composable (() -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val rColor = RuTheme.colors
    val color = getRuButtonType(type, style, enabled, rColor)

    val textColor = if (isPressed) color.focusColor else color.color

    val backgroundColor by animateColorAsState(
        targetValue = if (isPressed) color.focusBg else color.bg,
        label = "ButtonColor"
    )
    val alpha by animateFloatAsState(
        targetValue = getBorderAlpha(isPressed, style),
        animationSpec = tween(durationMillis = 300),
        label = "FadeAlpha"
    )

    val onlyIcon = (iconLeft != null || iconRight != null) && text == null && content == null
    val rect = getRuButtonSize(size, onlyIcon)

    Row(
        horizontalArrangement = Arrangement.spacedBy(rect.gap, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick
            )
            .let {
                if (type == RuButtonType.PRIMARY) it.shadow(
                    elevation = 2.dp,
                    spotColor = rColor.primaryAlpha10,
                    ambientColor = rColor.primaryAlpha10
                ) else it
            }
            .let { if (onlyIcon) it.size(rect.height) else it.height(rect.height) }
            .clip(shape)
            .background(backgroundColor)
            .let {
                if (!enabled) it
                else
                    when (style) {
                        RuButtonStyle.FILLED,
                        RuButtonStyle.GHOST,
                        -> it

                        RuButtonStyle.STROKE,
                        RuButtonStyle.LIGHTER,
                        -> {
                            val borderColor = when (type) {
                                RuButtonType.PRIMARY -> rColor.primaryBase
                                RuButtonType.NEUTRAL -> rColor.strokeSoft
                                RuButtonType.ERROR -> rColor.errorBase
                            }
                            it.border(1.dp, borderColor.copy(alpha), shape)
                        }
                    }
            }
            .padding(horizontal = rect.padding)

    ) {
        if (loading) Loading(tint = textColor)
        else
            if (content != null) content()
            else {
                if (iconLeft != null)
                    Icon(
                        iconLeft,
                        modifier = Modifier.size(20.dp),
                        contentDescription = text,
                        tint = textColor
                    )
                if (text != null) Text(text, style = RuTheme.typo.labelS, color = textColor)
                if (iconRight != null)
                    Icon(
                        iconRight,
                        modifier = Modifier.size(20.dp),
                        contentDescription = text,
                        tint = textColor
                    )
            }
    }
}


