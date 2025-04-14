package com.quick.app.components.checkbox

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.quick.app.components.spacer.Expanded
import com.quick.app.components.spacer.RuSpacer
import com.quick.app.ui.theme.RuTheme


@Composable
fun RuCheckBoxCard(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    checked: Boolean = false,
    isToggle: Boolean = false,
    indeterminate: Boolean = false,
    text: String? = null,
    icon: @Composable (() -> Unit)? = null,
    subLabel: @Composable (() -> Unit)? = null,
    badge: @Composable (() -> Unit)? = null,
    desc: @Composable (() -> Unit)? = null,
    onChange: ((Boolean) -> Unit)? = null,
) {
    val rColor = RuTheme.colors

    val mod = modifier
        .clickable { onChange?.invoke(!checked) }
        .clip(RoundedCornerShape(RuTheme.radius.radius12))
        .background(rColor.bgWhite)
        .border(
            width = 1.dp,
            color = if (checked && enabled) rColor.primaryBase else rColor.strokeSoft,
            shape = RoundedCornerShape(RuTheme.radius.radius12)
        )
        .padding(16.dp)

    val main: @Composable () -> Unit = {
        val secondaryColor = if (enabled) rColor.textSub else rColor.textSoft

        Row(verticalAlignment = Alignment.CenterVertically) {
            if (text != null) {
                Text(
                    text,
                    style = RuTheme.typo.paragraphS,
                    color = if (enabled) rColor.textStrong else rColor.textSub
                )
                RuSpacer(4)
            }
            if (subLabel != null) {
                CompositionLocalProvider(LocalContentColor provides secondaryColor) {
                    subLabel()
                }
                RuSpacer(4)
            }
            if (badge != null) badge()
            Expanded()
            CheckBoxIcon(
                enabled = enabled,
                checked = checked,
                isToggle = isToggle,
                indeterminate = indeterminate
            )
        }
        RuSpacer(h = 4)
        if (desc != null) {
            CompositionLocalProvider(LocalContentColor provides secondaryColor) {
                desc()
            }
        }
    }

    if (icon != null)
        Row(
            modifier = mod,
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon()
            RuSpacer(w = 14)
            Column { main() }
        }
    else Column(mod) { main() }
}