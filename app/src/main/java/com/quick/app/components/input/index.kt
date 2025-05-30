package com.quick.app.components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.quick.app.components.svgicon.SvgIcon
import com.quick.app.extension.leftBorder
import com.quick.app.extension.rightBorder
import com.quick.app.ui.theme.RuTheme

val shape = RoundedCornerShape(10.dp)


sealed class IconType {
    data class Path(val path: String) : IconType()
    data class Slot(val slot: @Composable () -> Unit) : IconType()
}
@Composable
fun RuInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    size: InputSize = InputSize.M,
    isError: Boolean = false,
    disabled: Boolean = false,
    singleLine: Boolean = true,
    placeholder: String = "",
    leftIcon: IconType? = null,
    rightIcon: IconType? = null,
    leftAddon: @Composable ((disabled: Boolean, color: Color) -> Unit)? = null,
    rightAddon: @Composable ((disabled: Boolean, color: Color) -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Done,
    //•	KeyboardType.Text：普通文本
    //•	KeyboardType.Number：数字键盘
    //•	KeyboardType.Email：带 @ 和 .com 的键盘
    //•	KeyboardType.Password：隐藏字符键盘
    keyboardType: KeyboardType = KeyboardType.Text,
    onImeAction: () -> Unit = {},
    contentAlignment: Alignment = Alignment.CenterStart,
) {
    val rColor = RuTheme.colors
    val typo = RuTheme.typo
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val cfgSize = getInputSize(size)

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
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(onDone = { onImeAction() }),
        cursorBrush = SolidColor(if (isError) Color.Red else Color.Black),
        interactionSource = interactionSource,
        textStyle = RuTheme.typo.paragraphS.copy(
            color = cfgColor.textColor,
            textAlign = when (contentAlignment) {
                Alignment.Center -> TextAlign.Center
                Alignment.CenterEnd -> TextAlign.End
                else -> TextAlign.Start
            }
        ),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(cfgColor.bgColor, shape)
                    .border(1.dp, cfgColor.borderColor, shape)
                    .height(cfgSize.h)
                    .padding(
                        start = if (leftAddon == null) cfgSize.pl else 0.dp,
                        end = if (rightAddon == null) cfgSize.pr else 0.dp,
                    )
            ) {
                if (leftAddon != null) {
                    Box(
                        Modifier
                            .height(cfgSize.h)
                            .rightBorder(1.dp, rColor.strokeSoft),
                        contentAlignment = Alignment.Center
                    ) { leftAddon(disabled, cfgColor.textColor) }
                }

                if (leftIcon != null) RenderIcon(leftIcon, cfgColor)
                if (leftIcon != null && leftAddon == null) Spacer(Modifier.width(cfgSize.gap))

                Box(
                    modifier = Modifier
                        .let { if (leftAddon != null) it.padding(start = cfgSize.pl) else it }
                        .let { if (rightAddon != null) it.padding(end = cfgSize.pr) else it }
                        .weight(1f),
                    contentAlignment = contentAlignment
                ) {
                    if (value.isEmpty()) {
                        Text(placeholder, style = typo.paragraphS, color = cfgColor.placeholder)
                    }
                    innerTextField()
                }

                if (rightIcon != null && rightAddon == null) Spacer(Modifier.width(cfgSize.gap))
                if (rightIcon != null) RenderIcon(rightIcon, cfgColor)

                if (rightAddon != null) {
                    Box(
                        Modifier
                            .height(cfgSize.h)
                            .leftBorder(1.dp, rColor.strokeSoft),
                        contentAlignment = Alignment.Center
                    ) { rightAddon(disabled, cfgColor.textColor) }
                }
            }
        }
    )
}

@Composable
fun RenderIcon(icon: IconType, cfgColor: InputColorModel) {
    when (icon) {
        is IconType.Slot -> icon.slot()
        is IconType.Path -> SvgIcon(icon.path, size = 20, tint = cfgColor.iconColor)
    }
}