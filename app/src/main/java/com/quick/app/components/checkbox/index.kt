package com.quick.app.components.checkbox

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.quick.app.components.spacer.Expanded
import com.quick.app.components.spacer.RuSpacer
import com.quick.app.ui.theme.RuTheme


@Composable
fun RuCheckBox(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    checked: Boolean = false,
    isToggle: Boolean = false,
    indeterminate: Boolean = false,
    text: String? = null,
    isFlip: Boolean = false,
    subLabel: @Composable (() -> Unit)? = null,
    badge: @Composable (() -> Unit)? = null,
    desc: @Composable (() -> Unit)? = null,
    onChange: ((Boolean) -> Unit)? = null,
) {
    val rColor = RuTheme.colors
    val secondaryColor = if (enabled) rColor.textSub else rColor.textSoft

    Column(
        modifier = modifier.clickable { if (enabled && onChange != null) onChange(!checked) },
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (!isFlip) {
                CheckBoxIcon(
                    enabled = enabled,
                    checked = checked,
                    isToggle = isToggle,
                    indeterminate = indeterminate
                )
                RuSpacer(8)
            }

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
            if (isFlip) {
                Expanded()
                CheckBoxIcon(
                    enabled = enabled,
                    checked = checked,
                    isToggle = isToggle,
                    indeterminate = indeterminate
                )
            }
        }
        RuSpacer(h = 4)
        if (desc != null) {
            val descEl: @Composable () -> Unit = {
                CompositionLocalProvider(LocalContentColor provides secondaryColor) {
                    desc()
                }
            }

            if (isFlip) descEl()
            else
                Row {
                    RuSpacer(28)
                    descEl()
                }
        }
    }
}