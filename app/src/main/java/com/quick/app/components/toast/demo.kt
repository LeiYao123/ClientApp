package com.quick.app.components.toast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quick.app.components.checkbox.BoxType
import com.quick.app.components.checkbox.RuCheckBox
import com.quick.app.components.spacer.RuSpacer

@Composable
fun ToastDemo() {
    val currStyle = remember { mutableStateOf(ToastStyle.Filled) }
    val currSize = remember { mutableStateOf(ToastSize.S) }
    val currPos = remember { mutableStateOf(ToastPosition.TopCenter) }

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
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
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Button(onClick = {
                Toast.show(
                    "Server error",
                    status = ToastStatus.ERROR,
                    style = currStyle.value,
                    size = currSize.value,
                    position = currPos.value,
                    showDismiss = true
                )
            }) { Text("Toast Error demo") }
            Button(onClick = {
                Toast.show(
                    message = "Server error",
                    status = ToastStatus.SUCCESS,
                    style = currStyle.value,
                    size = currSize.value, position = currPos.value,

                    )
            }) { Text("Toast Success demo") }
            Button(onClick = {
                Toast.show(
                    message = "Server error",
                    status = ToastStatus.WARNING,
                    style = currStyle.value,
                    size = currSize.value, position = currPos.value,

                    )
            }) { Text("Toast Warning demo") }
            Button(onClick = {
                Toast.show(
                    message = "Server error",
                    status = ToastStatus.INFO,
                    style = currStyle.value,
                    size = currSize.value, position = currPos.value,
                    showDismiss = true
                )
            }) { Text("Toast Info demo") }
            Button(onClick = {
                Toast.show(
                    message = "Server error",
                    status = ToastStatus.FEATURE,
                    style = currStyle.value,
                    size = currSize.value, position = currPos.value,

                    showDismiss = true
                )
            }) { Text("Toast Feature demo") }

            Button(onClick = { Toast.showError(desc = "Insert the alert description here. It would look better as two lines of text.") }) {
                Text("Error Toast ")
            }
        }

        RuSpacer(h = 20)

        Alert(
            "Insert your alert title here!",
            showDismiss = true,
            modifier = Modifier.width(390.dp)
        )
        RuSpacer(h = 12)
        Alert(
            "Insert your alert title here!",
            desc = "Insert the alert description here. It would look better as two lines of text.",
            size = ToastSize.L,
            showDismiss = true,
            modifier = Modifier.width(390.dp)
        )
        RuSpacer(h = 20)
        Alert(
            "Insert your alert title here!",
            desc = "Insert the alert description here. It would look better as two lines of text.",
            size = ToastSize.L,
            status = ToastStatus.SUCCESS,
            style = ToastStyle.Lighter,
            showDismiss = true,
            modifier = Modifier.width(390.dp)
        )
    }
}