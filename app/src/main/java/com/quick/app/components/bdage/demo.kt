package com.quick.app.components.bdage


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.quick.app.components.spacer.RuSpacer

@Composable
fun RuBadgeDemo() {
    Scaffold { pd ->
        Surface(
            modifier = Modifier
                .padding(pd)
                .padding(16.dp),
            color = Color.Transparent
        ) {
            Column {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    RuBadgeType.entries.forEach { type ->
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            RuBadgeStyle.entries.forEach { style ->
                                RuBadge(
                                    text = "Badge",
                                    type = type,
                                    style = style,
                                    size = RuBadgeSize.M,
                                    iconLeft = "icons/search-2-line.svg",
                                    iconRight = "icons/search-2-line.svg",
                                )
                                RuBadge(
                                    text = "2",
                                    isNumber = true,
                                    type = type,
                                    style = style,
                                )
                            }
                        }
                    }
                }
                RuSpacer(h = 16)
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    RuBadgeStatus.entries.forEach { status ->
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            RuStatusBadgeStyle.entries.forEach { style ->
                                RuStatusBadge(
                                    text = "Badge",
                                    status = status,
                                    style = style,
                                    icon = "icons/search-2-line.svg",
                                )
                                RuStatusBadge(
                                    text = "Badge",
                                    status = status,
                                    style = style,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
