package com.quick.app.pages.productDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quick.app.PreviewContent
import com.quick.app.components.Loading
import com.quick.app.pages.productDetail.comps.BottomBar
import com.quick.app.pages.productDetail.comps.ContentView
import com.quick.app.pages.productDetail.comps.TopBar

@Composable
fun ProductDetailRoute() {
    ProductDetailScreen()
}

@Composable
fun ProductDetailScreen() {
    val vm: DetailViewModel = viewModel()
    val uiState by vm.uiState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TopBar()
            Column(modifier = Modifier.weight(1f)) {
                when (uiState) {
                    is DetailUiState.Loading -> Loading()
                    is DetailUiState.Success -> ContentView((uiState as DetailUiState.Success).data)
                    is DetailUiState.Error -> Text((uiState as DetailUiState.Error).error.toString())
                }
            }
            BottomBar(onAddCart = { }, onBuy = { })
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GuideRoutePreview() {
    PreviewContent("detail/123456")
}