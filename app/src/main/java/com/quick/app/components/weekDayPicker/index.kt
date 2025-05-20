package com.quick.app.components.weekDayPicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.quick.app.ui.theme.RuTheme
import java.util.Calendar
import java.util.Date

/**
 * 根据日期获取对应的星期几英文简称
 * @param date 日期
 * @return 星期几英文简称 (Mon, Tue, Wed, Thu, Fri, Sat, Sun)
 */
fun getWeekDayFromDate(date: Date): String {
    val calendar = Calendar.getInstance()
    calendar.time = date
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

    return when (dayOfWeek) {
        Calendar.MONDAY -> "Mon"
        Calendar.TUESDAY -> "Tue"
        Calendar.WEDNESDAY -> "Wed"
        Calendar.THURSDAY -> "Thu"
        Calendar.FRIDAY -> "Fri"
        Calendar.SATURDAY -> "Sat"
        Calendar.SUNDAY -> "Sun"
        else -> "Sun" // 默认返回Sunday
    }
}

// 移除DatePickerState枚举，使用布尔类型isSelected替代

/**
 * 日期选择器组件
 * @param date 日期
 * @param isSelected 是否选中
 * @param onClick 点击回调
 */
@Composable
fun DatePicker(
    date: Date,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
) {
    // 提取日期信息
    val calendar = Calendar.getInstance()
    calendar.time = date
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val weekDayShortLabel = getWeekDayFromDate(date)

    // 根据状态设置样式
    val backgroundColor = if (isSelected) RuTheme.colors.primaryBase else Color.Transparent
    val textColor = if (isSelected) RuTheme.colors.textWhite else RuTheme.colors.textStrong
    val weekDayTextColor = if (isSelected) RuTheme.colors.textWhite else RuTheme.colors.textSub

    // 日期选择器UI
    Box(
        modifier = Modifier
            .width(64.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .let {
                if (!isSelected) {
                    it.border(1.dp, RuTheme.colors.strokeSoft, RoundedCornerShape(8.dp))
                } else {
                    it
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = weekDayShortLabel,
                style = RuTheme.typo.labelS,
                color = weekDayTextColor,
                textAlign = TextAlign.Center
            )
            Text(
                text = day.toString(),
                style = RuTheme.typo.titleH5,
                color = textColor,
                textAlign = TextAlign.Center
            )
        }
    }
}