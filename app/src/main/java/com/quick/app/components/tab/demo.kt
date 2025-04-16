package com.quick.app.components.tab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.quick.app.components.spacer.RuSpacer
import com.quick.app.components.svgicon.SvgIcon

@Composable
fun RuTabDemo() {
    val index = remember { mutableStateOf(0) }
    Column {
        RuTab(
            modifier = Modifier.width(500.dp),
            selectIndex = index.value,
            options = listOf(
                TabItemModel("Overview"),
                TabItemModel(
                    "Overview",
                    iconLeft = { SvgIcon("icons/arrow-left-s-line.svg", tint = Color.Blue) },
                    iconRight = { SvgIcon("icons/arrow-right-s-line.svg", tint = Color.Blue) }
                ),
                TabItemModel("Overview"),
            ),
            onChange = { index.value = it }
        )
        RuSpacer(h = 24)
        RuTabSegmented(
            modifier = Modifier.width(500.dp),
            selectIndex = index.value,
            options = listOf(
                TabItemModel("Overview"),
                TabItemModel(
                    "Overview",
                    iconLeft = { SvgIcon("icons/arrow-left-s-line.svg", tint = Color.Blue) },
                    iconRight = { SvgIcon("icons/arrow-right-s-line.svg", tint = Color.Blue) }
                ),
                TabItemModel("Overview"),
            ),
            onChange = { index.value = it }
        )
    }

}