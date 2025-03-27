package com.quick.app.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RuPullToRefresh(
    modifier: Modifier = Modifier,
    onRefreshApi: suspend () -> Unit,
    content: @Composable () -> Unit,
) {
    val refreshState = rememberPullToRefreshState()
    val isRefreshing = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val onRefresh: () -> Unit = {
        isRefreshing.value = true
        coroutineScope.launch {
            try {
                onRefreshApi()
            } finally {
                isRefreshing.value = false
            }
        }
    }
    PullToRefreshBox(
        modifier = modifier,
        isRefreshing = isRefreshing.value,
        state = refreshState,
        onRefresh = onRefresh,
    ) {
        content()
    }
}