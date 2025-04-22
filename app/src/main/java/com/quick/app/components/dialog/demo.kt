package com.quick.app.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.quick.app.components.button.RuButtonStyle
import com.quick.app.components.button.RuButtonType
import com.quick.app.components.spacer.Expanded
import com.quick.app.components.spacer.RuSpacer

@Composable
fun RuDialogDemo() {
    Column {
        Button(onClick = { RuDialog.open(DialogEntry(id = "dialog_001") { DialogHeaderDemo() }) }) {
            Text("Show Dialog")
        }

        Button(onClick = { RuDialog.open(DialogEntry(id = "dialog_002") { DialogFooterDemo() }) }) {
            Text("Show Dialog")
        }
    }
}


@Composable
fun DialogHeaderDemo() {
    RuDialogHeader(
        title = "Insert title here",
        showDivider = true,
        icon = {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.HistoryEdu,
                contentDescription = null
            )
        },
        onClose = { RuDialog.close("dialog_001") }
    )
    RuSpacer(h = 20)
    RuDialogHeader(
        title = "Insert title here",
        desc = "Please insert modal description here.",
        showDivider = true,
        icon = {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.HistoryEdu,
                contentDescription = null
            )
        },
        onClose = { RuDialog.close("dialog_001") }
    )

    RuSpacer(h = 200)
}


@Composable
fun DialogFooterDemo() {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RuSpacer(h = 18)
        RuDialogFooter {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                RuButton(
                    "Cancel",
                    modifier = Modifier.weight(1f),
                    style = RuButtonStyle.STROKE,
                )
                RuButton(
                    "Continue",
                    modifier = Modifier.weight(1f),
                    style = RuButtonStyle.FILLED,
                    type = RuButtonType.PRIMARY,
                )
            }
        }

        RuDialogFooter {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Expanded()
                RuButton(
                    "Cancel",
                    modifier = Modifier.width(100.dp),
                    style = RuButtonStyle.STROKE,
                )
                RuButton(
                    "Continue",
                    modifier = Modifier.width(100.dp),
                    style = RuButtonStyle.FILLED,
                    type = RuButtonType.PRIMARY
                )
            }
        }
    }
}