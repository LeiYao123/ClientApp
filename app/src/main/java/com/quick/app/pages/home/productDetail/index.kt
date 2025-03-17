package com.quick.app.pages.home.productDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quick.app.PreviewContent
import com.quick.app.components.Loading
import com.quick.app.pages.home.productDetail.comps.BottomBar
import com.quick.app.pages.home.productDetail.comps.ContentView
import com.quick.app.pages.home.productDetail.comps.TopBar

@Composable
fun ProductDetailRoute() {
    ProductDetailScreen()
}

@Composable
fun ProductDetailScreen() {
    val vm: DetailViewModel = viewModel()
    val uiState by vm.uiState.collectAsState()
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
            ) {
                when (uiState) {
                    is DetailUiState.Loading -> Loading()
                    is DetailUiState.Success -> ContentView((uiState as DetailUiState.Success).data)
                    is DetailUiState.Error -> Text((uiState as DetailUiState.Error).error.toString())
                }
            }
            TopBar()
        }
        BottomBar(onAddCart = { }, onBuy = { })
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GuideRoutePreview() {
    PreviewContent("detail/123456")
}