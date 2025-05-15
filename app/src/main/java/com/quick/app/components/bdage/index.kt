package com.quick.app.components.bdage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.quick.app.components.svgicon.SvgIcon
import com.quick.app.ui.theme.RuColors
import com.quick.app.ui.theme.RuTheme

enum class RuBadgeType {
    GRAY,
    BLUE,
    ORANGE,
    RED,
    GREEN,
    YELLOW,
    PURPLE,
    SKY,
    PINK,
    TEAL,
}

enum class RuBadgeSize { M, S }

enum class RuBadgeStyle {
    FILLED,
    LIGHT,
    LIGHTER,
    STROKE,
}

@Composable
fun RuBadge(
    text: String = "",
    modifier: Modifier = Modifier,
    type: RuBadgeType = RuBadgeType.GRAY,
    size: RuBadgeSize = RuBadgeSize.M,
    style: RuBadgeStyle = RuBadgeStyle.FILLED,
    iconLeft: String? = null,
    iconRight: String? = null,
    isNumber: Boolean = false,
) {
    val rColor = RuTheme.colors
    val color = getBadgeColor(type, style, rColor)
    val badgeSize = getBadgeSize(size)

    val pl = if (iconLeft != null) 4 else 8
    val pr = if (iconRight != null) 4 else 8


    Row(
        modifier = modifier
            .let {
                if (isNumber) it.size(badgeSize.row)
                else it.height(badgeSize.row)
            }
            .clip(RoundedCornerShape(50.dp))
            .background(color.bg)
            .let {
                if (style == RuBadgeStyle.STROKE)
                    it.border(1.dp, color.text, RoundedCornerShape(50.dp))
                else it
            }
            .let {
                if (isNumber) it
                else it.padding(start = pl.dp, end = pr.dp)
            },
        horizontalArrangement = if (isNumber) Arrangement.Center else Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (iconLeft != null) SvgIcon(iconLeft, size = badgeSize.icon, tint = color.text)
        Text(text, style = badgeSize.text, color = color.text)
        if (iconRight != null) SvgIcon(iconRight, size = badgeSize.icon, tint = color.text)
    }
}


private data class BadgeSize(
    val text: TextStyle,
    val icon: Int,
    val row: Dp,
)

private fun getBadgeSize(size: RuBadgeSize): BadgeSize {
    return when (size) {
        RuBadgeSize.M -> BadgeSize(RuTheme.typo.labelS, 16, 20.dp)
        RuBadgeSize.S -> BadgeSize(RuTheme.typo.subheading2XS, 12, 16.dp)
    }
}

private data class BadgeColor(
    val bg: Color,
    val text: Color,
)

private fun getBadgeColor(type: RuBadgeType, style: RuBadgeStyle, c: RuColors): BadgeColor {
    return when (type) {
        RuBadgeType.GRAY -> {
            when (style) {
                RuBadgeStyle.FILLED -> BadgeColor(c.fadedBase, c.staticWhite)
                RuBadgeStyle.LIGHT -> BadgeColor(c.fadedLight, c.fadedDark)
                RuBadgeStyle.LIGHTER -> BadgeColor(c.fadedLighter, c.fadedBase)
                RuBadgeStyle.STROKE -> BadgeColor(Color.Transparent, c.fadedBase)
            }
        }

        RuBadgeType.BLUE -> {
            when (style) {
                RuBadgeStyle.FILLED -> BadgeColor(c.informationBase, c.staticWhite)
                RuBadgeStyle.LIGHT -> BadgeColor(c.informationLight, c.primaryDark)
                RuBadgeStyle.LIGHTER -> BadgeColor(c.informationLighter, c.informationBase)
                RuBadgeStyle.STROKE -> BadgeColor(Color.Transparent, c.informationBase)
            }
        }

        RuBadgeType.ORANGE -> {
            when (style) {
                RuBadgeStyle.FILLED -> BadgeColor(c.warningBase, c.staticWhite)
                RuBadgeStyle.LIGHT -> BadgeColor(c.warningLight, c.warningDark)
                RuBadgeStyle.LIGHTER -> BadgeColor(c.warningLighter, c.warningBase)
                RuBadgeStyle.STROKE -> BadgeColor(Color.Transparent, c.warningBase)
            }
        }

        RuBadgeType.RED -> {
            when (style) {
                RuBadgeStyle.FILLED -> BadgeColor(c.errorBase, c.staticWhite)
                RuBadgeStyle.LIGHT -> BadgeColor(c.errorLight, c.errorDark)
                RuBadgeStyle.LIGHTER -> BadgeColor(c.errorLighter, c.errorBase)
                RuBadgeStyle.STROKE -> BadgeColor(Color.Transparent, c.errorBase)
            }
        }

        RuBadgeType.GREEN -> {
            when (style) {
                RuBadgeStyle.FILLED -> BadgeColor(c.successBase, c.staticWhite)
                RuBadgeStyle.LIGHT -> BadgeColor(c.successLight, c.successDark)
                RuBadgeStyle.LIGHTER -> BadgeColor(c.successLighter, c.successBase)
                RuBadgeStyle.STROKE -> BadgeColor(Color.Transparent, c.successBase)
            }
        }

        RuBadgeType.YELLOW -> {
            when (style) {
                RuBadgeStyle.FILLED -> BadgeColor(c.awayBase, c.staticWhite)
                RuBadgeStyle.LIGHT -> BadgeColor(c.awayLight, c.awayDark)
                RuBadgeStyle.LIGHTER -> BadgeColor(c.awayLighter, c.awayBase)
                RuBadgeStyle.STROKE -> BadgeColor(Color.Transparent, c.awayBase)
            }
        }

        RuBadgeType.PURPLE -> {
            when (style) {
                RuBadgeStyle.FILLED -> BadgeColor(c.featureBase, c.staticWhite)
                RuBadgeStyle.LIGHT -> BadgeColor(c.featureLight, c.featureDark)
                RuBadgeStyle.LIGHTER -> BadgeColor(c.featureLighter, c.featureBase)
                RuBadgeStyle.STROKE -> BadgeColor(Color.Transparent, c.featureBase)
            }
        }

        RuBadgeType.SKY -> {
            when (style) {
                RuBadgeStyle.FILLED -> BadgeColor(c.verifiedBase, c.staticWhite)
                RuBadgeStyle.LIGHT -> BadgeColor(c.verifiedLight, c.verifiedDark)
                RuBadgeStyle.LIGHTER -> BadgeColor(c.verifiedLighter, c.verifiedBase)
                RuBadgeStyle.STROKE -> BadgeColor(Color.Transparent, c.verifiedBase)
            }
        }

        RuBadgeType.PINK -> {
            when (style) {
                RuBadgeStyle.FILLED -> BadgeColor(c.highlightedBase, c.staticWhite)
                RuBadgeStyle.LIGHT -> BadgeColor(c.highlightedLight, c.highlightedDark)
                RuBadgeStyle.LIGHTER -> BadgeColor(c.highlightedLighter, c.highlightedBase)
                RuBadgeStyle.STROKE -> BadgeColor(Color.Transparent, c.highlightedBase)
            }
        }

        RuBadgeType.TEAL -> {
            when (style) {
                RuBadgeStyle.FILLED -> BadgeColor(c.stableBase, c.staticWhite)
                RuBadgeStyle.LIGHT -> BadgeColor(c.stableLight, c.stableDark)
                RuBadgeStyle.LIGHTER -> BadgeColor(c.stableLighter, c.stableBase)
                RuBadgeStyle.STROKE -> BadgeColor(Color.Transparent, c.stableBase)
            }
        }
    }
}


