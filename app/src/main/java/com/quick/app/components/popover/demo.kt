package com.quick.app.components.popover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.quick.app.components.button.RuButton
import com.quick.app.components.button.RuButtonSize
import com.quick.app.components.toast.Toast

@Composable
fun PopoverDemoRoute() {
    Column {
        Text("hello world")
        Text("hello world")
        Text("hello world")
        Spacer(Modifier.height(48.dp))
        RuPopover(
            modifier = Modifier.padding(top = 40.dp, start = 60.dp),
            trigger = { expanded, onClick ->
                RuButton(
                    text = "Click to open popover --> expanded: $expanded",
                    size = RuButtonSize.XL,
                    onClick = onClick,
                    modifier = Modifier.width(600.dp)
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(16.dp)
                ) {
                    Text("This is popover content")
                    Spacer(Modifier.height(8.dp))
                    Button(
                        onClick = { /* Do something */ }) {
                        Text("Action")
                    }
                }
            }
        )
        Text("hello world")
        Text("hello world")
        Text("hello world")
        Text("hello world")
        Text("hello world")
        Text("hello world")
        Text("hello world")
        Text("hello world")
        Text("hello world")
        Text("hello world")
        Text("hello world")
        RuPopover(
            direction = PopoverDirection.Top,
            contentWidth = 120.dp,
            contentHeight = 280.dp,
            modifier = Modifier.padding(top = 40.dp, start = 60.dp),
            trigger = { expanded, onClick ->
                RuButton(
                    text = "Click to open popover --> expanded: $expanded",
                    size = RuButtonSize.XL,
                    onClick = onClick,
                    modifier = Modifier.width(400.dp)
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .background(Color.LightGray)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text("This is popover content")
                    Spacer(Modifier.height(8.dp))
                    Button(
                        onClick = { Toast.show("hello world") }) {
                        Text("Action")
                    }
                    Text("hello world")
                    Text("hello world")
                    Text("hello world")
                    Text("hello world")
                    Text("hello world")
                    Text("hello world")
                    Text("hello world")
                    Text("hello world")
                    Text("hello world")
                    Text("hello world")
                    Text("hello world")
                    Text("hello world")
                    Text("hello world")
                    Text("hello world")
                    Text("hello world")
                    Text("hello world")
                    Text("hello world")
                    Text("hello world")
                }
            }
        )
        Text("hello world")
        Text("hello world")
        Text("hello world")
    }
}