package com.quick.app.components.timelineActivity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quick.app.components.spacer.RuSpacer

@Composable
fun TimelineActivityDemoRoute() {
    Column(modifier = Modifier.width(600.dp)) {
        TimelineActivity(
            list = listOf(
                TimelineModel(
                    title = "2023-01-01",
                    desc = "desc",
                    time = "2023-01-01",
                    action = "action"
                ),
                TimelineModel(
                    title = "2023-01-01",
                    desc = "desc",
                    time = "2023-01-01",
                    action = "action"
                ),

                )
        )
        RuSpacer(h = 48)
        TimelineActivity(
            isHorizontal = true, list = listOf(
                TimelineModel(
                    title = "2023-01-01",
                    desc = "desc",
                    time = "2023-01-01",
                    action = "action"
                ),
                TimelineModel(
                    title = "2023-01-01",
                    desc = "desc",
                    time = "2023-01-01",
                    action = "action"
                ),
            )
        )
    }
}

