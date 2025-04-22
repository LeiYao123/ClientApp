package com.quick.app.components.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quick.app.components.button.RuCompactButton
import com.quick.app.components.button.RuCompactSize
import com.quick.app.components.button.RuCompactStyle
import com.quick.app.components.spacer.Expanded
import com.quick.app.components.svgicon.SvgIcon
import com.quick.app.components.svgicon.SvgPath
import com.quick.app.extension.bottomBorder
import com.quick.app.ui.theme.RuTheme


@Composable
fun RuDrawerHeader(
    modifier: Modifier = Modifier,
    showBack: Boolean = true,
    showDivider: Boolean = true,
    icon: @Composable (() -> Unit)? = null,
    title: String? = null,
    desc: String? = null,
    onClose: () -> Unit = {},
) {
    val rColor = RuTheme.colors
    val typo = RuTheme.typo
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(rColor.bgWhite)
            .let { if (showDivider) it.bottomBorder(1.dp, rColor.strokeSoft) else it }
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        val closeIcon: @Composable (modifier: Modifier) -> Unit = {
            RuCompactButton(
                modifier = it,
                style = RuCompactStyle.GHOST,
                size = RuCompactSize.L,
                icon = SvgPath.close_line,
                onClick = onClose
            )
        }
        if (showBack) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SvgIcon(SvgPath.arrow_left_line, modifier = Modifier.clickable { onClose() })
                Text("Back", style = typo.labelS, color = rColor.textSub)
                Expanded()
                closeIcon(Modifier)
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                icon.let { it?.invoke() }
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = title ?: "", style = typo.labelL, color = rColor.textStrong)
                    if (desc != null) {
                        Text(text = desc, style = typo.paragraphS, color = rColor.textSub)
                    }
                }
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                icon.let { it?.invoke() }
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(text = title ?: "", style = typo.labelL, color = rColor.textStrong)
                    if (desc != null) {
                        Text(text = desc, style = typo.paragraphS, color = rColor.textSub)
                    }
                }
                Expanded()
                closeIcon(Modifier.align(Alignment.Top))
            }
        }
    }
}