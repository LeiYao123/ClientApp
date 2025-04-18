package com.quick.app.components.drawer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DrawerDemo() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterEnd,
    ) {
        Column {
            Button(
                onClick = {
                    RuDrawer.open(
                        DrawerEntry(direction = DrawerDirection.Left, id = "left") {
                            Text("左边的 drawer")
                        }
                    )
                }
            ) {
                Text("打开左边 drawer")
            }

            Button(
                onClick = {
                    RuDrawer.open(
                        DrawerEntry(direction = DrawerDirection.Right, id = "right") {
                            Text("右边的 drawer")
                            Button(onClick = {
                                RuDrawer.close("right")
                            }) {
                                Text("关闭右边的 drawer")
                            }

                        }
                    )
                }
            ) {
                Text("打开右边 drawer")
            }
        }
    }
}
