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
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Button(onClick = { RuDrawer.open(DrawerEntry(id = "left_drawer_header") { RenderDrawerHeader() }) }) {
                Text("打开左边 drawer header")
            }

            Button(onClick = { RuDrawer.open(DrawerEntry(id = "left_drawer_footer") { RenderDrawerFooter() }) }) {
                Text("打开左边 drawer footer")
            }
        }
    }
}


@Composable
fun RenderDrawerHeader() {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RuSpacer(h = 8)
        RuDrawerHeader(title = "Insert title here", showBack = false)
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
fun RenderDrawerFooter() {
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
                    "Continue",
                    modifier = Modifier.weight(1f),
                    size = RuButtonSize.XL,
                    style = RuButtonStyle.FILLED,
                    type = RuButtonType.PRIMARY
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