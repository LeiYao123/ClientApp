package com.quick.app.components.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.quick.app.components.button.RuButton
import com.quick.app.components.button.RuButtonStyle
import com.quick.app.components.checkbox.BoxType
import com.quick.app.components.checkbox.RuCheckBox
import com.quick.app.components.clickOutsideToBlur.ClickOutsideToBlur
import com.quick.app.components.spacer.RuSpacer
import com.quick.app.components.svgicon.SvgPath
import com.quick.app.ui.theme.RuTheme

@Composable
fun InputRouteDemo() {
    val currSize = remember { mutableStateOf(InputSize.M) }
    val showAddon = remember { mutableStateOf(true) }
    val showIcon = remember { mutableStateOf(listOf<String>("left", "right")) }
    val state = remember { mutableStateOf(InputState.NORMAL) }
    ClickOutsideToBlur {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row {
                InputSize.entries.forEach {
                    RuCheckBox(
                        type = BoxType.RADIO,
                        text = it.name,
                        checked = currSize.value == it,
                        onChange = { _ -> currSize.value = it }
                    )
                    RuSpacer(12)
                }
            }
            RuCheckBox(
                type = BoxType.CHECKBOX,
                text = "show Addon",
                checked = showAddon.value,
                onChange = { checked -> showAddon.value = checked }
            )
            RuSpacer(12)
            Row {
                listOf("left", "right").forEach {
                    RuCheckBox(
                        type = BoxType.CHECKBOX,
                        text = it,
                        checked = showIcon.value.contains(it),
                        onChange = { checked ->
                            showIcon.value.plus("eed")
                            if (checked) {
                                showIcon.value = showIcon.value.plus(it)
                            } else {
                                showIcon.value = showIcon.value.minus(it)
                            }
                        }
                    )
                    RuSpacer(12)
                }
            }
            Row {
                InputState.entries.forEach {
                    RuCheckBox(
                        type = BoxType.RADIO,
                        text = it.name,
                        checked = state.value == it,
                        onChange = { _ -> state.value = it }
                    )
                    RuSpacer(12)
                }
            }


            CustomTextFieldDemo(currSize.value, showIcon.value, state.value, showAddon.value)
        }
    }
}

@Composable
fun CustomTextFieldDemo(
    size: InputSize,
    icons: List<String>,
    state: InputState,
    showAddon: Boolean,
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier.size(300.dp, 50.dp),
        placeholder = { Text("placeholder...") }
    )

    val leftAddon: @Composable (Boolean, Color) -> Unit =
        { disabled, _ ->
            Text(
                "https://",
                modifier = Modifier.padding(horizontal = 10.dp),
                color = if (disabled) RuTheme.colors.textDisabled else RuTheme.colors.textStrong,
            )
        }
    val rightAddon: @Composable (Boolean, Color) -> Unit =
        { disabled, _ ->
            RuButton(
                text = "Add",
                style = RuButtonStyle.GHOST,
                enabled = !disabled,
                modifier = Modifier.padding(horizontal = 10.dp),
                onClick = {
                    println("Go")
                }
            )
        }


    CustomTextField(
        value = text,
        onValueChange = { text = it },
        size = size,
        isError = state == InputState.ERROR,
        disabled = state == InputState.DISABLED,
        placeholder = "Placeholder text...",
        leftIcon = if (!showAddon && icons.contains("left")) SvgPath.search_2_line else null,
        rightIcon = if (!showAddon && icons.contains("right")) SvgPath.close_line else null,
        leftAddon = if (showAddon && icons.contains("left")) leftAddon else null,
        rightAddon = if (showAddon && icons.contains("right")) rightAddon else null,
        imeAction = ImeAction.Done,
        onImeAction = {
            println("提交逻辑")
        }
    )
}