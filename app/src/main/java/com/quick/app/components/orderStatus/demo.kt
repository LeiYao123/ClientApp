package com.quick.app.components.orderStatus

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun OrderStatusDemoRoute() {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        OrderStatus(status = OrderStatusEnum.NEW)
        OrderStatus(status = OrderStatusEnum.PREPPING_PART)
        OrderStatus(status = OrderStatusEnum.PREPPING)
        OrderStatus(status = OrderStatusEnum.READY_SERVE)
        OrderStatus(status = OrderStatusEnum.OUT_FOR_DELIVERY)
        OrderStatus(status = OrderStatusEnum.COMPLETED)
        OrderStatus(status = OrderStatusEnum.CANCELED)
    }
}