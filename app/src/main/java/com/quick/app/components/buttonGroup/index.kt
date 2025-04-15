package com.quick.app.components.buttonGroup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.quick.app.components.svgicon.SvgIcon
import com.quick.app.extension.leftBorder
import com.quick.app.ui.theme.RuTheme

@Composable
fun RuButtonGroup(
    modifier: Modifier = Modifier,
    size: RuButtonGroupSize = RuButtonGroupSize.S,
    selectedIndex: Int = 0,
    onChange: (Int) -> Unit,
    options: List<ButtonItemModel>,
) {
    val rColor = RuTheme.colors
    val shape = RoundedCornerShape(RuTheme.radius.radius8)
    Row(
        modifier = modifier
            .border(1.dp, rColor.strokeSoft, shape)
            .clip(shape),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        options.forEachIndexed { idx, item ->
            RuButtonItem(
                text = item.text,
                modifier = Modifier.let {
                    if (idx > 0) it.leftBorder(1.dp, rColor.strokeSoft)
                    else it
                },
                checked = idx == selectedIndex,
                size = size,
                enabled = item.enabled,
                iconLeft = item.iconLeft,
                iconRight = item.iconRight,
                onClick = { onChange(idx) }
            )
        }
    }
}


enum class RuButtonGroupSize { S, XS, XXS }

data class ButtonItemModel(
    val text: String,
    val enabled: Boolean = true,
    val iconLeft: String? = null,
    val iconRight: String? = null,
)

@Composable
fun RuButtonItem(
    text: String,
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    size: RuButtonGroupSize = RuButtonGroupSize.S,
    iconLeft: String? = null,
    iconRight: String? = null,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    val rColor = RuTheme.colors
    val itemSize = getItemSize(size, iconLeft, iconRight)
    val textColor = if (!enabled) {
        rColor.textDisabled
    } else {
        if (checked) rColor.textStrong else rColor.textSub
    }
    Row(
        modifier = modifier
            .clickable { if (enabled) onClick() }
            .height(itemSize.height)
            .background(if (checked) rColor.bgWeak else rColor.bgWhite)
            .padding(start = itemSize.pl, end = itemSize.pr),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(itemSize.gap)
    ) {
        if (iconLeft != null) {
            SvgIcon(path = iconLeft, size = itemSize.iconSize, tint = textColor)
        }
        Text(text, style = itemSize.textStyle, color = textColor)
        if (iconRight != null) {
            SvgIcon(path = iconRight, size = itemSize.iconSize, tint = textColor)
        }
    }
}


data class ItemSize(
    val pl: Dp,
    val pr: Dp,
    val gap: Dp,
    val height: Dp,
    val textStyle: TextStyle,
    val iconSize: Int,
)

fun getItemSize(
    size: RuButtonGroupSize,
    left: String? = null,
    right: String? = null,
): ItemSize {
    return when (size) {
        RuButtonGroupSize.S -> {
            ItemSize(
                pl = if (left != null) 8.dp else 16.dp,
                pr = if (right != null) 8.dp else 16.dp,
                gap = 8.dp,
                height = 36.dp,
                textStyle = RuTheme.typo.labelS,
                iconSize = 20
            )
        }

        RuButtonGroupSize.XS -> {
            ItemSize(
                pl = if (left != null) 6.dp else 14.dp,
                pr = if (right != null) 6.dp else 14.dp,
                gap = 6.dp,
                height = 32.dp,
                textStyle = RuTheme.typo.labelS,
                iconSize = 20,
            )
        }

        RuButtonGroupSize.XXS -> {
            ItemSize(
                pl = if (left != null) 4.dp else 12.dp,
                pr = if (right != null) 4.dp else 12.dp,
                gap = 4.dp,
                height = 24.dp,
                textStyle = RuTheme.typo.labelXS,
                iconSize = 16,
            )
        }
    }
}