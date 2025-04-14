package com.quick.app.components.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.quick.app.components.radio.SelectableRadioGroup

@Composable
fun RuButtonDemo() {
    Scaffold { pd ->
        Surface(
            modifier = Modifier
                .padding(pd)
                .padding(16.dp),
            color = Color.Transparent
        ) {
            val sizeOpts = RuButtonSize.entries.map { it }
            var selectedSize by remember { mutableStateOf<RuButtonSize?>(null) }
            var checkedEnabled by remember { mutableStateOf(true) }
            var checkedLoading by remember { mutableStateOf(false) }

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Row {
                    Text("size: ")
                    SelectableRadioGroup(
                        options = sizeOpts,
                        selectedOption = selectedSize,
                        onChange = { selectedSize = it }
                    )
                }
                Row {
                    Text("enabled: ")
                    Switch(
                        checked = checkedEnabled,
                        onCheckedChange = { checkedEnabled = it }
                    )
                }
                Row {
                    Text("loading: ")
                    Switch(
                        checked = checkedLoading,
                        onCheckedChange = { checkedLoading = it }
                    )
                }



                RuButtonType.entries.forEach { type ->
                    Row {
                        RuButtonStyle.entries.forEach { style ->
                            RuButton(
                                size = selectedSize ?: RuButtonSize.M,
                                enabled = checkedEnabled,
                                loading = checkedLoading,
                                type = type,
                                style = style,
                                iconLeft = Icons.AutoMirrored.Filled.ArrowBack,
                                iconRight = Icons.AutoMirrored.Filled.ArrowForward,
                                text = "Button"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            RuButton(
                                size = selectedSize ?: RuButtonSize.M,
                                enabled = checkedEnabled,
                                loading = checkedLoading,
                                type = type,
                                style = style,
                                iconLeft = Icons.AutoMirrored.Filled.ArrowBack
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
                            icon = Icons.Default.Close,
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
                            icon = Icons.Default.Close,
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                    }

                }
            }
        }
    }
}



