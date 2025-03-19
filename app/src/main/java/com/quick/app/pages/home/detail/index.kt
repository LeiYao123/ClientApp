package com.quick.app.pages.home.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quick.app.PreviewContent
import com.quick.app.components.ErrorTips
import com.quick.app.components.Loading
import com.quick.app.pages.home.DetailUiState
import com.quick.app.pages.home.HomeViewModel
import com.quick.app.pages.home.comps.BottomBar
import com.quick.app.pages.home.comps.ContentView
import com.quick.app.pages.home.comps.TopBar
import com.quick.app.route.LocalNavController

@Composable
fun ProductDetailRoute() {
    ProductDetailScreen()
}

@Composable
fun ProductDetailScreen() {
    val navController = LocalNavController.current
    val id = navController.currentBackStackEntry?.arguments?.getString("id") ?: ""
    val owner = navController.getBackStackEntry("index/home")
    val vm: HomeViewModel = viewModel(owner)
    val uiState by vm.detailUiState

    LaunchedEffect(Unit) {
        vm.loadDetail(id)
    }

    when (val state = uiState) {
        is DetailUiState.Loading -> Loading()

        is DetailUiState.Error -> ErrorTips(
            message = state.error.message ?: "加载失败",
            onClick = { vm.loadDetail(id) })

        is DetailUiState.Success -> {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(scrollState)
                    ) { ContentView(state.data) }
                    TopBar()
                }
                BottomBar(onAddCart = { }, onBuy = { })
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GuideRoutePreview() {
    PreviewContent("detail/123456")
}