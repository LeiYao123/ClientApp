package com.quick.app.components.drawer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

enum class DrawerDirection { Left, Right }

data class DrawerEntry(
    val id: String = UUID.randomUUID().toString(),
    val direction: DrawerDirection = DrawerDirection.Left,
    val width: Dp = 300.dp,
    val dismissOnClickOutside: Boolean = true,
    val requestDelete: Boolean = false,
    val content: @Composable ColumnScope.() -> Unit,
)


object RuDrawer {
    private val _drawers = MutableStateFlow<List<DrawerEntry>>(emptyList())
    val drawers: StateFlow<List<DrawerEntry>> = _drawers.asStateFlow()

    fun open(entry: DrawerEntry): String {
        if (_drawers.value.any { it.id == entry.id }) {
            return entry.id // 已存在则忽略
        }
        _drawers.value += entry
        return entry.id
    }

    // 外界直接调用关闭，播放离开动画，然后执行真正清除
    fun close(id: String) {
        _drawers.value = _drawers.value.map {
            if (it.id == id) it.copy(requestDelete = true)
            else it
        }
    }

    // 真正清除
    fun delete(id: String) {
        _drawers.value = _drawers.value.filterNot { it.id == id }
    }
}

@Composable
fun DrawerHost() {
    val drawers by RuDrawer.drawers.collectAsState()

    if (drawers.isNotEmpty()) {
        // 关闭最上层一个 Drawer
        BackHandler { RuDrawer.close(drawers.last().id) }
    }

    drawers.forEach { entry ->
        key(entry.id) {
            DrawerBody(entry) { RuDrawer.delete(it) }
        }
    }
}