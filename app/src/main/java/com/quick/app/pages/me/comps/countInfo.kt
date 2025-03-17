package com.quick.app.pages.me.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CountInfo(
    modifier: Modifier = Modifier,
) {
    val list = listOf(
        mapOf("num" to "10", "text" to "我的收藏"),
        mapOf("num" to "20", "text" to "我的浏览记录"),
        mapOf("num" to "30", "text" to "我的下载"),
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier,
        ) {
            list.forEachIndexed { index, it ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "${it["num"]}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "${it["text"]}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline,
                    )
                }
                if (index != list.size - 1) {
                    Spacer(
                        modifier = Modifier
                            .height(32.dp)
                            .width(2.dp)
                            .background(Color(0xFFf5f5f5))
                    )
                }
            }
        }
    }
}


