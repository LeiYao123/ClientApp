package com.quick.app.components.dialog

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.UUID

data class DialogEntry(
    val id: String = UUID.randomUUID().toString(),
    val width: Dp = 400.dp,
    val dismissOnClickOutside: Boolean = true,
    val duration: Int = 300,
    var requestDelete: Boolean = false, // 外界调用删除，内部标记执行完动画再进行删除
    val content: @Composable () -> Unit,
)


object RuDialog {
    private var _dialogs = mutableStateListOf<DialogEntry>()
    val dialogs: List<DialogEntry> get() = _dialogs

    fun open(entry: DialogEntry) {
        _dialogs += entry
    }

    fun close(id: String) {
        // 修改 id 的 requestDelete 为 true
        val index = _dialogs.indexOfFirst { it.id == id }
        if (index != -1) {
            _dialogs[index] = _dialogs[index].copy(requestDelete = true)
        }
    }

    fun delete(id: String) {
        _dialogs.removeAll { it.id == id }
    }

}

@Composable
fun DialogHost() {
    val dialogs = RuDialog.dialogs

    if (dialogs.isNotEmpty()) {
        BackHandler { RuDialog.close(dialogs.last().id) }
    }

    dialogs.forEach { entry ->
        key(entry.id) {
            AppDialog(entry) { RuDialog.delete(it) }
        }
    }
}