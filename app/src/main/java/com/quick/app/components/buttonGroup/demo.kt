package com.quick.app.components.buttonGroup

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.quick.app.components.spacer.RuSpacer

@Composable
fun RuButtonGroupDemo() {
    val index1 = remember { mutableStateOf(0) }
    val index2 = remember { mutableStateOf(0) }
    val index3 = remember { mutableStateOf(0) }

    Column {
        RuButtonGroup(
            size = RuButtonGroupSize.S,
            selectedIndex = index1.value,
            onChange = { index1.value = it },
            options = listOf(ButtonItemModel("Button"))
        )
        RuSpacer(h = 16)

        RuButtonGroup(
            size = RuButtonGroupSize.XS,
            selectedIndex = index2.value,
            onChange = { index2.value = it },
            options = listOf(
                ButtonItemModel("Button"),
                ButtonItemModel("Button"),
            )
        )
        RuSpacer(h = 16)

        RuButtonGroup(
            size = RuButtonGroupSize.XXS,
            selectedIndex = index2.value,
            onChange = { index2.value = it },
            options = listOf(
                ButtonItemModel("Button"),
                ButtonItemModel("Button"),
                ButtonItemModel("Button"),
            )
        )
        RuSpacer(h = 16)

        RuButtonGroup(
            selectedIndex = index3.value,
            onChange = { index3.value = it },
            options = listOf(
                ButtonItemModel("Button", iconLeft = "icons/arrow-left-s-line.svg"),
                ButtonItemModel(
                    "Button",
                    enabled = false,
                    iconRight = "icons/arrow-right-s-line.svg",
                    iconLeft = "icons/arrow-left-s-line.svg"
                ),
                ButtonItemModel("Button", iconRight = "icons/arrow-right-s-line.svg"),
                ButtonItemModel("Button", iconRight = "icons/arrow-right-s-line.svg"),
            )
        )
        RuSpacer(h = 16)
    }
}