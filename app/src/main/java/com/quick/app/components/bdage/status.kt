package com.quick.app.components.bdage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.quick.app.components.svgicon.SvgIcon
import com.quick.app.ui.theme.RuColors
import com.quick.app.ui.theme.RuTheme

enum class RuBadgeStatus {
    COMPLETED,
    PENDING,
    FAILED,
    DISABLED,
}

enum class RuStatusBadgeStyle { STROKE, LIGHT }

@Composable
fun RuStatusBadge(
    text: String = "",
    modifier: Modifier = Modifier,
    status: RuBadgeStatus = RuBadgeStatus.COMPLETED,
    style: RuStatusBadgeStyle = RuStatusBadgeStyle.STROKE,
    icon: String? = null,
) {
    val rColor = RuTheme.colors
    val color = getStatusBadgeColor(status, style, rColor)
    val shape = RoundedCornerShape(RuTheme.radius.radius6)

    val pl = if (icon != null) 4 else 8

    Row(
        modifier = modifier
            .height(24.dp)
            .clip(shape)
            .background(color.bg)
            .let {
                if (style == RuStatusBadgeStyle.STROKE)
                    it.border(1.dp, rColor.strokeSoft, shape)
                else it
            }
            .padding(start = pl.dp, end = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (icon != null) SvgIcon(icon, size = 16, tint = color.icon)
        Text(text, style = RuTheme.typo.labelXS, color = color.text)
    }
}


private data class StatusColor(
    val bg: Color,
    val text: Color,
    val icon: Color,
)

private fun getStatusBadgeColor(
    status: RuBadgeStatus,
    style: RuStatusBadgeStyle,
    c: RuColors,
): StatusColor {
    return when (status) {
        RuBadgeStatus.COMPLETED -> {
            when (style) {
                RuStatusBadgeStyle.STROKE -> StatusColor(c.bgWhite, c.textSub, c.successBase)
                RuStatusBadgeStyle.LIGHT -> StatusColor(
                    c.successLighter,
                    c.successBase,
                    c.successBase
                )
            }
        }

        RuBadgeStatus.PENDING -> {
            when (style) {
                RuStatusBadgeStyle.STROKE -> StatusColor(c.bgWhite, c.textSub, c.warningBase)
                RuStatusBadgeStyle.LIGHT -> StatusColor(
                    c.warningLighter,
                    c.warningBase,
                    c.warningBase
                )
            }
        }

        RuBadgeStatus.FAILED -> {
            when (style) {
                RuStatusBadgeStyle.STROKE -> StatusColor(c.bgWhite, c.textSub, c.errorBase)
                RuStatusBadgeStyle.LIGHT -> StatusColor(
                    c.errorLighter,
                    c.errorBase,
                    c.errorBase
                )
            }
        }

        RuBadgeStatus.DISABLED -> {
            when (style) {
                RuStatusBadgeStyle.STROKE -> StatusColor(c.bgWhite, c.textSub, c.fadedBase)
                RuStatusBadgeStyle.LIGHT -> StatusColor(
                    c.fadedLighter,
                    c.textSub,
                    c.fadedBase
                )
            }
        }
    }
}


