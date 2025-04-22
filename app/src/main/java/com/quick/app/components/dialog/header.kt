package com.quick.app.components.dialog

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
import com.quick.app.components.svgicon.SvgPath
import com.quick.app.extension.bottomBorder
import com.quick.app.ui.theme.RuTheme


@Composable
fun RuDialogHeader(
    modifier: Modifier = Modifier,
    showDivider: Boolean = true,
    icon: @Composable (() -> Unit)? = null,
    title: String,
    desc: String? = null,
    onClose: () -> Unit = {},
) {
    val rColor = RuTheme.colors
    val typo = RuTheme.typo

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(rColor.bgWhite)
            .let { if (showDivider) it.bottomBorder(1.dp, rColor.strokeSoft) else it }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        val titleRow: @Composable () -> Unit = {
            Text(
                text = title,
                style = typo.labelL,
                color = rColor.textStrong,
                modifier = Modifier.weight(1f)
            )
            RuCompactButton(
                modifier = Modifier.clickable { onClose() },
                style = RuCompactStyle.GHOST,
                size = RuCompactSize.L,
                icon = SvgPath.close_line,
                onClick = onClose
            )
        }
        icon.let { it?.invoke() }
        if (desc == null) titleRow()
        else
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) { titleRow() }
                Text(text = desc, style = typo.paragraphS, color = rColor.textSub)
            }
    }
}