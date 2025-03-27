package com.quick.app.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Loading(isShow: Boolean = true) {
    if (!isShow) return
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp), // 圆圈大小
            color = Color.Red, // 圆圈颜色
            strokeWidth = 3.dp // 线条宽度
        )
    }
}

@Composable
fun InlineLoading() {
    CircularProgressIndicator(
        modifier = Modifier
            .padding(4.dp)
            .size(24.dp)
    )
}