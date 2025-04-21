package com.quick.app.components.drawer

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quick.app.components.button.RuButton
import com.quick.app.components.button.RuButtonSize
import com.quick.app.components.button.RuButtonStyle
import com.quick.app.components.button.RuButtonType
import com.quick.app.components.spacer.Expanded
import com.quick.app.components.spacer.RuSpacer

@Composable
fun DrawerDemo() {
    var num by remember { mutableIntStateOf(0) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Button(onClick = {
                RuDrawer.open(DrawerEntry(id = "left_drawer_header") {
                    RenderDrawerHeader(
                        num,
                        onAdd = { num++ })
                })
            }) {
                Text("打开左边 drawer header")
            }

            Button(onClick = {
                RuDrawer.open(DrawerEntry(id = "left_drawer_footer") {
                    RenderDrawerFooter(
                        num,
                        onAdd = { num++ })
                })
            }) {
                Text("打开左边 drawer footer")
            }
            Text("num 文本， $num")
            Button(onClick = { num++; }) { Text("自增按钮") }
        }
    }
}


@Composable
fun RenderDrawerHeader(num: Int, onAdd: () -> Unit) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RuSpacer(h = 8)
        RuDrawerHeader(title = "Insert title here $num", showBack = false)
        Button(onClick = { onAdd() }) { Text("自增按钮") }
        RuDrawerHeader(
            title = "Insert title here",
            showBack = false,
            icon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.HistoryEdu,
                    contentDescription = null
                )
            },
            onClose = {
                Log.d("Drawer", "header onClose")
                RuDrawer.close("left_drawer_header")
            }
        )
        RuDrawerHeader(
            showBack = false,
            title = "Insert title here",
            desc = "Please insert drawer description here."
        )
        RuDrawerHeader(
            showBack = false,
            title = "Insert title here",
            icon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.HistoryEdu,
                    contentDescription = null
                )
            },
            desc = "Please insert drawer description here."
        )

        RuSpacer(h = 24)
        RuDrawerHeader(title = "Insert title here")
        RuDrawerHeader(
            title = "Insert title here",
            icon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.HistoryEdu,
                    contentDescription = null
                )
            }
        )
        RuDrawerHeader(
            title = "Insert title here",
            desc = "Please insert drawer description here."
        )
        RuDrawerHeader(
            onClose = { RuDrawer.close("left_drawer_header") },
            title = "Insert title here",
            icon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.HistoryEdu,
                    contentDescription = null
                )
            },
            desc = "Please insert drawer description here."
        )
    }
}


@Composable
fun RenderDrawerFooter(num: Int, onAdd: () -> Unit) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RuSpacer(h = 18)
        RuDrawerFooter {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                RuButton(
                    "Cancel",
                    modifier = Modifier.weight(1f),
                    style = RuButtonStyle.STROKE,
                    size = RuButtonSize.XL,
                )
                RuButton(
                    "Continue $num",
                    modifier = Modifier.weight(1f),
                    size = RuButtonSize.XL,
                    style = RuButtonStyle.FILLED,
                    type = RuButtonType.PRIMARY,
                    onClick = { onAdd() }
                )
            }
        }

        RuDrawerFooter {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Expanded()
                RuButton(
                    "Cancel",
                    modifier = Modifier.width(100.dp),
                    style = RuButtonStyle.STROKE,
                    size = RuButtonSize.XL,
                )
                RuButton(
                    "Continue",
                    modifier = Modifier.width(100.dp),
                    size = RuButtonSize.XL,
                    style = RuButtonStyle.FILLED,
                    type = RuButtonType.PRIMARY
                )
            }
        }
    }
}