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
    type: BoxType = BoxType.CHECKBOX,
    enabled: Boolean = true,
    checked: Boolean = false,
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

    val icon: @Composable () -> Unit = {
        BoxIcon(
            type = type,
            enabled = enabled,
            checked = checked,
            indeterminate = indeterminate
        )
    }

    Column(
        modifier = modifier.clickable {
            if (enabled && onChange != null) {
                if (checked && type == BoxType.RADIO) return@clickable
                else onChange(!checked)
            }
        },
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (!isFlip) {
                icon()
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
                icon()
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