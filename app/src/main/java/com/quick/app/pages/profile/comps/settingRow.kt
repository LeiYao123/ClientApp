package com.quick.app.pages.profile.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun SettingRow(
    modifier: Modifier = Modifier,
    showLine: Boolean = false,
    // 加了 RowScope.()  内部 content 才能使用 row 特有的 modifier
    content: @Composable RowScope.() -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) { content() }
    if (showLine)
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color = Color(0xffeeeeee))
        )
}

@Composable
fun SettingTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier,
    )
}


@Composable
fun SettingInput(
    value: String,
    onValueChanged: (String) -> Unit,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChanged,
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            textAlign = TextAlign.Right,
            color = MaterialTheme.colorScheme.outline,
        ),
    )
}
