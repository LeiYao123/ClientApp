package com.quick.app.components.courierStatus

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quick.app.components.spacer.RuSpacer

@Composable
fun CourierStatusDemoRoute() {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        CourierState.entries.forEach {
            Row {
                CourierStatus(state = it, modifier = Modifier.width(440.dp))
                RuSpacer(24)
                CourierStatus(
                    state = it,
                    modifier = Modifier.width(440.dp),
                    size = CourierStatusSize.S,
                    error = true
                )
            }
        }
    }
}