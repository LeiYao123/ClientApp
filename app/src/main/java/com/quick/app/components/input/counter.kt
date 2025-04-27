package com.quick.app.components.input

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.quick.app.components.button.RuCompactButton
import com.quick.app.components.button.RuCompactSize
import com.quick.app.components.button.RuCompactStyle
import com.quick.app.components.svgicon.SvgPath
import com.quick.app.extension.safeToInt

@Composable
fun CounterInput(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    size: InputSize = InputSize.M,
    isError: Boolean = false,
    disabled: Boolean = false,
    placeholder: String = "",
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Number,
    onImeAction: () -> Unit = {},
) {
    RuInput(
        value = value.toString(),
        onValueChange = { onValueChange(it.safeToInt()) },
        size = size,
        isError = isError,
        disabled = disabled,
        placeholder = placeholder,
        imeAction = imeAction,
        keyboardType = keyboardType,
        modifier = modifier,
        contentAlignment = Alignment.Center,
        onImeAction = onImeAction,
        leftIcon = IconType.Slot {
            RuCompactButton(
                icon = SvgPath.subtract_line,
                style = RuCompactStyle.GHOST,
                size = RuCompactSize.L,
                onClick = {
                    onValueChange(value - 1)
                }
            )
        },
        rightIcon = IconType.Slot {
            RuCompactButton(
                icon = SvgPath.add_line,
                style = RuCompactStyle.GHOST,
                size = RuCompactSize.L,
                onClick = {
                    onValueChange(value + 1)
                }
            )
        }
    )
}