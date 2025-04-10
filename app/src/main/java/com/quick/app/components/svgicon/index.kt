package com.quick.app.components.svgicon

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun SvgIcon(
    path: String, // 相对于 assets 的路径
    size: Int = 20,
    tint: Color = Color.Unspecified,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = "file:///android_asset/$path",
        contentDescription = path,
        modifier = modifier
            .size(size.dp)
            .then(modifier), // 根据需求调整大小
        colorFilter = if (tint != Color.Unspecified) {
            ColorFilter.tint(tint)
        } else null
    )
}
