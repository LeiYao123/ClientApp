package com.quick.app.components.menuCard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MenuCardDemoRoute() {
    Column(modifier = Modifier.width(300.dp)) {
        val checked = remember { mutableStateOf(false) }
        MenuCard(
            checked = checked.value,
            onCheckedChange = { checked.value = it },
            optionName = "(Modifier option)",
            title = "Red Curry",
            price = "\$8.00+",
            image = null,
            time = "Unavailable until 10/20/2024"
        )
    }
}