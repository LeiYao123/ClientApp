package com.quick.app.components.toast

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

enum class ToastSize { XS, S, L }
enum class ToastStatus { ERROR, WARNING, SUCCESS, INFO, FEATURE }
enum class ToastStyle { Filled, Light, Lighter, Stroke }
enum class ToastPosition { TopCenter, TopRight, BottomRight }

const val DURATION = 3000L

data class ToastModel(
    val message: String,
    val title: String? = null, // 仅当 size 为 L 时存在
    val status: ToastStatus = ToastStatus.INFO,
    val style: ToastStyle = ToastStyle.Filled,
    val size: ToastSize = ToastSize.S,
    val duration: Long = DURATION,
    val position: ToastPosition = ToastPosition.TopCenter,
    val showDismiss: Boolean = false,
    val id: String = UUID.randomUUID().toString(), // 唯一标识
    val isDismissing: Boolean = false, // 隐藏字段用于控制消失动画
)

class ToastManager(application: Application) : AndroidViewModel(application) {
    private val _toasts = MutableStateFlow<List<ToastModel>>(emptyList())
    val toasts = _toasts.asStateFlow()

    fun show(toast: ToastModel) {
        _toasts.update { it + toast }

        // 这里使用 AndroidViewModel 的 主要原因是有 延时自动关闭的需求，需要使用协程，正常 drawer 类的直接使用单例即可
        // 自动消失逻辑在 animatedToast 里面执行，因为要执行 动画消失效果
        viewModelScope.launch {
            delay(toast.duration) // 等待指定时间后消失
            markDismissing(toast.id) // 消失逻辑
        }
    }

    private fun markDismissing(id: String) {
        _toasts.update { list -> list.map { if (it.id == id) it.copy(isDismissing = true) else it } }
    }

    fun dismiss(id: String) {
        _toasts.update { it.filterNot { toast -> toast.id == id } }
    }
}

object Toast {
    private var vm: ToastManager? = null

    // 初始化
    fun init(viewModel: ToastManager) {
        vm = viewModel
    }

    // 显示 toast
    fun show(
        message: String,
        status: ToastStatus = ToastStatus.INFO,
        style: ToastStyle = ToastStyle.Filled,
        size: ToastSize = ToastSize.S,
        title: String? = null,
        duration: Long = DURATION,
        position: ToastPosition = ToastPosition.TopCenter,
        showDismiss: Boolean = false,
    ) {
        vm?.show(
            ToastModel(
                title = title,
                message = message,
                status = status,
                style = style,
                size = size,
                duration = duration,
                position = position,
                showDismiss = showDismiss,
            )
        )
    }

    // 错误专用提示
    fun showError(
        message: String,
        title: String = "Server error",
        duration: Long = DURATION,
        position: ToastPosition = ToastPosition.TopCenter,
        showDismiss: Boolean = false,
    ) {
        show(
            message = message,
            status = ToastStatus.ERROR,
            style = ToastStyle.Lighter,
            size = ToastSize.L,
            title = title,
            duration = duration,
            position = position,
            showDismiss = showDismiss,
        )
    }
}