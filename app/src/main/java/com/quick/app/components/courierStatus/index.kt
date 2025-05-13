package com.quick.app.components.courierStatus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.quick.app.components.spacer.RuSpacer
import com.quick.app.components.svgicon.SvgIcon
import com.quick.app.components.svgicon.SvgPath
import com.quick.app.ui.theme.RuTheme


enum class CourierState {
    Delivered,
    ArrivedAtConsumer,
    EnrouteToConsumer,
    PickedUp,
    ArrivedAtStore,
    EnrouteToPickup,
    DriverAssigned,
    Dispatching
}

enum class CourierStatusSize { S, M }

@Composable
fun CourierStatus(
    modifier: Modifier = Modifier,
    state: CourierState = CourierState.Delivered,
    size: CourierStatusSize = CourierStatusSize.M,
    error: Boolean = false,
) {
    // 总共十个元素，用 boolean 代表 active
    val statusList = getActiveList(state)

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Column(
            modifier = Modifier.width(80.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CourierIcon(SvgPath.select_box_circle_line, statusList[0], size, error)
                RuSpacer(8)
                ProgressLine(statusList[1], error)
                ProgressLine(statusList[2], error, false)
            }
            Text(
                "Courier StatusStatus",
                modifier = Modifier.fillMaxWidth(),
                style = RuTheme.typo.labelXS,
                color = if (statusList[0]) RuTheme.colors.textSub else RuTheme.colors.strokeSoft,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CourierIcon(SvgPath.store_2_line, statusList[3], size, error)
                RuSpacer(8)
                ProgressLine(statusList[4], error)
                ProgressLine(statusList[5], error, false)
                RuSpacer(8)
                CourierIcon(SvgPath.car_line, statusList[6], size, error)
                RuSpacer(8)
                ProgressLine(statusList[7], error)
                ProgressLine(statusList[8], error, false)
                RuSpacer(8)
                CourierIcon(SvgPath.home_4_line, statusList[9], size, error)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "6:12 PM",
                    style = RuTheme.typo.labelXS,
                    color = if (statusList[3]) RuTheme.colors.textSub else RuTheme.colors.strokeSoft
                )
                Text(
                    "ETA 6:21 PM",
                    style = RuTheme.typo.labelXS,
                    color = if (statusList[9]) RuTheme.colors.textSub else RuTheme.colors.strokeSoft
                )
            }
        }
    }
}

@Composable
fun RowScope.ProgressLine(
    isActive: Boolean = false,
    error: Boolean = false,
    isLeftShape: Boolean = true,
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .height(3.dp)
            .background(
                color = if (isActive) if (error) RuTheme.colors.errorBase else RuTheme.colors.textSub else RuTheme.colors.strokeSoft,
                shape = RoundedCornerShape(
                    topStart = if (isLeftShape) 50.dp else 0.dp,
                    bottomStart = if (isLeftShape) 50.dp else 0.dp,
                    topEnd = if (isLeftShape) 0.dp else 50.dp,
                    bottomEnd = if (isLeftShape) 0.dp else 50.dp
                )
            )
    )
}

@Composable
fun CourierIcon(
    path: String,
    isActive: Boolean = false,
    size: CourierStatusSize = CourierStatusSize.M,
    error: Boolean = false,
) {
    SvgIcon(
        path,
        size = if (size == CourierStatusSize.M) 24 else 16,
        tint = if (isActive) if (error) RuTheme.colors.errorBase else RuTheme.colors.textSub else RuTheme.colors.strokeSoft
    )
}

fun getActiveList(state: CourierState): List<Boolean> {
    return when (state) {
        CourierState.Delivered -> listOf(true, true, true, true, true, true, true, true, true, true)
        CourierState.ArrivedAtConsumer -> listOf(
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            false
        )

        CourierState.EnrouteToConsumer -> listOf(
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            false,
            false
        )

        CourierState.PickedUp -> listOf(
            true,
            true,
            true,
            true,
            true,
            false,
            false,
            false,
            false,
            false
        )

        CourierState.ArrivedAtStore -> listOf(
            true,
            true,
            true,
            true,
            false,
            false,
            false,
            false,
            false,
            false
        )

        CourierState.EnrouteToPickup -> listOf(
            true,
            true,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false
        )

        CourierState.DriverAssigned -> listOf(
            true,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false
        )

        CourierState.Dispatching -> listOf(
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false
        )
    }
}