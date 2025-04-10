package com.quick.app.components.radio

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RadioGroupExample() {
    val options = listOf("苹果", "香蕉", "橘子")
    var selected by remember { mutableStateOf<String?>(null) }

    Row(Modifier.padding(16.dp)) {
        Text("请选择一个水果：", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        SelectableRadioGroup(

            options = options,
            selectedOption = selected,
            onChange = { selected = it }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("你选择了：${selected ?: "未选择"}")
    }
}