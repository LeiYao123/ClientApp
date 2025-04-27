package com.quick.app.components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.quick.app.ui.theme.RuTheme


@Composable
fun Textarea(
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int = 1000,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    disabled: Boolean = false,
    placeholder: String = "",
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Text,
    onImeAction: () -> Unit = {},
) {
    val rColor = RuTheme.colors
    val typo = RuTheme.typo
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val inputState = if (isError) InputState.ERROR
    else if (disabled) InputState.DISABLED
    else if (isFocused) InputState.FOCUS
    else InputState.NORMAL

    val cfgColor = getInputColor(inputState, value.isNotEmpty(), rColor)

    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        enabled = !disabled,
        textStyle = RuTheme.typo.paragraphS.copy(color = cfgColor.textColor),
        singleLine = false,
        maxLines = Int.MAX_VALUE,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(onDone = { onImeAction() }),
        cursorBrush = SolidColor(if (isError) Color.Red else Color.Black),
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(cfgColor.bgColor, shape)
                    .border(1.dp, cfgColor.borderColor, shape)
                    .height(120.dp)
                    .padding(horizontal = 10.dp, vertical = 12.dp)
            ) {

                Box(modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()) {
                    if (value.isEmpty()) {
                        Text(placeholder, style = typo.paragraphS, color = cfgColor.placeholder)
                    }
                    innerTextField()
                }
                Text(
                    text = "${value.length} / $maxLength",
                    style = typo.subheading2XS,
                    color = rColor.textSoft,
                )
            }
        }
    )
}
