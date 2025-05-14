package com.quick.app.components.slimeNumberPad

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.quick.app.components.button.RuButton
import com.quick.app.components.button.RuButtonStyle
import com.quick.app.components.button.RuButtonType
import com.quick.app.components.dialog.AppDialog1
import com.quick.app.components.dialog.RuDialog
import com.quick.app.components.dialog.RuDialogFooter
import com.quick.app.components.dialog.RuDialogHeader
import com.quick.app.extension.bottomBorder
import com.quick.app.ui.theme.RuTheme

@Composable
fun SlimeNumberPadDemoRoute() {
    val open = remember { mutableStateOf(true) }
    val showNumberPad = remember { mutableStateOf(false) }
    val currIndex = remember { mutableIntStateOf(0) }

    val currAmount = remember { mutableStateOf("100") }
    val newAmount = remember { mutableStateOf("100") }

    val width = animateDpAsState(
        targetValue = if (showNumberPad.value) 840.dp else 400.dp,
        label = ""
    )

    LaunchedEffect(currIndex.intValue) {
        showNumberPad.value = currIndex.intValue != 0
    }

    Button(onClick = { open.value = true }) {
        Text("Open")
    }
    Column {
        Text("Open")
        Text("Open")
        Text("Open")
        Text("Open")
        Text("Open")
        Text("Open")
        Text("Open")
        Text("Open")
        Text("Open")
        Text("Open")
        Text("Open")
    }
    AppDialog1(open = open.value, width = width.value, onClose = { open.value = false }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .width(400.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
            ) {
                RuDialogHeader(
                    title = "Adjust tip",
                    showDivider = true,
                    onClose = { RuDialog.close("dialog_001") })

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,

                        ) {
                        Text(
                            "Current amount",
                            style = RuTheme.typo.labelS,
                            color = RuTheme.colors.textSub
                        )
                        Box(
                            modifier = Modifier
                                .clickable { currIndex.intValue = 1 }
                                .fillMaxWidth()
                                .let {
                                    if (currIndex.intValue == 1) it.bottomBorder(
                                        1.dp,
                                        RuTheme.colors.primaryBase
                                    ) else it
                                }
                                .padding(vertical = 12.dp),

                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "$${currAmount.value}",
                                style = RuTheme.typo.titleH4,
                                color = RuTheme.colors.textStrong
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            "New amount amount",
                            style = RuTheme.typo.labelS,
                            color = RuTheme.colors.textSub
                        )
                        Box(
                            modifier = Modifier
                                .clickable { currIndex.intValue = 2 }
                                .fillMaxWidth()
                                .let {
                                    if (currIndex.intValue == 2) it.bottomBorder(
                                        1.dp,
                                        RuTheme.colors.primaryBase
                                    ) else it
                                }
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "$${newAmount.value}",
                                style = RuTheme.typo.titleH4,
                                color = RuTheme.colors.textStrong
                            )
                        }
                    }
                }
                RuDialogFooter(showDivider = true) {
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        RuButton(
                            "Cancel",
                            modifier = Modifier.weight(1f),
                            style = RuButtonStyle.STROKE,
                            onClick = {
                                showNumberPad.value = false
                                currIndex.intValue = 0
                            }
                        )
                        RuButton(
                            "Continue",
                            modifier = Modifier.weight(1f),
                            style = RuButtonStyle.FILLED,
                            type = RuButtonType.PRIMARY
                        )
                    }
                }
            }
            if (showNumberPad.value) SlimeNumberPad(
                onValueChange = {
                    if (currIndex.intValue == 1) {
                        currAmount.value += it.toString()
                    }
                    if (currIndex.intValue == 2) {
                        newAmount.value += it.toString()
                    }
                },
                onClear = {
                    if (currIndex.intValue == 1) {
                        currAmount.value = ""
                    }
                    if (currIndex.intValue == 2) {
                        newAmount.value = ""
                    }
                },
                onDelete = {
                    if (currIndex.intValue == 1) {
                        currAmount.value = currAmount.value.dropLast(1)
                    }
                    if (currIndex.intValue == 2) {
                        newAmount.value = newAmount.value.dropLast(1)
                    }
                }
            )
        }
    }
}

