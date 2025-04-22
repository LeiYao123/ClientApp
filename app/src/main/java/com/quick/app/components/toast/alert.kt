package com.quick.app.components.toast

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.quick.app.components.svgicon.SvgIcon
import com.quick.app.components.svgicon.SvgPath
import com.quick.app.ui.theme.RuTheme

@Composable
fun Alert(
    message: String,
    desc: String? = null,
    status: ToastStatus = ToastStatus.INFO,
    style: ToastStyle = ToastStyle.Filled,
    size: ToastSize = ToastSize.S,
    showDismiss: Boolean = false,
    onDismiss: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val rColor = RuTheme.colors
    val shape = RoundedCornerShape(size = RuTheme.radius.radius8)
    val cfg = getToastItem(status, style, rColor)
    val cfgSize = getToastItemSize(size)


    val titleRow: @Composable () -> Unit = {
        Row(
            horizontalArrangement = Arrangement.spacedBy(cfgSize.gap.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            cfg.icon(cfgSize.iconSize)
            Text(
                text = message,
                color = cfg.textColor,
                style = cfgSize.textSize,
                modifier = Modifier.weight(1f)
            )
            if (showDismiss) {
                SvgIcon(
                    SvgPath.close_line,
                    size = cfgSize.iconSize,
                    tint = Color.White.copy(alpha = 0.72f),
                    modifier = Modifier.clickable { onDismiss() }
                )
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .let {
                if (style == ToastStyle.Stroke) {
                    it
                        .shadow(
                            elevation = 32.dp,
                            spotColor = Color(0x1A0E121B),
                            ambientColor = Color(0x1A0E121B)
                        )
                        .border(
                            width = 1.dp,
                            color = rColor.strokeSoft,
                            shape = shape
                        )
                } else it
            }
            .background(cfg.bgColor, shape)
            .padding(cfgSize.padding),
    ) {
        if (desc == null) titleRow()
        else Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            titleRow()
            Text(
                desc,
                color = cfg.textColor.copy(alpha = if (style == ToastStyle.Filled) 1f else 0.72f),
                style = RuTheme.typo.paragraphS,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }
    }
}
