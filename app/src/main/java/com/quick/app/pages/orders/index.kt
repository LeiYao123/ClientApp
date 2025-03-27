package com.quick.app.pages.orders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quick.app.components.Loading
import com.quick.app.components.RuPullToRefresh
import com.quick.app.components.RuTopAppBar
import com.quick.app.extension.shortToast
import com.quick.app.models.Order
import com.quick.app.pages.orders.comps.OrderItem
import com.quick.app.pages.orders.comps.OrderTab
import kotlinx.coroutines.delay

@Composable
fun OrdersRoute() {
    OrdersScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(vm: OrdersViewModel = viewModel()) {
    Scaffold(topBar = { RuTopAppBar("订单列表", toBack = true) }) { pv ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(pv)
        ) {
            OrderTab(
                selectedIndex = vm.currIndex.intValue,
                tabChanged = vm::handleSwitchType,
            )
            when (val ui = vm.uiState.value) {
                LoadingState.Loading -> Loading()
                is LoadingState.Error -> ui.data.shortToast()
                is LoadingState.Error401 -> ui.data.shortToast()
                LoadingState.NoMoreData -> TODO()
                LoadingState.Success -> {
                    RuPullToRefresh(
                        onRefreshApi = {
                            delay(2000)
                            "刷新成功".shortToast()
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        OrderList(vm.list.value)
                    }
                }
            }
        }
    }
}

@Composable
private fun OrderList(list: List<Order>) {
    val listState = rememberLazyListState()
    var isLoadMore by remember { mutableStateOf(false) }

    // 检测是否到达底部，如果是，触发加载更多
    val isAtBottom = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
        }
    }

    // 当到达底部时触发加载更多
    LaunchedEffect(isAtBottom.value) {
        if (isAtBottom.value) {
            isLoadMore = true
            delay(5000)
            isLoadMore = false
        }
    }
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 12.dp)
    ) {
        items(list.size) { OrderItem(data = list[it]) }
        // 加载更多的提示
        if (isLoadMore) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(24.dp)
                    )
                }
            }
        }
    }
}