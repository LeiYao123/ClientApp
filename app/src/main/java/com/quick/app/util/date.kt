package com.quick.app.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * 日期格式化工具函数
 */
fun formatDate(date: Date, pattern: String): String {
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(date)
}

/**
 * 从字符串解析日期
 */
fun parseDate(dateString: String, pattern: String): Date? {
    return try {
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        formatter.parse(dateString)
    } catch (e: Exception) {
        null
    }
}