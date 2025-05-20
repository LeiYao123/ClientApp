package com.quick.app.components.weekDayPicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quick.app.ui.theme.RuTheme
import com.quick.app.util.formatDate
import com.quick.app.util.parseDate
import java.util.Calendar

/**
 * 日期选择器示例
 */
@Composable
fun WeekDayPickerDemoRoute() {
    // 获取当前日期
    val today = Calendar.getInstance()

    // 创建前一天和后一天的日期
    val yesterday = Calendar.getInstance()
    yesterday.add(Calendar.DAY_OF_MONTH, -1)

    val tomorrow = Calendar.getInstance()
    tomorrow.add(Calendar.DAY_OF_MONTH, 1)

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "日期选择器示例",
            style = RuTheme.typo.titleH5,
            color = RuTheme.colors.textStrong
        )

        // 展示三种不同状态的日期选择器
        Text(
            text = "不同状态的日期选择器",
            style = RuTheme.typo.labelM,
            color = RuTheme.colors.textSub
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 默认状态
            DatePicker(
                date = yesterday.time,
                isSelected = false
            )

            // 选中状态
            DatePicker(
                date = today.time,
                isSelected = true
            )

            // 普通状态（原高亮状态）
            DatePicker(
                date = tomorrow.time,
                isSelected = false
            )
        }

        // 使用示例：传入日期字符串
        Text(
            text = "使用示例：传入日期字符串",
            style = RuTheme.typo.labelM,
            color = RuTheme.colors.textSub,
            modifier = Modifier.padding(top = 16.dp)
        )

        // 解析日期字符串并显示
        val dateString = "2025-05-20"
        val parsedDate = parseDate(dateString, "yyyy-MM-dd")

        if (parsedDate != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                DatePicker(
                    date = parsedDate,
                    isSelected = true
                )
            }

            // 显示日期信息
            val calendar = Calendar.getInstance()
            calendar.time = parsedDate
            val weekDayShortLabel = getWeekDayFromDate(parsedDate)

            Text(
                text = "日期：${
                    formatDate(
                        parsedDate,
                        "yyyy-MM-dd"
                    )
                }\n星期：${weekDayShortLabel}\n日：${calendar.get(Calendar.DAY_OF_MONTH)}",
                style = RuTheme.typo.paragraphM,
                color = RuTheme.colors.textSub,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
