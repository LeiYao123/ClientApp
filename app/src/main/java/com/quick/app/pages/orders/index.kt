package com.quick.app.pages.orders

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quick.app.components.RuTopAppBar
import com.quick.app.pages.orders.comps.OrderItem
import com.quick.app.pages.orders.comps.OrderTab

@Composable
fun OrdersRoute() {
    OrdersScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(vm: OrdersViewModel = viewModel()) {
    val listState = rememberLazyListState()

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
            LazyColumn(
                state = listState,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 6.dp, horizontal = 12.dp)
            ) {
                items(vm.list.value.size) {
                    OrderItem(
                        data = vm.list.value[it],
                        modifier = Modifier.clickable {}
                    )
                }
            }
        }
    }
}