package com.quick.app.components.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.quick.app.components.checkbox.BoxType
import com.quick.app.components.checkbox.RuCheckBox
import com.quick.app.components.spacer.RuSpacer
import com.quick.app.components.svgicon.SvgPath

@Composable
fun RuButtonDemo() {
    Scaffold { pd ->
        Surface(
            modifier = Modifier
                .padding(pd)
                .padding(16.dp),
            color = Color.Transparent
        ) {
            val selectedSize = remember { mutableStateOf(RuButtonSize.M) }

            var checkedEnabled by remember { mutableStateOf(true) }
            var checkedLoading by remember { mutableStateOf(false) }

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Row {
                    RuButtonSize.entries.forEach {
                        RuCheckBox(
                            type = BoxType.RADIO,
                            text = it.name,
                            checked = selectedSize.value == it,
                            onChange = { _ -> selectedSize.value = it }
                        )
                        RuSpacer(12)
                    }
                }
                RuCheckBox(
                    text = "enabled",
                    type = BoxType.TOGGLE,
                        checked = checkedEnabled,
                    onChange = { checkedEnabled = it }
                    )

                RuCheckBox(
                    text = "loading",
                    type = BoxType.TOGGLE,
                        checked = checkedLoading,
                    onChange = { checkedLoading = it }
                    )

                RuSpacer(h = 24)



                RuButtonType.entries.forEach { type ->
                    Row {
                        RuButtonStyle.entries.forEach { style ->
                            RuButton(
                                size = selectedSize.value,
                                enabled = checkedEnabled,
                                loading = checkedLoading,
                                type = type,
                                style = style,
                                iconLeft = SvgPath.arrow_left_s_line,
                                iconRight = SvgPath.arrow_right_s_line,
                                text = "Button"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            RuButton(
                                size = selectedSize.value,
                                enabled = checkedEnabled,
                                loading = checkedLoading,
                                type = type,
                                style = style,
                                iconLeft = SvgPath.arrow_left_s_line
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    RuCompactStyle.entries.forEach {
                        RuCompactButton(
                            size = RuCompactSize.L,
                            enabled = checkedEnabled,
                            style = it,
                            icon = SvgPath.close_line,
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                    }

                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    RuCompactStyle.entries.forEach {
                        RuCompactButton(
                            size = RuCompactSize.M,
                            enabled = checkedEnabled,
                            style = it,
                            icon = SvgPath.close_line,
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                    }

                }
            }
        }
    }
}



