package com.quick.app.components.wheel

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.quick.app.ui.theme.RuTheme

@Composable
fun WheelDemoRoute() {
    val state = rememberFWheelPickerState(3)
// Observe currentIndex.
    LaunchedEffect(state) {
        snapshotFlow { state.currentIndex }
            .collect {
                Log.i("wheel", "currentIndex ${state.currentIndex}")
            }
    }

// Observe currentIndexSnapshot.
    LaunchedEffect(state) {
        snapshotFlow { state.currentIndexSnapshot }
            .collect {
//                Log.i("wheel", "currentIndexSnapshot ${state.currentIndexSnapshot}")
            }
    }


    Box(
        modifier = Modifier.width(400.dp),
    ) {
        FVerticalWheelPicker(
            state = state,
            // Specified item count.
            itemHeight = 60.dp,
            unfocusedCount = 1,
            count = 50,
            focus = {
                // Custom divider.
                FWheelPickerFocusVertical(
                    dividerColor = RuTheme.colors.strokeSoft,
                    dividerSize = 1.dp
                )
            },

            ) { index ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 80.dp)
                    .border(width = 1.dp, color = Color.Red),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("${25 * index} min", style = RuTheme.typo.labelM)
                Row {
                    Text(
                        "Ready by 6:15 PM (+$index minutes)",
                        style = RuTheme.typo.paragraphXS,
                        color = RuTheme.colors.textSub
                    )
                    Text(
                        " (+$index minutes)",
                        style = RuTheme.typo.paragraphXS,
                        color = RuTheme.colors.warningBase
                    )
                }
            }
        }
        Text(
            "Prep time",
            style = RuTheme.typo.paragraphS,
            color = RuTheme.colors.textSub,
            modifier = Modifier.align(Alignment.CenterStart)
        )
    }
}