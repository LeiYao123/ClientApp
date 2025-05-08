package com.quick.app.components.orderStatus

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.quick.app.components.bdage.RuBadge
import com.quick.app.components.bdage.RuBadgeStyle
import com.quick.app.components.bdage.RuBadgeType
import com.quick.app.components.svgicon.SvgPath


enum class OrderStatusEnum {
    NEW,
    PREPPING_PART,
    PREPPING,
    READY_SERVE,
    OUT_FOR_DELIVERY,
    COMPLETED,
    CANCELED,
}

@Composable
fun OrderStatus(status: OrderStatusEnum, modifier: Modifier = Modifier, text: String? = null) {
    Box(modifier = modifier) {
        when (status) {
            OrderStatusEnum.NEW -> RuBadge(text ?: "New", type = RuBadgeType.RED)
            OrderStatusEnum.PREPPING_PART -> RuBadge(
                text ?: "Prepping (8/12)",
                type = RuBadgeType.YELLOW,
                style = RuBadgeStyle.LIGHT,
                iconLeft = SvgPath.progress_2_line,
            )

            OrderStatusEnum.PREPPING -> RuBadge(
                text ?: "Prepping",
                type = RuBadgeType.ORANGE,
                style = RuBadgeStyle.LIGHT,
                iconLeft = SvgPath.progress_2_line,
            )

            OrderStatusEnum.READY_SERVE -> RuBadge(
                text ?: "Ready to serve",
                type = RuBadgeType.SKY,
                style = RuBadgeStyle.LIGHT,
                iconLeft = SvgPath.progress_4_line,
            )

            OrderStatusEnum.OUT_FOR_DELIVERY -> RuBadge(
                text ?: "Out for delivery",
                type = RuBadgeType.TEAL,
                style = RuBadgeStyle.LIGHT,
                iconLeft = SvgPath.progress_6_line,
            )

            OrderStatusEnum.COMPLETED -> RuBadge(
                text ?: "Completed",
                type = RuBadgeType.GREEN,
                style = RuBadgeStyle.LIGHT,
                iconLeft = SvgPath.check_line,
            )

            OrderStatusEnum.CANCELED -> RuBadge(
                text ?: "Canceled",
                type = RuBadgeType.RED,
                style = RuBadgeStyle.LIGHT,
                iconLeft = SvgPath.close_line,
            )
        }
    }
}