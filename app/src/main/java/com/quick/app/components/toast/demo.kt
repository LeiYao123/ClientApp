package com.quick.app.components.toast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.quick.app.components.checkbox.BoxType
import com.quick.app.components.checkbox.RuCheckBox

@Composable
fun ToastDemo() {
    val currStyle = remember { mutableStateOf(ToastStyle.Filled) }
    val currSize = remember { mutableStateOf(ToastSize.S) }
    val currPos = remember { mutableStateOf(ToastPosition.TopCenter) }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ToastStyle.entries.forEach { style ->
                RuCheckBox(
                    type = BoxType.RADIO,
                    text = style.name,
                    checked = style == currStyle.value,
                    onChange = { currStyle.value = style }
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ToastSize.entries.forEach { size ->
                RuCheckBox(
                    type = BoxType.RADIO,
                    text = size.name,
                    checked = size == currSize.value,
                    onChange = { currSize.value = size }
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ToastPosition.entries.forEach { pos ->
                RuCheckBox(
                    type = BoxType.RADIO,
                    text = pos.name,
                    checked = pos == currPos.value,
                    onChange = { currPos.value = pos }
                )
            }
        }
        Button(onClick = {
            Toast.show(
                "message message message message message message message message message message message ",
                status = ToastStatus.ERROR,
                style = currStyle.value,
                size = currSize.value,
                title = "Server error",
                position = currPos.value,
                showDismiss = true
            )
        }) { Text("Toast Error demo") }
        Button(onClick = {
            Toast.show(
                "message ",
                status = ToastStatus.SUCCESS,
                style = currStyle.value,
                size = currSize.value, position = currPos.value,
                title = "Server error",
            )
        }) { Text("Toast Success demo") }
        Button(onClick = {
            Toast.show(
                "message ...",
                status = ToastStatus.WARNING,
                style = currStyle.value,
                size = currSize.value, position = currPos.value,
                title = "Server error",
            )
        }) { Text("Toast Warning demo") }
        Button(onClick = {
            Toast.show(
                "message ...",
                status = ToastStatus.INFO,
                style = currStyle.value,
                size = currSize.value, position = currPos.value,
                title = "Server error",
            )
        }) { Text("Toast Info demo") }
        Button(onClick = {
            Toast.show(
                "message ...",
                status = ToastStatus.FEATURE,
                style = currStyle.value,
                size = currSize.value, position = currPos.value,
                title = "Server error",
            )
        }) { Text("Toast Feature demo") }

        Button(onClick = { Toast.showError("Insert the alert description here. It would look better as two lines of text.") }) {
            Text("Error Toast ")
        }
    }
}