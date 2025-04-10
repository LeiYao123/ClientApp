package com.quick.app.components.radio

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> SelectableRadioGroup(
    options: List<T>,
    selectedOption: T? = null,
    onChange: (T) -> Unit,
    optionLabel: (T) -> String = { it.toString() },
    modifier: Modifier = Modifier,
) {
    var selected by remember { mutableStateOf(selectedOption) }

    Row(modifier = modifier) {
        options.forEach { option ->
            val isSelected = option == selected

            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        selected = option
                        onChange(option)
                    }
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = isSelected,
                    onClick = {
                        selected = option
                        onChange(option)
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = optionLabel(option))
            }
        }
    }
}